package ua.nure.kn16.oleksiienko.usermanagement.web;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;

import org.junit.Before;
import org.junit.Test;

import ua.nure.kn16.oleksiienko.usermanagement.User;

public class EditServletTest extends MockServletTestCase {

    @Before
    public void setUp() throws Exception {
        super.setUp();
        createServlet(EditServlet.class);
    }

    @Test
    public void testEdit() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy", Locale.ENGLISH);

        String dateFormatted = sdf.format(date);

        try {
            date = sdf.parse(dateFormatted);
        } catch (ParseException e1) {
            e1.printStackTrace();
        }

        User user = new User(1000L, "John", "Doe", LocalDate.now());
        getMockUserDAO().expect("update", user);

        addRequestParameter("id", "1000");
        addRequestParameter("firstName", "John");
        addRequestParameter("lastName", "Doe");
        addRequestParameter("date", DateFormat.getDateInstance().format(date));
        addRequestParameter("okButton", "Ok");
        doPost();
    }

}
