package com.labuda.gdlunch.parser.entity;

public class Restaurant {

    private String name;
    private String url;
    private String parserUrl;

    public Restaurant(String name, String url, String parserUrl) {
        this.name = name;
        this.url = url;
        this.parserUrl = parserUrl;
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
    public String toString() {
        return "Restaurant{" +
                "name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", parserUrl='" + parserUrl + '\'' +
                '}';
    }
}
