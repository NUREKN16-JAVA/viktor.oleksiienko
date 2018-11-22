package ua.nure.kn16.oleksiienko.usermanagement.db;

import ua.nure.kn16.oleksiienko.usermanagement.User;

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

    List<User> findAll() throws DatabaseException;
}
