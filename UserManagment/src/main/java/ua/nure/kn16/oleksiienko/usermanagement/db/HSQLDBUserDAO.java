package ua.nure.kn16.oleksiienko.usermanagement.db;

import ua.nure.kn16.oleksiienko.usermanagement.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class HSQLDBUserDAO implements UserDAO{
    ConnectionFactory connectionFactory;

    HSQLDBUserDAO(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    /**
     * Creates new user row in the HSQL database
     * @param user
     * @return user with ID
     */
    @Override
    public User create(User user) throws DatabaseException {
        Connection connection = connectionFactory.createConnection();
        java.sql.PreparedStatement statement;
        java.sql.CallableStatement callableStatement;
        User use;

        try {
            statement = connection.prepareStatement(
                    "INSERT INTO users (firstname, lastname, dateofbirth) VALUES (?, ?, ?)");
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setDate(3, java.sql.Date.valueOf(user.getDateOfBirth()));
            int insertRows = statement.executeUpdate();

            if (insertRows != 1) throw new DatabaseException("Numbers of inserted rows is not equal to 1");

            callableStatement = connection.prepareCall("call IDENTITY()");

            java.sql.ResultSet keys = callableStatement.executeQuery();
            use = new User(user);
            if (keys.next()) {
                use.setId(keys.getLong(1));
            }

            keys.close();
            callableStatement.close();
            statement.close();
            connection.close();
        } catch (DatabaseException | SQLException e) {
            throw new DatabaseException(e.toString());
        }

        return use;
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
    public List<User> findAll() throws DatabaseException {
        try {
            Connection connection = connectionFactory.createConnection();
            java.sql.Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users");

            ArrayList<User> result = new ArrayList<>();
            while(resultSet.next()) {
                Long id = resultSet.getLong(1);
                String firstName = resultSet.getString(2);
                String lastName = resultSet.getString(3);
                LocalDate birthDate = resultSet.getDate(4).toLocalDate();
                result.add(new User(id, firstName, lastName, birthDate));
            }
            resultSet.close();
            statement.close();
            connection.close();

            return result;
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }

    }

    @Override
    public void setConnectionFactory(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public ConnectionFactory getConnectionFactory() {
        return connectionFactory;
    }

    public HSQLDBUserDAO() { }
}
