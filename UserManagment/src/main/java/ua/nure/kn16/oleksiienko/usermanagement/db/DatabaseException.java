package ua.nure.kn16.oleksiienko.usermanagement.db;

public class DatabaseException extends Exception {
    public DatabaseException(java.sql.SQLException e) {
        super(e);
    }

    public DatabaseException(String s) {
        super(s);
    }
}
