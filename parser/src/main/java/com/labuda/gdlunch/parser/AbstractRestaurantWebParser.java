package com.labuda.gdlunch.parser;

import com.labuda.gdlunch.parser.entity.Restaurant;
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
     * Restaurant
     */
    protected final Restaurant restaurant;

    /**
     * Constructor
     *
     * @param restaurant restaurant
     */
    public AbstractRestaurantWebParser(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
}
