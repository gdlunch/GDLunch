package com.labuda.gdlunch.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * Restaurant representing class
 */
@Entity
public class Restaurant {

    /**
     * Database ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
     * URL for the parser, this should contain a link for the page, where the menu is shown
     */
    private String parserUrl;

    /**
     * Daily menus available for the restaurant
     */
    @OneToMany(cascade = CascadeType.ALL)
    private List<DailyMenu> dailyMenus = new ArrayList<>();

    /**
     * Constructor
     */
    public Restaurant() {

    }

    /**
     * Constructor
     *
     * @param name restaurant name
     * @param url restaurant url
     * @param parserUrl url for the parser
     * @param dailyMenus list of daily menus
     */
    public Restaurant(String name, String url, String parserUrl, List<DailyMenu> dailyMenus) {
        this.name = name;
        this.url = url;
        this.parserUrl = parserUrl;
        this.dailyMenus = dailyMenus;
    }

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

    public List<DailyMenu> getDailyMenus() {
        return dailyMenus;
    }

    public void setDailyMenus(List<DailyMenu> dailyMenus) {
        this.dailyMenus = dailyMenus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Restaurant)) {
            return false;
        }
        Restaurant that = (Restaurant) o;
        return Objects.equals(getName(), that.getName()) &&
                Objects.equals(getUrl(), that.getUrl()) &&
                Objects.equals(getParserUrl(), that.getParserUrl()) &&
                Objects.equals(getDailyMenus(), that.getDailyMenus());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getUrl(), getParserUrl(), getDailyMenus());
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", parserUrl='" + parserUrl + '\'' +
                ", dailyMenus='" + dailyMenus + '\'' +
                '}';
    }
}
