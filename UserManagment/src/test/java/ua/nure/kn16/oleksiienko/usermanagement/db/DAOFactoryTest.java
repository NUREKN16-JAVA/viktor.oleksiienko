package ua.nure.kn16.oleksiienko.usermanagement.db;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class DAOFactoryTest {
    DAOFactory daoFactory;

    @Before
    public void setUp() throws Exception {
        daoFactory = DAOFactory.getInstance();
    }

    /**
     * Tests creation of the new connection with params from properties file
     */
    @Test
    public void getUserDAOTest () {
        assertNotNull(daoFactory);
        UserDAO userDAO;
        try {
            userDAO = daoFactory.getUserDAO();
            assertNotNull(userDAO);
        } catch (Exception e) {
            fail(e.toString());
        }
    }
}
