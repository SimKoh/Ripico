package ripico.database.connection;

import ripico.api.ServiceFactory;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DefaultConnectionPool {
    private static final Logger logger = Logger.getLogger(DefaultConnectionPool.class.getName());

    private final String url;
    private final String user;
    private final String password;
    private final List<Connection> connectionPool;
    private final List<Connection> usedConnections = new ArrayList<>();
    private static final int INITIAL_POOL_SIZE = 10;
    private static final int MAX_POOL_SIZE = 20;


    public static DefaultConnectionPool create() throws SQLException {
        Properties properties = loadProperties();
        String url = properties.getProperty("db_url");
        String user = properties.getProperty("db_username");
        String password = properties.getProperty("db_password");
        List<Connection> pool = new ArrayList<>(INITIAL_POOL_SIZE);
        for (int i = 0; i < INITIAL_POOL_SIZE; i++) {
            pool.add(createConnection(url, user, password));
        }
        return new DefaultConnectionPool(url, user, password, pool);
    }

    private DefaultConnectionPool(String url, String user, String password, List<Connection> connectionPool) {
        this.url = url;
        this.user = user;
        this.password = password;
        this.connectionPool = connectionPool;
    }

    public Connection getConnection() throws SQLException {
        if (connectionPool.isEmpty()) {
            if (usedConnections.size() < MAX_POOL_SIZE) {
                connectionPool.add(createConnection(url, user, password));
            } else {
                logger.log(Level.WARNING, "Connection konnte nicht erstellt werden, da die maximale Anzahl der Connections erreicht wurde");
                throw new RuntimeException("Max connections!");
            }
        }

        Connection connection = connectionPool.remove(connectionPool.size() - 1);
        usedConnections.add(connection);
        return connection;
    }

    public boolean releaseConnection(Connection connection) {
        connectionPool.add(connection);
        return usedConnections.remove(connection);
    }

    private static Connection createConnection(String url, String user, String password) throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    public void shutdown() throws SQLException {
        usedConnections.forEach(this::releaseConnection);
        for (Connection c : connectionPool) {
            c.close();
        }
        connectionPool.clear();
    }

    private static Properties loadProperties() {
        InputStream propertiesStream = ServiceFactory.class.getResourceAsStream("../../resources/ripico.properties");
        Properties properties = new Properties();
        try {
            properties.load(propertiesStream);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Fehler beim laden der Properties-Datei", e);
        }
        return properties;
    }
}
