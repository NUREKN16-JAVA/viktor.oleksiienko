package ua.nure.kn16.oleksiienko.usermanagement.db;

public interface ConnectionFactory {
    java.sql.Connection createConnection() throws DatabaseException;
}
