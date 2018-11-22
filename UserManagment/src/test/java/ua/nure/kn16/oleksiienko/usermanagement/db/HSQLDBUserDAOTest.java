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
    private User user, user1 = null;
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
        if (user1 != null && user1.getId() == null || user1 == null) {
            user1 = new User("Test", "User", LocalDate.of(1, 1, 1));
            user1 = dao.create(user1);
        }

    }

    @Test
    public void testCreate() {
        user = new User("So", "Stupid", LocalDate.of(2004, 5, 2));

        assertNull(user.getId());
        try {
            user = dao.create(user);
        } catch (DatabaseException e) {
            fail(e.toString());
        }

        assertNotNull(user.getId());
    }

    @Test
    public void testFindAll() {
        try {
            User user1 = new User("Allahu", "Akbar", LocalDate.of(2001, 9, 11));
            //User user2 = new User("Sos", "Tupet", LocalDate.of(2007, 5, 5));

            dao.create(user1);
            //dao.create(user2);

            Collection<User> collection;
            collection = dao.findAll();

            assertNotNull("Collection is null", collection);
            assertEquals("Collection size.", 2, collection.size());
        } catch (Exception e) {
            fail(e.toString());
        }
    }

    @Test
    public void testFind() {
        try {
            User testUser = dao.find(user1.getId());

            assertNotNull(testUser);
            assertEquals(testUser.getFirstName(), user1.getFirstName());
            assertEquals(testUser.getLastName(), user1.getLastName());
            assertEquals(testUser.getDateOfBirth(), user1.getDateOfBirth());
        } catch (Exception e) {
            fail(e.toString());
        }
    }

    @Test
    public void testUpdate() {
        try {
            // There is a row user1 in table, 'cause @Before setUp() creates this row
            // then we just copy user1 to user2 and change all data (except ID)
            // and update row. After that method just check data for correct update
            User user2 = new User(user1);
            user2.setFirstName("New");
            user2.setLastName("Data");
            user2.setDateOfBirth(LocalDate.of(2000, 1, 7));

            dao.update(user2);
            User user3 = dao.find(user2.getId());
            assertEquals(user3.getFirstName(), user2.getFirstName());
            assertEquals(user3.getLastName(), user2.getLastName());
            assertEquals(user3.getDateOfBirth(), user2.getDateOfBirth());
        } catch (Exception e) {
            fail(e.toString());
        }
    }

    @Test
    public void testDelete() {
        try {
            // user1 was created before this test case
            dao.delete(user1);
            assertNull(dao.find(user1.getId()));
            user1.setId(null);
        } catch (Exception e) {
            fail(e.toString());
        }
    }
}
