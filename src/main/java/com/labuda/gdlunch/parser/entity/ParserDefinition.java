package com.labuda.gdlunch.parser.entity;

import com.labuda.gdlunch.repository.entity.Restaurant;
import java.util.List;

/**
 * Single entry in the parser configuration
 */
public class ParserDefinition {

    /**
     * Full class name of the specific parser (e.g. com.labuda.gdlunch.parser.MagicAIParser)
     */
    private String parserClass;

    /**
     * Contains info about the parser refresh period, currently supported are "daily" and "weekly"
     */
    private String refreshFrequency;

    /**
     * Contains the list of restaurants, that are parsed using this specified parser
     */
    private List<Restaurant> restaurants;

    /**
     * Constructor
     *
     * @param parserClass parser class name (including the package)
     * @param refreshFrequency refresh frequency
     * @param restaurants list of restaurants
     */
    public ParserDefinition(String parserClass, String refreshFrequency,
            List<Restaurant> restaurants) {
        this.parserClass = parserClass;
        this.refreshFrequency = refreshFrequency;
        this.restaurants = restaurants;
    }

    public String getParserClass() {
        return parserClass;
    }

    public void setParserClass(String parserClass) {
        this.parserClass = parserClass;
    }

    public String getRefreshFrequency() {
        return refreshFrequency;
    }

    public void setRefreshFrequency(String refreshFrequency) {
        this.refreshFrequency = refreshFrequency;
    }

    public List<Restaurant> getRestaurants() {
        return restaurants;
    }

    public void setRestaurants(List<Restaurant> restaurants) {
        this.restaurants = restaurants;
    }

    @Override
    public String toString() {
        return "ParserDefinition{" +
                "parserClass='" + parserClass + '\'' +
                ", refreshFrequency='" + refreshFrequency + '\'' +
                ", restaurants=" + restaurants +
                '}';
    }
}
