package com.labuda.gdlunch.tools;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class WebAddressesConfig extends Config {

    /**
     * Logger
     */
    private final static Logger log = LoggerFactory.getLogger(WebAddressesConfig.class);

    /**
     * Singleton instance
     */
    private final static Config INSTANCE = new WebAddressesConfig();

    private WebAddressesConfig() {
        super();
        try {
            load("urls.properties");
        } catch (IOException e) {
            log.error("WebAddressesConfig could not be initialized correctly.", e);
            System.exit(-1);
        }
    }

    public static Config getInstance() {
        return INSTANCE;
    }
}
