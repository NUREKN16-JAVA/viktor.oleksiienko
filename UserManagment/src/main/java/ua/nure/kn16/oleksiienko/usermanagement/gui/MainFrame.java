package ua.nure.kn16.oleksiienko.usermanagement.gui;

import ua.nure.kn16.oleksiienko.usermanagement.db.DAOFactory;
import ua.nure.kn16.oleksiienko.usermanagement.db.UserDAO;
import ua.nure.kn16.oleksiienko.usermanagement.util.Message;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private final int FRAME_WIDTH = 700;
    private final int FRAME_HEIGHT = 600;
    private JPanel contentPanel;
    private JPanel browsePanel;
    private JPanel addPanel;
    private UserDAO dao;

    public MainFrame() {
        try {
            dao = DAOFactory.getInstance().getUserDAO();
        } catch (ReflectiveOperationException e) {
            e.printStackTrace();
        }
        init();
    }

    private void init() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        this.setTitle(Message.getString("userManagementTitle"));
        this.setContentPane(getContentPanel());
    }

    public UserDAO getDAO() { return dao; }

    private JPanel getContentPanel() {
        if (contentPanel == null) {
            contentPanel = new JPanel();
            contentPanel.setLayout(new BorderLayout());
            contentPanel.add(getBrowsePanel(), BorderLayout.CENTER);

        }
        return contentPanel;
    }

    private JPanel getBrowsePanel() {
        if (browsePanel == null) {
            browsePanel = new BrowsePanel(this);
        }
        ((BrowsePanel) browsePanel).initTable();

        return browsePanel;
    }


    public static void main(String[] args) {
        MainFrame frame = new MainFrame();
        frame.setVisible(true);
    }

    public void showAddPanel() {
        showPanel(getAddPanel());
    }

    public void showBrowsePanel() { showPanel(getBrowsePanel()); }

    private JPanel getAddPanel() {
        if (addPanel == null) {
            addPanel = new AddPanel(this);
        }

        return addPanel;
    }

    private void showPanel(JPanel panel) {
        getContentPane().add(panel, BorderLayout.CENTER);
        panel.setVisible(true);
        panel.repaint();
    }
}
