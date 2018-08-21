package com.labuda.gdlunch.dto;

/**
 * DTO for restaurant with current daily menu
 */
public class RestaurantWithCurrentMenuDTO {

    /**
     * Restaurant name
     */
    private String name;

    /**
     * Restaurant visitor page url, not the one for parsing
     */
    private String url;

    /**
     * Current daily menu
     */
    private DailyMenuDTO dailyMenu;

    /**
     * Parameter-less constructor, used for Spring reflection
     */
    public RestaurantWithCurrentMenuDTO() {
    }

    /**
     * Constructor
     *
     * @param name restaurant name
     * @param url restaurant url
     * @param dailyMenu single daily menu for the current day
     */
    public RestaurantWithCurrentMenuDTO(String name, String url, DailyMenuDTO dailyMenu) {
        this.name = name;
        this.url = url;
        this.dailyMenu = dailyMenu;
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

    public DailyMenuDTO getDailyMenu() {
        return dailyMenu;
    }

    public void setDailyMenu(DailyMenuDTO dailyMenu) {
        this.dailyMenu = dailyMenu;
    }

    @Override
    public String toString() {
        return "RestaurantWithCurrentMenuDTO{" +
                "name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", dailyMenu=" + dailyMenu +
                '}';
    }
}
