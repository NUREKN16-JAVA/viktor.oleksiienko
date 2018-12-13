package ua.nure.kn16.oleksiienko.usermanagement.db;

import java.sql.Connection;
import java.util.Properties;

public class ConnectionFactoryImplement implements ConnectionFactory {
    private static String DRIVER;
    private static String URL;
    private static String USER;
    private static String PASSWORD;

    public ConnectionFactoryImplement() {     }

    public ConnectionFactoryImplement(Properties properties) {
        this.USER = properties.getProperty("connection.user");
        this.PASSWORD = properties.getProperty("connection.password");
        this.URL = properties.getProperty("connection.url");
        this.DRIVER = properties.getProperty("connection.driver");
    }

    @Override
    public Connection createConnection() throws DatabaseException{
        try {
            Class.forName(DRIVER);
        } catch (java.lang.ClassNotFoundException e) {
            throw new DatabaseException(e.toString());
        }
        Connection newConnection;
        try {
            newConnection = java.sql.DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (java.sql.SQLException e) {
            throw new DatabaseException(e);
        }

        return newConnection;
    }
}
