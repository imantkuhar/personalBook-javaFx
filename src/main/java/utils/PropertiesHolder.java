package utils;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by Imant on 16.11.16.
 */
public class PropertiesHolder {

    private static final String APP_PROPERTIES_PATH = "/resources/constants.properties";
    private static final Properties PROPERTIES = load();

    private static Properties load() {
        Properties properties = new Properties();
        try {
            properties.load(Properties.class.getResourceAsStream(APP_PROPERTIES_PATH));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }

    public static String getProperty(String key) {
        return PROPERTIES.getProperty(key);
    }
}

