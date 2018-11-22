package ua.nure.kn16.oleksiienko.usermanagement.db;

import org.dbunit.DatabaseTestCase;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.XmlDataSet;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import ua.nure.kn16.oleksiienko.usermanagement.User;

import java.time.LocalDate;
import java.util.Collection;


public class HSQLDBUserDAOTest extends DatabaseTestCase {
    private User user;
    private UserDAO dao;
    private ConnectionFactory connectionFactory;

    @Override
    protected IDatabaseConnection getConnection() throws DatabaseException {
        return new DatabaseConnection(connectionFactory.createConnection());
    }

    @Override
    protected IDataSet getDataSet() throws Exception {
        return new XmlDataSet(getClass().getClassLoader().getResourceAsStream("usersDataSet.xml"));
    }

    @Before
    public void setUp() throws Exception{
        dao = DAOFactory.getInstance().getUserDAO();
        connectionFactory = new ConnectionFactoryImplement();


        User user1 = new User("So", "Stupid", LocalDate.of(2001, 9, 11));
        User user2 = new User("Sos", "Tupet", LocalDate.of(2007, 5, 5));

        dao.create(user1);
        dao.create(user2);

    }

    @Test
    public void testCreate() {
        user = new User("So", "Stupid", LocalDate.of(2001, 9, 11));

        assertNull(user.getId());
        try {
            user = dao.create(user);
        } catch (DatabaseException e) {
            fail(e.toString());
        }

        assertNotNull(user.getId());
    }

    @Test
    public void test111FindAll() {
        try {
            Collection<User> collection;
            collection = dao.findAll();

            assertNotNull("Collection is null", collection);
            assertEquals("Collection size.", 2, collection.size());
        } catch (Exception e) {
            fail(e.toString());
        }
    }
}
