package ua.nure.kn16.oleksiienko.usermanagement.db;

import ua.nure.kn16.oleksiienko.usermanagement.User;

import java.util.Collection;
import java.util.List;

/**
 * Interface for User's Data Access Object
 *
 */
public interface UserDAO {

    User create(User user) throws DatabaseException;

    void update(User user) throws DatabaseException;

    void delete(User user) throws DatabaseException;

    User find(Long id) throws DatabaseException;

    Collection<User> find(String firstName, String lastName) throws DatabaseException;

    List<User> findAll() throws DatabaseException;

    /**
     * Set connection factory for User's data access object.
     * @param connectionFactory
     */
    void setConnectionFactory(ConnectionFactory connectionFactory);
}
