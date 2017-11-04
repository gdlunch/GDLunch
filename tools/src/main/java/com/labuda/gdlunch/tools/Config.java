package com.labuda.gdlunch.tools;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Configuration class
 */
public abstract class Config {

    /**
     * Logger
     */
    private final static Logger log = LoggerFactory.getLogger(Config.class);

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

    public String getString(String key) {
        return properties.getProperty(key);
    }

    public String getString(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }

}
