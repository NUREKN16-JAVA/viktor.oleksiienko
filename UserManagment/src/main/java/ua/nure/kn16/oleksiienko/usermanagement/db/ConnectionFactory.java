package ua.nure.kn16.oleksiienko.usermanagement.db;

public interface ConnectionFactory {
    public java.sql.Connection createConnection() throws DatabaseException;
}
