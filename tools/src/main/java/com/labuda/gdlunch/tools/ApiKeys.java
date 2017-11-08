package com.labuda.gdlunch.tools;

import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Configuration of API keys for various services
 */
public class ApiKeys extends Config {

    /**
     * Logger
     */
    private final static Logger log = LoggerFactory.getLogger(ApiKeys.class);

    /**
     * Singleton instance
     */
    private final static Config INSTANCE = new ApiKeys();

    private ApiKeys() {
        super();
        try {
            load("apiKeys.properties");
        } catch (IOException e) {
            log.error("ApiKeys could not be initialized correctly", e);
            System.exit(-1);
        }
    }

    public static Config getInstance() {
        return INSTANCE;
    }
}
