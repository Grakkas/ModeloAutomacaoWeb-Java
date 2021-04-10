package util;

import org.openqa.selenium.InvalidArgumentException;

import java.io.FileInputStream;
import java.util.Properties;

public class PropertyReader {

    private static Properties getProperties() {
        try {
            Properties properties = new Properties();
            FileInputStream propertiesFile = new FileInputStream("./src/main/resources/config.properties");
            properties.load(propertiesFile);
            return properties;
        } catch (Exception e) {
            throw new InvalidArgumentException("Invalid property file path:");
        }
    }

    public static String getProperty(String property){
        return getProperties().getProperty(property);
    }
}
