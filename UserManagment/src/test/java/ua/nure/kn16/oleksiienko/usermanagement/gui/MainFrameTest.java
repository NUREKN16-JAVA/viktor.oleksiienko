package ua.nure.kn16.oleksiienko.usermanagement.gui;

import junit.extensions.jfcunit.JFCTestCase;
import junit.extensions.jfcunit.JFCTestHelper;
import junit.extensions.jfcunit.TestHelper;
import junit.extensions.jfcunit.eventdata.MouseEventData;
import junit.extensions.jfcunit.finder.NamedComponentFinder;
import org.junit.Test;

import javax.swing.*;
import java.awt.*;

import static org.junit.Assert.*;

public class MainFrameTest extends JFCTestCase {
    private MainFrame mainFrame;


    public void setUp() throws Exception {
        super.setUp();
        mainFrame = new MainFrame();
        setHelper(new JFCTestHelper());
        mainFrame.setVisible(true);
    }

    public void tearDown() throws Exception {
        try {
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

    }

    @Test
    public void testAddUser() {

        JButton addButton = (JButton) find(JButton.class, "addButton");
        MouseEventData mouseEventData = new MouseEventData(this, addButton);
        getHelper().enterClickAndLeave(mouseEventData);
        find(JPanel.class, "addPanel");

        JTextField firstNameField = (JTextField) find(JTextField.class, "firstNameField");
        JTextField dateOfBirthField = (JTextField) find(JTextField.class, "dateOfBirthField");
        JTextField lastNameField = (JTextField) find(JTextField.class, "lastNameField");
        JButton okButton = (JButton) find(JButton.class, "okButton");
        find(JButton.class, "cancelButton");

    }

}
