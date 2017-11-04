package com.labuda.gdlunch.parser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Basic web parsing for restaurant web pages
 */
public abstract class AbstractRestaurantWebParser {

    /**
     * Logger
     */
    private final static Logger log = LoggerFactory.getLogger(AbstractRestaurantWebParser.class);

    /**
     * Web address
     */
    protected final String webAddress;

    /**
     * Constructor
     *
     * @param webAddress restaurant web address
     */
    public AbstractRestaurantWebParser(String webAddress) {
        this.webAddress = webAddress;
    }
}
