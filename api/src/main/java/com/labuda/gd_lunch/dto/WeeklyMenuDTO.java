package com.labuda.gd_lunch.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * Weekly Menu DTO class
 */
public class WeeklyMenuDTO {

    /**
     * Database ID
     */
    private Long id;

    /**
     * Restaurant name
     */
    private String restaurantName;

    /**
     * Menu
     */
    private List<DailyMenuDTO> menu = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public List<DailyMenuDTO> getMenu() {
        return menu;
    }

    public void setMenu(List<DailyMenuDTO> menu) {
        this.menu = menu;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WeeklyMenuDTO)) return false;

        WeeklyMenuDTO that = (WeeklyMenuDTO) o;

        if (getRestaurantName() != null ? !getRestaurantName().equals(that.getRestaurantName()) : that.getRestaurantName() != null)
            return false;
        return getMenu() != null ? getMenu().equals(that.getMenu()) : that.getMenu() == null;
    }

    @Override
    public int hashCode() {
        int result = getRestaurantName() != null ? getRestaurantName().hashCode() : 0;
        result = 31 * result + (getMenu() != null ? getMenu().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "WeeklyMenuDTO{" +
                "id=" + id +
                ", restaurantName='" + restaurantName + '\'' +
                ", menu=" + menu +
                '}';
    }
}
