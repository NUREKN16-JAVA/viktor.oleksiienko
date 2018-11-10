package ua.nure.kn16.oleksiienko.usermanagement.db;

import ua.nure.kn16.oleksiienko.usermanagement.User;

import java.sql.SQLException;
import java.util.Collection;

public class HSQLDBUserDAO implements UserDAO{
    ConnectionFactory connectionFactory;

    HSQLDBUserDAO(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    @Override
    public User create(User user) {
        java.sql.Connection connection = connectionFactory.createConnection();
        java.sql.PreparedStatement statement;

        try {
            statement = connection.prepareStatement(
                    "NSERT INTO users (firstname, lastname, dateofbirth) VALUES ("
                            + user.getFirstName() + ", "
                            + user.getLastName() + ", "
                            + user.getDateOfBirth() + ")");
            int n = statement.executeUpdate()
            if (n != 1) throw new DatabaseException("Numbers of inserted rows");
        } catch (SQLException e) {

        }

        return null;

    }

    @Override
    public void update(User user) throws DatabaseException {

    }

    @Override
    public void delete(User user) throws DatabaseException {

    }

    @Override
    public User find(Long id) throws DatabaseException{
        return null;
    }

    @Override
    public Collection<User> findAll() throws DatabaseException {
        return null;
    }
}
