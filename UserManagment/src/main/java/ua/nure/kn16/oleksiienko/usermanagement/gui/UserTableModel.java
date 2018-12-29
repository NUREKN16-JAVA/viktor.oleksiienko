package ua.nure.kn16.oleksiienko.usermanagement.gui;

import ua.nure.kn16.oleksiienko.usermanagement.User;
import ua.nure.kn16.oleksiienko.usermanagement.util.Message;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserTableModel extends AbstractTableModel{
    private static  final String[] COLUMN_NAMES = {Message.getString("idTable"),
            Message.getString("nameTable"),
            Message.getString("surnameTable")};
    private static final Class[] COLUMN_CLASSES = {Long.class, String.class, String.class};
    private List users;

    public UserTableModel (Collection users) {
        this.users = new ArrayList(users);
    }

    public void addUsers(Collection<User> users) {
        this.users.addAll(users);
    }

    public void clearUsers() {
        this.users = new ArrayList<>();
    }

    @Override
    public int getRowCount() {
        return users.size();
    }

    @Override
    public int getColumnCount() {
        return COLUMN_NAMES.length;
    }

    public Class getColumnClass(int column) {
        return COLUMN_CLASSES[column];
    }

    public String getColumnName(int column) {
        return COLUMN_NAMES[column];
    }

    @Override
    public Object getValueAt(int row, int column) {
        User user = (User) users.get(row);

        if (column == 0) {
            return user.getId();
        } else if (column == 1) {
            return user.getFirstName();
        } else if (column == 2) {
            return user.getLastName();
        }

        return null;
    }
}
