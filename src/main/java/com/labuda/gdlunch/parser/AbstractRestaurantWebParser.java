package com.labuda.gdlunch.parser;

import com.labuda.gdlunch.repository.entity.Restaurant;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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

    /**
     * Parses the price from the first number in the String
     *
     * @param priceString string containing the price
     * @return -1 in case of error, 0 if no price was present, otherwise the parsed price
     */
    protected Float parsePrice(String priceString) {
        if (priceString == null || priceString.isEmpty()) {
            return 0f;
        }
        priceString = priceString.trim();
        Pattern numberPattern = Pattern.compile("([0-9]+)");
        Matcher numberMatcher = numberPattern.matcher(priceString);

        // If some number was found in the string
        if (numberMatcher.find()) {
            // Set the price to -1 so that we can identify parsing problem on the UI
            float price = -1f;
            try {
                // Parse the first match
                price = Float.parseFloat(numberMatcher.group());
            } catch (Exception e) {
                log.error("Couldn't parse the price correctly from = " + numberMatcher.group(), e);
            }
            return price;
        } else {
            return 0f;
        }
    }
}
