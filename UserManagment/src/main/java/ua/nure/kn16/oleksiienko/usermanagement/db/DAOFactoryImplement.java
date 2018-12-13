package ua.nure.kn16.oleksiienko.usermanagement.db;

public class DAOFactoryImplement  extends DAOFactory {
    @Override
    public UserDAO getUserDAO() throws ReflectiveOperationException {
        UserDAO userDAO = null;
        try {
            Class UserDOAClass = Class.forName(properties.getProperty("ua.nure.kn16.oleksiienko.usermanagement.db.UserDAO"));
            userDAO = (UserDAO) UserDOAClass.newInstance();
            userDAO.setConnectionFactory(getConnectionFactory());
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            throw new ReflectiveOperationException(e);
        }

        return userDAO;
    }
}
