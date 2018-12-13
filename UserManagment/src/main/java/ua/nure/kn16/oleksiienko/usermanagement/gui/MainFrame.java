package ua.nure.kn16.oleksiienko.usermanagement.gui;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private final int FRAME_WIDTH = 700;
    private final int FRAME_HEIGHT = 700;
    private JPanel contentPanel;
    private JPanel browsePanel;



    public MainFrame() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        this.setTitle("Управление пользователями");
        this.setContentPane(getContentPanel());
    }

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
//        ((BrowsePanel)browsePanel).initTable();

        return browsePanel;
    }


    public static void main(String[] args) {
        MainFrame frame = new MainFrame();
        frame.setVisible(true);


    }
}
