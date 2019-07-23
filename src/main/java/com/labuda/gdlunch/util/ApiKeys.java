package com.labuda.gdlunch.util;

import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Configuration of API keys for various services
 */
public class ApiKeys extends PropertiesConfig {

    /**
     * Logger
     */
    private final static Logger log = LoggerFactory.getLogger(ApiKeys.class);

    /**
     * Singleton instance
     */
    private final static PropertiesConfig INSTANCE = new ApiKeys();

    /**
     * Constructor
     */
    private ApiKeys() {
        super();
        try {
            load("apiKeys.properties");
        } catch (IOException e) {
            log.error("ApiKeys could not be initialized correctly", e);
            System.exit(-1);
        }
    }

    /**
     * Getter for the config
     *
     * @return instance of the config
     */
    public static PropertiesConfig getInstance() {
        return INSTANCE;
    }
}
