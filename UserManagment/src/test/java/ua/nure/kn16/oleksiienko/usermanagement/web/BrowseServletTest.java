package ua.nure.kn16.oleksiienko.usermanagement.web;

import org.junit.Test;
import ua.nure.kn16.oleksiienko.usermanagement.User;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class BrowseServletTest extends MockServletTestCase {
    public void setUp() throws Exception {
        super.setUp();
        createServlet(BrowseServlet.class);
    }

    @Test
    public void testEditWithNoOneSelected() {
        addRequestParameter("editButton", "Edit");
        doPost();
        assertNotNull(getWebMockObjectFactory().getMockRequest().getAttribute("error"));
    }

    @Test
    public void testDelete() {
        User user = new User(1000L, "Tvaryna", "Nerozumna", LocalDate.now());

        getMockUserDAO().expectAndReturn("find", new Long(1000), user);
        getMockUserDAO().expect("delete", user);
        getMockUserDAO().expect("findAll");
        addRequestParameter("deleteButton", "Delete");
        addRequestParameter("id", "1000");
        doPost();
        assertNotNull(getWebMockObjectFactory().getMockRequest().getAttribute("message"));
    }

    @Test
    public void testDeleteWithNoOneSelected() {
        addRequestParameter("deleteButton", "Delete");
        doPost();
        assertNotNull(getWebMockObjectFactory().getMockRequest().getAttribute("error"));
    }

    @Test
    public void testDetails() {
        User user = new User(1000L, "Sos", "Tudep", LocalDate.now());

        getMockUserDAO().expectAndReturn("find", 1000L, user);
        addRequestParameter("detailsButton", "Details");
        addRequestParameter("id", "1000");
        doPost();

        User userInSession = (User) getWebMockObjectFactory().getMockSession().getAttribute("user");

        assertNotNull("Could not find the user in session", userInSession);
        assertSame(user, userInSession);
    }

    @Test
    public void testDetailsWithNoOneSelected() {
        addRequestParameter("detailsButton", "Details");
        doPost();
        assertNotNull(getWebMockObjectFactory().getMockRequest().getAttribute("error"));
    }

    @Test
    public void testBrowse() {
        User user = new User(1000L, "John", "Doe", LocalDate.now());
        List<User> list = Collections.singletonList(user);

        getMockUserDAO().expectAndReturn("findAll", list);
        doGet();
        Collection<User> collection = (Collection<User>) getWebMockObjectFactory().getMockSession().getAttribute("users");
        assertNotNull(collection);
        assertSame(list, collection);
    }

    @Test
    public void testEdit() {
        User user = new User(1000L, "John", "Doe", LocalDate.now());

        getMockUserDAO().expectAndReturn("find", new Long(1000), user);
        addRequestParameter("editButton", "Edit");
        addRequestParameter("id", "1000");
        doPost();

        User userInSession = (User) getWebMockObjectFactory().getMockSession().getAttribute("user");

        assertNotNull("Could not find the user in session", user);
        assertSame(user, userInSession);
    }
}
