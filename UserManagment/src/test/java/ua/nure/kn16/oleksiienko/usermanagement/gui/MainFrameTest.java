package ua.nure.kn16.oleksiienko.usermanagement.gui;

import com.mockobjects.dynamic.Mock;
import junit.extensions.jfcunit.JFCTestCase;
import junit.extensions.jfcunit.JFCTestHelper;
import junit.extensions.jfcunit.TestHelper;
import junit.extensions.jfcunit.eventdata.MouseEventData;
import junit.extensions.jfcunit.eventdata.StringEventData;
import junit.extensions.jfcunit.finder.NamedComponentFinder;
import org.junit.Test;
import ua.nure.kn16.oleksiienko.usermanagement.User;
import ua.nure.kn16.oleksiienko.usermanagement.db.DAOFactory;
import ua.nure.kn16.oleksiienko.usermanagement.db.DAOFactoryImplement;
import ua.nure.kn16.oleksiienko.usermanagement.db.MockDaoFactory;
import ua.nure.kn16.oleksiienko.usermanagement.util.Message;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Properties;

import static org.junit.Assert.*;

public class MainFrameTest extends JFCTestCase {
    private MainFrame mainFrame;
    private Mock mockUserDAO;

    public void setUp() throws Exception {
        super.setUp();
        try {
            Properties properties = new Properties();
            properties.setProperty("dao.factory", MockDaoFactory.class.getName());

            DAOFactory.init(properties);

            mockUserDAO = ((MockDaoFactory) DAOFactory.getInstance()).getMockUserDao();
            mockUserDAO.expectAndReturn("findAll", new ArrayList<>());

            mainFrame = new MainFrame();

            setHelper(new JFCTestHelper());
        } catch (Exception e) {
            e.printStackTrace();
        }
        mainFrame.setVisible(true);
    }

    public void tearDown() throws Exception {
        try {
            mockUserDAO.verify();
            mainFrame.setVisible(false);
            TestHelper.cleanUp(this);
            super.tearDown();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Component find(Class componentElem, String name) {
        NamedComponentFinder finder = new NamedComponentFinder(componentElem, name);
        finder.setWait(0);

        Component component = finder.find(mainFrame,0);

        assertNotNull("Could not find element " + name + ",", component);

        return component;
    }

    @Test
    public void testBrowseControls() {
        find(JButton.class, "detailsButton");
        find(JButton.class, "deleteButton");
        find(JPanel.class, "browserPanel");
        find(JButton.class, "editButton");
        find(JButton.class, "addButton");

        JTable table = (JTable) find(JTable.class, "userTable");
        int expectedColumn = 3;

        String expectedFirstColumn = Message.getString("idTable");
        String expectedSecondColumn = Message.getString("nameTable");
        String expectedThirdColumn = Message.getString("surnameTable");

        assertEquals(expectedColumn, table.getColumnCount());
        assertEquals(expectedFirstColumn, table.getColumnName(0));
        assertEquals(expectedSecondColumn, table.getColumnName(1));
        assertEquals(expectedThirdColumn, table.getColumnName(2));
    }

    @Test
    public void testAddUser() {
        String firstName = "Sos",
               lastName = "Tupid";
        LocalDate dateOfBirth = LocalDate.now();

        User user = new User(firstName, lastName, dateOfBirth);
        User expectedUser = new User(1L, firstName, lastName, dateOfBirth);

        mockUserDAO.expectAndReturn("create", user, expectedUser);

        ArrayList<User> userList = new ArrayList<>();
        userList.add(expectedUser);
        mockUserDAO.expectAndReturn("findAll", userList);

        JButton addButton = (JButton) find(JButton.class, "addButton");
        MouseEventData mouseEventData = new MouseEventData(this, addButton);
        getHelper().enterClickAndLeave(mouseEventData);
        find(JPanel.class, "addPanel");

        JTable table = (JTable) find(JTable.class, "userTable");
        assertEquals(0, table.getRowCount());

        JTextField firstNameField = (JTextField) find(JTextField.class, "firstNameField");
        JTextField dateOfBirthField = (JTextField) find(JTextField.class, "dateOfBirthField");
        JTextField lastNameField = (JTextField) find(JTextField.class, "lastNameField");
        JButton okButton = (JButton) find(JButton.class, "okButton");
        find(JButton.class, "cancelButton");

        getHelper().sendString(new StringEventData(this, firstNameField, firstName));
        getHelper().sendString(new StringEventData(this, lastNameField, lastName));
        getHelper().sendString(new StringEventData(this, dateOfBirthField, String.valueOf(dateOfBirth)));

        getHelper().enterClickAndLeave(new MouseEventData(this, okButton));

        find(JPanel.class, "browserPanel");
        table = (JTable) find(JTable.class, "userTable");
        assertEquals(1, table.getRowCount());

    }

}
