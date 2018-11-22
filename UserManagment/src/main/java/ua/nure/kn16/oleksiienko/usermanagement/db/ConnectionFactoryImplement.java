package ua.nure.kn16.oleksiienko.usermanagement.db;

import java.sql.Connection;

public class ConnectionFactoryImplement implements ConnectionFactory {
    private static String DRIVER;
    private static String URL;
    private static String USER;
    private static String PASSWORD;

    public ConnectionFactoryImplement() {

    }

    public ConnectionFactoryImplement(String driver, String url, String user, String password) {
        this.DRIVER = driver;
        this.URL = url;
        this.USER = user;
        this.PASSWORD = password;
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
