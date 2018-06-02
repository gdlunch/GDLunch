package com.labuda.gdlunch.parser.entity;

import java.util.List;

/**
 * Parser configuration
 *
 * Contains all the parser definitions in the "restaurants.json"
 */
public class ParserConfig {

    /**
     * List of parser definitions
     */
    private List<ParserDefinition> config;

    /**
     * Constructor
     *
     * @param config list of parser definitions
     */
    public ParserConfig(List<ParserDefinition> config) {
        this.config = config;
    }

    /**
     * Getter for configuration
     *
     * @return list of parser definitions
     */
    public List<ParserDefinition> getConfig() {
        return config;
    }

    /**
     * Setter for configuration
     *
     * @param config list of parser definitions
     */
    public void setConfig(List<ParserDefinition> config) {
        this.config = config;
    }

    @Override
    public String toString() {
        return "ParserConfig{" +
                "config=" + config +
                '}';
    }
}
