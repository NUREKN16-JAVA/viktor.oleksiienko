package ua.nure.kn16.oleksiienko.usermanagement.db;

import ua.nure.kn16.oleksiienko.usermanagement.User;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MockUserDAO implements UserDAO {
    private Long globalId = 0L;
    private Map<Long, User> users = new HashMap<>();

    @Override
    public Collection<User> find(String firstName, String lastName) throws DatabaseException {
        throw new UnsupportedOperationException();
    }

    @Override
    public User create(User user) throws DatabaseException {
        Long userId = ++globalId;
        user.setId(userId);
        users.put(userId, user);
        return user;
    }

    @Override
    public void update(User user) throws DatabaseException {
        Long userId = user.getId();
        users.remove(userId);
        users.put(userId, user);
    }

    @Override
    public void delete(User user) throws DatabaseException {
        Long userId = user.getId();
        users.remove(userId);
    }

    @Override
    public User find(Long id) throws DatabaseException {
        return users.get(id);
    }

    @Override
    public List<User> findAll() throws DatabaseException {
        return (List<User>) users.values();
    }

    @Override
    public void setConnectionFactory(ConnectionFactory connectionFactory) {

    }
}
