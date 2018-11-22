package ua.nure.kn16.oleksiienko.usermanagement.db;

import ua.nure.kn16.oleksiienko.usermanagement.User;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

class HSQLDBUserDAO implements UserDAO{
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

            return use;
        } catch (DatabaseException | SQLException e) {
            throw new DatabaseException(e.toString());
        }

    }

    @Override
    public void update(User user) throws DatabaseException {
        try {
            Connection connection = connectionFactory.createConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE USERS SET firstname = ?, lastname = ?, dateofbirth = ? WHERE id = ?");
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setDate(3, Date.valueOf(user.getDateOfBirth()));
            preparedStatement.setLong(4, user.getId());

            int n = preparedStatement.executeUpdate();

            if (n != 1) throw new DatabaseException("Number of inserted rows: " + n);

            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public void delete(User user) throws DatabaseException {
        try {
            Connection connection = connectionFactory.createConnection();

            PreparedStatement statement = connection.prepareStatement(
                    "DELETE FROM USERS WHERE id = ?");
            statement.setLong(1, user.getId());

            int n = statement.executeUpdate();

            if (n != 1) throw new DatabaseException("Number of removed rows: " + n);

            statement.close();
            connection.close();
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public User find(Long id) throws DatabaseException {
        try {
            User user = null;
            Connection connection = connectionFactory.createConnection();
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM USERS WHERE id = ?");
            statement.setLong(1, id);
            ResultSet usersResultSet = statement.executeQuery();

            if (usersResultSet.next()) {
                user = new User();
                user.setId(usersResultSet.getLong(1));
                user.setFirstName(usersResultSet.getString(2));
                user.setLastName(usersResultSet.getString(3));
                Date date = usersResultSet.getDate(4);
                user.setDateOfBirth(date.toLocalDate());
            }

            connection.close();
            statement.close();
            usersResultSet.close();

            return user;
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
