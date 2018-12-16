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
        BrowseServlet.createServlet(BrowseServlet.class);

    }

    @Test
    public void testBrowse() {
        User user = new User(1000L, "John", "Doe", LocalDate.now());
        List list = Collections.singletonList(user);
        getMockUserDAO().expectAndReturn("findAll", list);
        doGet();
        Collection collection = (Collection) getWebMockObjectFactory().getMockSession().getAttribute("users");
        assertNotNull(collection);
        assertSame(user, collection);
    }
}
