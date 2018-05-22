package com.labuda.gdlunch.tools;

import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Configuration of web addresses for restaurant pages
 */
public class WebAddressesConfig extends PropertiesConfig {

    /**
     * Logger
     */
    private final static Logger log = LoggerFactory.getLogger(WebAddressesConfig.class);

    /**
     * Singleton instance
     */
    private final static PropertiesConfig INSTANCE = new WebAddressesConfig();

    private WebAddressesConfig() {
        super();
        try {
            load("urls.properties");
        } catch (IOException e) {
            log.error("WebAddressesConfig could not be initialized correctly.", e);
            System.exit(-1);
        }
    }

    public static PropertiesConfig getInstance() {
        return INSTANCE;
    }
}
