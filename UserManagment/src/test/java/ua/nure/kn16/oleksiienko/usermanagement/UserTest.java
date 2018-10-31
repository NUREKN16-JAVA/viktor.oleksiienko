package ua.nure.kn16.oleksiienko.usermanagement;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class UserTest {
    private  User user;
    private static final String FIRSTNAME = "Пётр";
    private static final String LASTNAME = "Эдуардович";

    @Before
    public void setUp() throws Exception {
        user = new User(FIRSTNAME, LASTNAME, null);
    }

    @Test
    public void testGetFullName() {
        assertEquals("Эдуардович, Пётр", user.getFullName());
    }

    @Test
    public void testGetAgeBefore() {
        user.setDateOfBirth(LocalDate.of(2001, 1, 2));
        assertEquals(17, user.getAge());
    }

    @Test
    public void testGetAgeAfter() {
        user.setDateOfBirth(LocalDate.of(2001, 12, 30));
        assertEquals(16, user.getAge());
    }

    @Test
    public void testGetAgeSame() {
        user.setDateOfBirth(LocalDate.now().withYear(2007).withDayOfMonth(20));
        assertEquals(11, user.getAge());
    }

    @Test
    public void testGetAgeSameMonthBefore() {
        user.setDateOfBirth(LocalDate.now().withYear(2007).withDayOfMonth(11));
        assertEquals(11, user.getAge());
    }

    @Test
    public void testGetAgeSameMonthAfter() {
        user.setDateOfBirth(LocalDate.now().withYear(2007).withDayOfMonth(28));
        assertEquals(10, user.getAge());
    }
}
