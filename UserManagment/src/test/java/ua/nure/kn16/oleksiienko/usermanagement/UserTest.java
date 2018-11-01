package ua.nure.kn16.oleksiienko.usermanagement;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class UserTest {
    private  User user;
    private static final String FIRSTNAME = "Пётр";
    private static final String LASTNAME = "Эдуардович";

    // Set up new user for tests
    @Before
    public void setUp() throws Exception {
        user = new User(FIRSTNAME, LASTNAME, null);
    }

    // Test method getFullName for correct output format
    @Test
    public void testGetFullName() {
        assertEquals("Эдуардович, Пётр", user.getFullName());
    }

    // Test method getAge when birth date is before any date
    @Test
    public void testGetAgeBefore() {
        user.setDateOfBirth(LocalDate.of(2001, 1, 1));
        assertEquals(17, user.getAge());
    }

    // Test method getAge when birth date is after any date
    @Test
    public void testGetAgeAfter() {
        user.setDateOfBirth(LocalDate.of(2001, 12, 31));
        assertEquals(16, user.getAge());
    }

    // Test method getAge when birth date is the same with current date
    @Test
    public void testGetAgeSame() {
        user.setDateOfBirth(LocalDate.now().withYear(2007).withDayOfMonth(20));
        assertEquals(11, user.getAge());
    }

    // Test method getAge when birth date have same month but day is before birth day
    @Test
    public void testGetAgeSameMonthBefore() {
        user.setDateOfBirth(LocalDate.now().withYear(2007).withDayOfMonth(11));
        assertEquals(11, user.getAge());
    }

    // Test method getAge when birth date have same month but day is after birth day
    @Test
    public void testGetAgeSameMonthAfter() {
        user.setDateOfBirth(LocalDate.now().withYear(2007).withDayOfMonth(28));
        assertEquals(10, user.getAge());
    }
}
