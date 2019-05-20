package ripico.database;

import ripico.api.ServiceFactory;
import ripico.api.dal.SpielAdapter;
import ripico.api.dal.WettscheinAdapter;
import ripico.api.domain.*;
import ripico.api.domain.enums.QuotenArt;
import ripico.database.connection.DefaultConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
            Wettschein wettschein = WettscheinBuilder
                    .newWettschein()
                    .withWettscheinId(wettenscheinId)
                    .withWetten(wetten)
                    .build();
            connectionPool.releaseConnection(connection);
            if (wettschein == null) {
                return Optional.empty();
            }
            return Optional.of(wettschein);
        } catch (SQLException e) {
            return Optional.empty();
        }
    }

    @Override
    public int zaehleWettschein() {
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

    private PreparedStatement createPreparedStatementRead(Connection connection, int wettscheinNr) throws SQLException {
        String sqlStatement = "SELECT wettschein_id,wette_id, spiel_id, gesetzte_wette " +
                "FROM ripico.wette w " +
                "WHERE wettschein_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement);
        preparedStatement.setInt(1, wettscheinNr);
        return preparedStatement;
    }

    private PreparedStatement createPreparedStatementCount(Connection connection) throws SQLException {
        String sqlStatement = "SELECT count(*) " +
                "FROM ripico.wette w " +
                "group by wettschein_id";
        PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement);
        return preparedStatement;
    }
}
