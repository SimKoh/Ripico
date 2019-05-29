package ripico.database;

import ripico.api.ServiceFactory;
import ripico.api.dal.SpielAdapter;
import ripico.api.dal.WettscheinAdapter;
import ripico.api.domain.*;
import ripico.api.domain.enums.QuotenArt;
import ripico.database.connection.DefaultConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DatabaseWettscheinAdapterImpl implements WettscheinAdapter {

    private DefaultConnectionPool connectionPool;
    private SpielAdapter spielAdapter;

    public DatabaseWettscheinAdapterImpl() throws SQLException {
        this.connectionPool = DefaultConnectionPool.create();
        this.spielAdapter = ServiceFactory.createService(SpielAdapter.class);
    }

    @Override
    public Optional<Wettschein> readWettschein(int wettenscheinId) {
        Connection connection;
        try {
            connection = connectionPool.getConnection();
        } catch (SQLException e) {
            return Optional.empty();
        }
        try (PreparedStatement preparedStatement = createPreparedStatementRead(connection, wettenscheinId);
             PreparedStatement preparedStatementEinsatz = createPreparedStatementEinsatz(connection, wettenscheinId);
             ResultSet resultSetEinsatz = preparedStatementEinsatz.executeQuery();
             ResultSet resultSet = preparedStatement.executeQuery()) {
            List<Wette> wetten = new ArrayList<>();
            while (resultSet.next()) {
                WetteBuilder wetteBuilder = WetteBuilder
                        .newWette()
                        .withWettscheinId(resultSet.getInt(1))
                        .withWettenId(resultSet.getInt(2));
                String quotenArtString = resultSet.getString(4);
                if (quotenArtString != null) {
                    wetteBuilder
                            .withGesetzteWette(QuotenArt.valueOf(quotenArtString));
                }
                Optional<Spiel> spiel = spielAdapter.readSpiel(resultSet.getInt(3));
                wetteBuilder.withSpiel(spiel.orElse(SpielBuilder.newSpiel().build()));
                wetten.add(wetteBuilder.build());
            }
            float einsatz = 0;
            while (resultSetEinsatz.next()) {
                einsatz = resultSetEinsatz.getFloat(1);
            }
            Wettschein wettschein = WettscheinBuilder
                    .newWettschein()
                    .withWettscheinId(wettenscheinId)
                    .withWetten(wetten)
                    .withEinsatz(einsatz)
                    .build();
            if (wettschein == null) {
                return Optional.empty();
            }
            connectionPool.releaseConnection(connection);
            return Optional.of(wettschein);
        } catch (SQLException e) {
            return Optional.empty();
        }
    }

    @Override
    public int zaehleWettscheine() {
        Connection connection;
        try {
            connection = connectionPool.getConnection();
        } catch (SQLException e) {
            return 0;
        }
        try (PreparedStatement preparedStatement = createPreparedStatementCount(connection);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            int count = 0;
            while (resultSet.next()) {
                count = resultSet.getInt(1);
            }
            return count;
        } catch (SQLException e) {
            return 0;
        }
    }

    @Override
    public Wettschein createWettschein(Wettschein wettschein) {
        Connection connection;
        try {
            connection = connectionPool.getConnection();
        } catch (SQLException e) {
            return null;
        }
        try (PreparedStatement preparedStatement = createPreparedStatement(connection, wettschein)) {
            int ergebnis = preparedStatement.executeUpdate();
            if (ergebnis == 0) {
                return null;
            }
            return wettschein;
        } catch (SQLException e) {
            return null;
        }
    }

    private PreparedStatement createPreparedStatementRead(Connection connection, int wettscheinNr) throws SQLException {
        String sqlStatement = "SELECT wettschein_id,wette_id, spiel_id, gesetzte_wette " +
                "FROM ripico.wette w " +
                "WHERE wettschein_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement);
        preparedStatement.setInt(1, wettscheinNr);
        return preparedStatement;
    }

    private PreparedStatement createPreparedStatementEinsatz(Connection connection, int wettscheinId) throws SQLException {
        String sqlStatement = "SELECT einsatz " +
                "FROM ripico.wettschein w " +
                "WHERE wettschein_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement);
        preparedStatement.setInt(1, wettscheinId);
        return preparedStatement;
    }

    private PreparedStatement createPreparedStatementCount(Connection connection) throws SQLException {
        String sqlStatement = "SELECT count(*) " +
                "FROM ripico.wettschein";
        return connection.prepareStatement(sqlStatement);
    }

    private PreparedStatement createPreparedStatement(Connection connection, Wettschein wettschein) throws SQLException {
        String sqlStatement = "INSERT INTO ripico.wettschein(wettschein_id, einsatz) values (?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement);
        preparedStatement.setInt(1, wettschein.getWettscheinId());
        preparedStatement.setFloat(2, wettschein.getEinsatz());
        return preparedStatement;
    }
}
