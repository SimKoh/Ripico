package ripico.database;

import ripico.api.dal.MannschaftAdapter;
import ripico.api.domain.Mannschaft;
import ripico.api.domain.MannschaftBuilder;
import ripico.api.domain.Mitarbeiter;
import ripico.api.domain.MitarbeiterBuilder;
import ripico.api.domain.enums.Sportart;
import ripico.database.connection.DefaultConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DatabaseMannschaftAdapterImpl implements MannschaftAdapter {

    private final DefaultConnectionPool connectionPool;

    public DatabaseMannschaftAdapterImpl(DefaultConnectionPool connectionPool) throws SQLException {
        this.connectionPool = DefaultConnectionPool.create();
    }

    @Override
    public List<Mannschaft> alleManschaften() {
        Connection connection;
        try {
            connection = connectionPool.getConnection();
        } catch (SQLException e) {
            return new ArrayList<>();
        }
        try (PreparedStatement preparedStatement = createPreparedStatement(connection);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            List<Mannschaft> mannschaften = new ArrayList<>();
            while (resultSet.next()) {
                MannschaftBuilder mannschaftBuilder = MannschaftBuilder
                        .newMannschaft()
                        .withMannschaftId(resultSet.getInt(1))
                        .withMannschaftsName(resultSet.getString(2))
                        .withMannschaftLogo(resultSet.getString(3))
                        .withSportart(Sportart.getSportartFromId(resultSet.getInt(4)));
                mannschaften.add(mannschaftBuilder.build());
            }
            connectionPool.releaseConnection(connection);
            return mannschaften;
        } catch (SQLException e) {
            return new ArrayList<>();
        }
    }

    private PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
        String sqlStatement = "SELECT mannschaft_id, mannschaft_name, logopfad, sportart_id FROM ripico.mannschaft";
        PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement);
        return preparedStatement;
    }
}
