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
        User userinSession = (User) getWebMockObjectFactory().getMockSession().getAttribute("user");
        assertNotNull("Could not find the user in session", user);
        assertSame(user, userinSession);
    }
}
