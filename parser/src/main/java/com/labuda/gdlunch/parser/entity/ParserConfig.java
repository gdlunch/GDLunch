package com.labuda.gdlunch.parser.entity;

import java.util.List;

public class ParserConfig {

    private List<ParserDefinition> config;

    public ParserConfig(List<ParserDefinition> config) {
        this.config = config;
    }

    public List<ParserDefinition> getConfig() {
        return config;
    }

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
