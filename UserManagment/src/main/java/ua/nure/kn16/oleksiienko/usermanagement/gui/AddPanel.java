package ua.nure.kn16.oleksiienko.usermanagement.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import ua.nure.kn16.oleksiienko.usermanagement.util.Message;

public class AddPanel extends JPanel implements ActionListener {
    private static final int FRAME_WIDTH = 500;
    private static final int FRAME_HEIGHT = 400;
    private MainFrame parent;
    private JButton okButton;
    private JButton cancelButton;
    private JPanel buttonPanel;
    private JPanel fieldPanel;
    private JTextField dateOfBirth;
    private JTextField lastNameField;
    private JTextField firstNameField;

    AddPanel(MainFrame mainFrame) {
        parent = mainFrame;
        this.initialize();
    }

    private void initialize() {
        this.setName("addPanel");
        this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        this.setLayout(new BorderLayout());
        this.add(getFieldPanel(), BorderLayout.NORTH);
        this.add(getButtonPanel(), BorderLayout.SOUTH);
    }

    private JPanel getButtonPanel() {
        if (buttonPanel == null) {
            buttonPanel = new JPanel();
            buttonPanel.add(getOkButton(), null);
            buttonPanel.add(getCancelButton(), null);
        }

        return buttonPanel;
    }

    private JButton getOkButton() {
        if (okButton == null){
            okButton = new JButton();
            okButton.setText(Message.getString("okButton"));
            okButton.setName("okButton");
            okButton.setActionCommand("ok");
            okButton.addActionListener(this);
        }

        return okButton;
    }

    private JButton getCancelButton() {
        if (cancelButton == null){
            cancelButton = new JButton();
            cancelButton.setText(Message.getString("cancelButton"));
            cancelButton.setName("cancelButton");
            cancelButton.setActionCommand("cancelBut");
            cancelButton.addActionListener(this);
        }

        return cancelButton;
    }

    private JPanel getFieldPanel() {
        if (fieldPanel == null) {
            fieldPanel = new JPanel();
            fieldPanel.setLayout(new GridLayout(3, 2));
            addLabelField(fieldPanel, Message.getString("nameLabel"), getFirstNameField());
            addLabelField(fieldPanel, Message.getString("surnameLabel"), getLastNameField());
            addLabelField(fieldPanel, Message.getString("dateTitleLabel"), getDateOfBirthField());
        }

        return fieldPanel;
    }

    private void addLabelField(JPanel fieldPanel, String labelTxt, JTextField textField) {
        JLabel label = new JLabel(labelTxt);
        label.setLabelFor(textField);
        fieldPanel.add(label);
        fieldPanel.add(textField);
    }

    private JTextField getDateOfBirthField() {
        if (dateOfBirth == null) {
            dateOfBirth = new JTextField();
            dateOfBirth.setName("dateOfBirthField");
        }

        return dateOfBirth;
    }

    private JTextField getLastNameField() {
        if (lastNameField == null) {
            lastNameField = new JTextField();
            lastNameField.setName("lastNameField");
        }

        return lastNameField;
    }

    private JTextField getFirstNameField() {
        if (firstNameField == null) {
            firstNameField = new JTextField();
            firstNameField.setName("firstNameField");
        }

        return firstNameField;
    }



    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String action = actionEvent.getActionCommand();
        if (action.equalsIgnoreCase("cancelBut")) {

        }
        this.setVisible(false);
        parent.showBrowsePanel();
    }
}
