package ripico.database;

import ripico.api.dal.MitarbeiterAdapter;
import ripico.api.domain.Mitarbeiter;
import ripico.api.domain.MitarbeiterBuilder;
import ripico.database.connection.DefaultConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class DatabaseMitarbeiterAdapterImpl implements MitarbeiterAdapter {

    private final DefaultConnectionPool connectionPool;

    public DatabaseMitarbeiterAdapterImpl() throws SQLException {
        connectionPool = DefaultConnectionPool.create();
    }

    @Override
    public Optional<Mitarbeiter> readMitarbeiter(String username) {
        Connection connection;
        try {
            connection = connectionPool.getConnection();
        } catch (SQLException e) {
            return Optional.empty();
        }
        try (PreparedStatement preparedStatement = createPreparedStatement(connection, username);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            Mitarbeiter mitarbeiter = null;
            while (resultSet.next()) {
                mitarbeiter = MitarbeiterBuilder
                        .newMitarbeiter()
                        .withBenutzername(resultSet.getString(1))
                        .withPasswort(resultSet.getString(2))
                        .withVorname(resultSet.getString(3))
                        .withNachname(resultSet.getString(4))
                        .build();
            }
            connectionPool.releaseConnection(connection);
            if (mitarbeiter == null) {
                return Optional.empty();
            }
            return Optional.of(mitarbeiter);
        } catch (SQLException e) {
            return Optional.empty();
        }
    }

    private PreparedStatement createPreparedStatement(Connection connection, String username) throws SQLException {
        String sqlStatement = "SELECT benutzername, passwort, vorname, nachname FROM ripico.mitarbeiter WHERE benutzername = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement);
        preparedStatement.setString(1, username);
        return preparedStatement;
    }
}
