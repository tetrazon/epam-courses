package by.training.carservicebook.util;

import java.util.Locale;
import java.util.ResourceBundle;

public class ResourceBundleCreator {
    public static ResourceBundle createResourceBundle(String language, String baseName){
        Locale locale = new Locale(language);
        return  ResourceBundle.getBundle(baseName, locale);
    }
}
