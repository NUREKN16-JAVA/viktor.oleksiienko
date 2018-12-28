package ua.nure.kn16.oleksiienko.usermanagement.web;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Locale;

import org.junit.Before;
import org.junit.Test;

import ua.nure.kn16.oleksiienko.usermanagement.User;

public class AddServletTest extends MockServletTestCase {

    @Before
    public void setUp() throws Exception {
        super.setUp();
        createServlet(AddServlet.class);
    }

    @Test
    public void testAdd() {
        LocalDate date = LocalDate.now();
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy", Locale.ENGLISH);

        String dateFormatted = sdf.format(date);

        try {
            date = sdf.parse(dateFormatted).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        } catch (ParseException e1) {
            e1.printStackTrace();
        }

        User newUser = new User("John", "Doe", date);
        User user = new User(1000L, "John", "Doe", date);
        getMockUserDAO().expectAndReturn("create", newUser, user);

        addRequestParameter("firstName", "John");
        addRequestParameter("lastName", "Doe");
        addRequestParameter("date", dateFormatted);
        addRequestParameter("okButton", "Ok");
        doPost();
    }

    @Test
    public void testAddEmptyFirstName() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy");

        String dateFormatted = sdf.format(date);

        try {
            date = sdf.parse(dateFormatted);
        } catch (ParseException e1) {
            e1.printStackTrace();
        }

        addRequestParameter("lastName", "Doe");
        addRequestParameter("date", DateFormat.getDateInstance().format(date));
        addRequestParameter("okButton", "Ok");
        doPost();
        String errorMessage = (String) getWebMockObjectFactory().getMockRequest().getAttribute("error");
        assertNotNull("Could not find error message", errorMessage);
    }

    @Test
    public void testAddEmptyLastName() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy");

        String dateFormatted = sdf.format(date);

        try {
            date = sdf.parse(dateFormatted);
        } catch (ParseException e1) {
            e1.printStackTrace();
        }

        addRequestParameter("firstName", "John");
        addRequestParameter("date", DateFormat.getDateInstance().format(date));
        addRequestParameter("okButton", "Ok");
        doPost();
        String errorMessage = (String) getWebMockObjectFactory().getMockRequest().getAttribute("error");
        assertNotNull("Could not find error message", errorMessage);
    }

    @Test
    public void testAddEmptyDate() {
        addRequestParameter("firstName", "John");
        addRequestParameter("lastName", "Doe");
        addRequestParameter("okButton", "Ok");
        doPost();
        String errorMessage = (String) getWebMockObjectFactory().getMockRequest().getAttribute("error");
        assertNotNull("Could not find error message", errorMessage);
    }

    @Test
    public void testAddDateIncorrect() {
        addRequestParameter("firstName", "John");
        addRequestParameter("lastName", "Doe");
        addRequestParameter("date", "aksdjwqi");
        addRequestParameter("okButton", "Ok");
        doPost();
        String errorMessage = (String) getWebMockObjectFactory().getMockRequest().getAttribute("error");
        assertNotNull("Could not find error message", errorMessage);
    }
}