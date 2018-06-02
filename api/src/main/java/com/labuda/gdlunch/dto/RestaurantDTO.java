package com.labuda.gdlunch.dto;

import java.util.Objects;

/**
 * Restaurant DTO class
 */
public class RestaurantDTO {

    /**
     * Database ID
     */
    private Long id;

    /**
     * Restaurant name
     */
    private String name;

    /**
     * URL to restaurant web page
     */
    private String url;

    /**
     * URL for the parser, should contain a link with a daily menu
     */
    private String parserUrl;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getParserUrl() {
        return parserUrl;
    }

    public void setParserUrl(String parserUrl) {
        this.parserUrl = parserUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RestaurantDTO)) {
            return false;
        }
        RestaurantDTO that = (RestaurantDTO) o;
        return Objects.equals(getName(), that.getName()) &&
                Objects.equals(getUrl(), that.getUrl()) &&
                Objects.equals(getParserUrl(), that.getParserUrl());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getUrl(), getParserUrl());
    }

    @Override
    public String toString() {
        return "RestaurantDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", parserUrl='" + parserUrl + '\'' +
                '}';
    }
}
