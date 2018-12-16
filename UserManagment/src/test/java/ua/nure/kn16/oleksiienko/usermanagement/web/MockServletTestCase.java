package ua.nure.kn16.oleksiienko.usermanagement.web;

import com.mockobjects.dynamic.Mock;
import com.mockrunner.servlet.BasicServletTestCaseAdapter;
import ua.nure.kn16.oleksiienko.usermanagement.db.DAOFactory;
import ua.nure.kn16.oleksiienko.usermanagement.db.MockDaoFactory;

import java.util.Properties;

public abstract class MockServletTestCase extends BasicServletTestCaseAdapter {
    private Mock mockUserDAO;

    public void setUp() throws Exception {
        super.setUp();
        Properties properties = new Properties();
        properties.setProperty("dao.factory", MockDaoFactory.class.getName());
        DAOFactory.init(properties);
        mockUserDAO = ((MockDaoFactory) DAOFactory.getInstance()).getMockUserDao();

    }

    public void tearDown() {
        mockUserDAO.verify();

    }

    public Mock getMockUserDAO() {
        return mockUserDAO;
    }

    public void setMockUserDAO(Mock mockUserDAO) {
        this.mockUserDAO = mockUserDAO;
    }
}
