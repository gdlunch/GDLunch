package com.labuda.gdlunch.parser.entity;

import com.labuda.gdlunch.entity.Restaurant;
import java.util.List;

public class ParserDefinition {

    private String parserClass;
    private String refreshFrequency;
    private List<Restaurant> restaurants;

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
