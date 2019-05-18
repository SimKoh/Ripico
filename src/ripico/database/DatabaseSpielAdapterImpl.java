package ripico.database;

import ripico.api.dal.SpielAdapter;
import ripico.api.domain.*;
import ripico.api.domain.enums.QuotenArt;
import ripico.database.connection.DefaultConnectionPool;
import ripico.service.exception.ResourceNotFoundException;

import java.sql.*;
import java.util.*;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseSpielAdapterImpl implements SpielAdapter {
    private static final Logger logger = Logger.getLogger(DatabaseSpielAdapterImpl.class.getName());

    private final DefaultConnectionPool connectionPool;

    public DatabaseSpielAdapterImpl() throws SQLException {
        connectionPool = DefaultConnectionPool.create();
    }

    @Override
    public Spiel createSpiel(Spiel spiel) throws SQLException {
//        String sqlStatement = "INSERT into ripico.SPIEL (sportart_id, mannschaft_heim,mannschaft_auswaerts,datum,ergebnis) VALUES (?,?,?,?,?)";
//        Connection connection = connectionPool.getConnection();
//        PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement, Statement.RETURN_GENERATED_KEYS);
//
//        preparedStatement.setString(1, "Test");
//
//        int inserted = preparedStatement.executeUpdate();
//        if (inserted > 0) {
//            logger.log(Level.INFO, "Ein Spiel wurde hinzugefügt");
//        } else {
//            throw new SQLException("Spiel wurde nicht hinzugefügt");
//        }
//
//        ResultSet tableKeys = preparedStatement.getGeneratedKeys();
//        tableKeys.next();
//        int autoGeneratedID = tableKeys.getInt(1);
//        spiel.setSpielId(autoGeneratedID);
//        return spiel;
        return null;
    }

    @Override
    public Optional<Spiel> readSpiel(int spielNr) {
        Connection connection;
        try {
            connection = connectionPool.getConnection();
        } catch (SQLException e) {
            return Optional.empty();
        }
        try (PreparedStatement preparedStatement = createPreparedStatementRead(connection, spielNr);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            SpielBuilder spielBuilder = SpielBuilder
                    .newSpiel();
            Spiel spiel = null;
            while (resultSet.next()) {
                spielBuilder
                        .withSpielId(resultSet.getInt(1))
                        .withSportart(resultSet.getString(2))
                        .withMannschaftHeim(resultSet.getString(3))
                        .withMannschaftHeimLogoPfad(resultSet.getString(4))
                        .withMannschaftAuswaerts(resultSet.getString(5))
                        .withMannschaftAuswaertsLogoPfad(resultSet.getString(6))
                        .withDatum(convertTimestampToDate(resultSet.getTimestamp(7)));
                String quotenArtString = resultSet.getString(8);
                if (quotenArtString != null) {
                    spielBuilder
                            .withErgebnis(QuotenArt.valueOf(quotenArtString));
                }
                Map<QuotenArt, Float> quotenFromDatabase = getQuotenFromDatabase(spielNr, connection);
                spiel = spielBuilder.withQuoten(quotenFromDatabase).build();
            }
            connectionPool.releaseConnection(connection);
            if (spiel == null) {
                return Optional.empty();
            }
            return Optional.of(spiel);
        } catch (SQLException | ResourceNotFoundException e) {
            return Optional.empty();
        }
    }

    @Override
    public Spiel updateSpiel(Spiel spiel) {
        return null;
    }

    @Override
    public List<Spiel> getAllSpiele() {
        Connection connection;
        try {
            connection = connectionPool.getConnection();
        } catch (SQLException e) {
            return new ArrayList<>();
        }
        try (PreparedStatement preparedStatement = createPreparedStatementRead(connection, 0);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            List<Spiel> spiele = new ArrayList<>();
            while (resultSet.next()) {
                SpielBuilder spielBuilder = SpielBuilder
                        .newSpiel()
                        .withSpielId(resultSet.getInt(1))
                        .withSportart(resultSet.getString(2))
                        .withMannschaftHeim(resultSet.getString(3))
                        .withMannschaftHeimLogoPfad(resultSet.getString(4))
                        .withMannschaftAuswaerts(resultSet.getString(5))
                        .withMannschaftAuswaertsLogoPfad(resultSet.getString(6))
                        .withDatum(convertTimestampToDate(resultSet.getTimestamp(7)));
                String quotenArtString = resultSet.getString(8);
                if (quotenArtString != null) {
                    spielBuilder
                            .withErgebnis(QuotenArt.valueOf(quotenArtString));
                }
                Map<QuotenArt, Float> quotenFromDatabase = getQuotenFromDatabase(resultSet.getInt(1), connection);
                spielBuilder.withQuoten(quotenFromDatabase);
                spiele.add(spielBuilder.build());
            }
            connectionPool.releaseConnection(connection);
            return spiele;
        } catch (SQLException | ResourceNotFoundException e) {
            return new ArrayList<>();
        }
    }

    private PreparedStatement createPreparedStatementRead(Connection connection, int spielNr) throws SQLException {
        String sqlStatement = "SELECT spi.spiel_id,spo.sportart_name, mheim.mannschaft_name, mheim.logopfad, " +
                "maus.mannschaft_name, maus.logopfad, spi.datum, spi.ergebnis " +
                "FROM ripico.spiel spi " +
                "JOIN ripico.sportart spo on spo.sportart_id = spi.sportart_id " +
                "JOIN ripico.mannschaft mheim on mheim.mannschaft_id = spi.mannschaft_heim " +
                "JOIN ripico.mannschaft maus on maus.mannschaft_id = spi.mannschaft_auswaerts ";
        if (spielNr != 0) {
            sqlStatement += "WHERE spiel_id = ?";

        }
        PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement);
        if (spielNr != 0) {
            preparedStatement.setInt(1, spielNr);
        }
        return preparedStatement;
    }


    private PreparedStatement createPreparedStatementReadQuoten(Connection connection, int spielNr) throws SQLException {
        String sqlStatement = "SELECT quotenart, quote " +
                "FROM ripico.quote q " +
                "WHERE spiel_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement);
        preparedStatement.setInt(1, spielNr);
        return preparedStatement;
    }


    private Map<QuotenArt, Float> getQuotenFromDatabase(int spielNr, Connection connection) throws ResourceNotFoundException {
        try (PreparedStatement preparedStatement = createPreparedStatementReadQuoten(connection, spielNr);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            Map<QuotenArt, Float> quoten = new EnumMap<>(QuotenArt.class);
            while (resultSet.next()) {
                QuotenArt quotenArt = QuotenArt.valueOf(resultSet.getString(1));
                Float quote = resultSet.getFloat(2);
                quoten.put(quotenArt, quote);
            }
            connectionPool.releaseConnection(connection);
            if (quoten.size() != 3) {
                throw new ResourceNotFoundException("Quotensize ist nicht gleich 3");
            }
            return quoten;
        } catch (SQLException e) {
            throw new ResourceNotFoundException("Error in getQuotenFromDB", e);
        }
    }

    private Date convertTimestampToDate(Timestamp timestamp) {
        Date date = null;
        if (timestamp != null) {
            date = new Date(timestamp.getTime());
        }
        return date;
    }
}
