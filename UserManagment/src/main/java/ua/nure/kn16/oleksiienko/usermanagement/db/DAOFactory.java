package ua.nure.kn16.oleksiienko.usermanagement.db;

import ua.nure.kn16.oleksiienko.usermanagement.User;

import java.io.IOException;
import java.util.Properties;

public class DAOFactory {
    private  final Properties PROPERTIES;
    private UserDAO result = null;
    private final static DAOFactory INSTANCE = new DAOFactory();

    public static DAOFactory getInstance() {
        return INSTANCE;
    }

    private DAOFactory() throws RuntimeException {
        try {
            PROPERTIES = new Properties();
            PROPERTIES.load(getClass().getClassLoader().getResourceAsStream("settings.properties"));

        }catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private ConnectionFactory getConnectionFactory() {
        String user = PROPERTIES.getProperty("connection.user");
        String password = PROPERTIES.getProperty("connection.password");
        String url = PROPERTIES.getProperty("connection.url");
        String driver = PROPERTIES.getProperty("connection.driver");
        return new ConnectionFactoryImplement(driver, url, user, password);
    }

    public UserDAO getUserDAO() throws DatabaseException {
        try {
            Class DAO = Class.forName(PROPERTIES.getProperty("ua.nure.kn16.oleksiienko.usermanagement.db.UserDAO"));
            UserDAO userDAO = (UserDAO) DAO.newInstance();
            userDAO.setConnectionFactory(this.getConnectionFactory());
            return userDAO;
        } catch (ClassNotFoundException e) {
            throw new DatabaseException("Illegal UserDAO class name property!");
        } catch (Exception e) {
            throw new DatabaseException(e.toString());
        }
    }
}
