package gui;

import java.util.Locale;
import java.util.PropertyResourceBundle;

public class GuiResource
{
    private static final String RESOURCES = "gui.ContactResources";

    private static PropertyResourceBundle components = null;

    public static void initComponentResources() {
        initComponentResources(null);
    }

    public static void initComponentResources(String keyLocale) {
        components = (PropertyResourceBundle) PropertyResourceBundle.getBundle(RESOURCES, getLocale(keyLocale));
    }

    public static String getLabel(String formId, String componentId) {
        return components.getString(formId + "." + componentId);
    }

    private static Locale getLocale(String key){
        if (key != null && !key.trim().isEmpty()){
            String lang = key.replace("-", "");
            if (lang.equalsIgnoreCase("en") || lang.equalsIgnoreCase("ru")){
                return new Locale(lang);
            }
            return new Locale("en");
        }
        return new Locale("en");
    }
}
