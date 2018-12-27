package ua.nure.kn16.oleksiienko.usermanagement.db;

import com.mockobjects.dynamic.Mock;

public class MockDaoFactory extends DAOFactory{
    private Mock mockUserDao;

    MockDaoFactory() {
        mockUserDao = new Mock(UserDAO.class);
    }

    public Mock getMockUserDao() {
        return mockUserDao;
    }

    @Override
    public UserDAO getUserDAO() {
        return (UserDAO) mockUserDao.proxy();
    }
}
