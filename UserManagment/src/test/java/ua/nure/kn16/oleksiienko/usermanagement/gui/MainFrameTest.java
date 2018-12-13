package ua.nure.kn16.oleksiienko.usermanagement.gui;

import junit.extensions.jfcunit.JFCTestCase;
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

        mainFrame.setVisible(true);
    }

    public void tearDown() throws Exception {
        try {
     //       mockUserDAO.verify();
            mainFrame.setVisible(false);
       //     TestHelper.cleanUp(this);
            getHelper().cleanUp(this);
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

}
