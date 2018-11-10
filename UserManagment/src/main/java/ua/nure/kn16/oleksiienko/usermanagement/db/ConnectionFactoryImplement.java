package ua.nure.kn16.oleksiienko.usermanagement.db;

import java.sql.Connection;

public class ConnectionFactoryImplement implements ConnectionFactory {
    @Override
    public Connection createConnection() throws DatabaseException{
        final String DRIVER = "org.hsqldb.jdbcDriver";
        final String URL = "jdbc:hsqldb:file:db/usermanagement";
        final String USER = "sa";
        final String PASSWORD = "";

        try {
            Class.forName(DRIVER);
        } catch (java.lang.ClassNotFoundException e) {
            throw new DatabaseException(e.toString());
        }
        Connection newConnection;
        try {
            newConnection = java.sql.DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (java.sql.SQLException e) {
            throw new DatabaseException(e.toString());
        }

        return newConnection;
    }
}
