package com.labuda.gdlunch.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Daily Menu DTO class
 */
public class DailyMenuDTO {

    /**
     * Database ID
     */
    private Long id;

    /**
     * Date
     */
    private LocalDate date;

    /**
     * Restaurant name
     */
    private String restaurantName;

    /**
     * List of courses on the menu on specific day
     */
    private List<MenuItemDTO> menu = new ArrayList<>();

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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<MenuItemDTO> getMenu() {
        return menu;
    }

    public void setMenu(List<MenuItemDTO> menu) {
        this.menu = menu;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DailyMenuDTO)) {
            return false;
        }

        DailyMenuDTO that = (DailyMenuDTO) o;

        if (getDate() != null ? !getDate().equals(that.getDate()) : that.getDate() != null) {
            return false;
        }
        return getMenu() != null ? getMenu().equals(that.getMenu()) : that.getMenu() == null;
    }

    @Override
    public int hashCode() {
        int result = getDate() != null ? getDate().hashCode() : 0;
        result = 31 * result + (getMenu() != null ? getMenu().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "DailyMenuDTO{" +
                "id=" + id +
                ", date=" + date +
                ", menu=" + menu +
                '}';
    }
}
