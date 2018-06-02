package com.labuda.gdlunch.tools;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Configuration class
 */
public abstract class PropertiesConfig {

    /**
     * Properties
     */
    private static Properties properties = new Properties();

    /**
     * Loads the properties from file
     *
     * @param filePath property filepath
     * @throws IOException when property file opening has failed
     */
    protected void load(String filePath) throws IOException {
        properties.load(new FileInputStream(filePath));
    }

    /**
     * Gets a simple String from configuration
     *
     * @param key property name
     * @return found value, null otherwise
     */
    public String getString(String key) {
        return properties.getProperty(key);
    }

    /**
     * Gets a simple String from configuration
     *
     * @param key property name
     * @param defaultValue default value
     * @return found value, default value otherwise
     */
    public String getString(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }

}
