package by.training.demo.entity.manager;

import java.util.Locale;
import java.util.ResourceBundle;

public enum MessageManager {
    EN(ResourceBundle.getBundle("lang.text", new Locale("en", "US"))),
    RU(ResourceBundle.getBundle("lang.text", new Locale("ru", "RU"))),
    BY(ResourceBundle.getBundle("lang.text", new Locale("be", "BY")));
    private ResourceBundle bundle;
    MessageManager(ResourceBundle bundle) {
        this.bundle = bundle;
    }
    public String getString(String key) {
        return bundle.getString(key);
    }
}
