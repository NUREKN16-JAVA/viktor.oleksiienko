package ua.nure.kn16.oleksiienko.usermanagement.db;

import ua.nure.kn16.oleksiienko.usermanagement.User;

import java.io.IOException;
import java.util.Properties;

public abstract class DAOFactory {
    protected static Properties properties;
    private static DAOFactory instance;

    static {
        properties = new Properties();
        try {
            properties.load(DAOFactory.class.getClassLoader().getResourceAsStream("settings.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected DAOFactory () {}

    public static void init (Properties properties) {
        DAOFactory.properties = properties;
        instance = null;
    }

    protected ConnectionFactory getConnectionFactory() {
        return new ConnectionFactoryImplement(properties);
    }

    public abstract UserDAO getUserDAO()  throws ReflectiveOperationException;

    public static synchronized DAOFactory getInstance() {
        if (instance == null) {
            try {
                Class factoryClass = Class.forName(properties.getProperty("dao.factory"));
                instance = (DAOFactory) factoryClass.newInstance();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        return instance;
    }
}
