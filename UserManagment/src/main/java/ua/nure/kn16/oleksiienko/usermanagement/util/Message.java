package ua.nure.kn16.oleksiienko.usermanagement.util;

import java.util.ResourceBundle;

public class Message {
    private static final String BUNDLE_NAME = "titles";
    private  static final ResourceBundle resourceBundle = ResourceBundle.getBundle(BUNDLE_NAME);

    private Message() {}

    public static String getString(String element) {
        return resourceBundle.getString(element);
    }
}
