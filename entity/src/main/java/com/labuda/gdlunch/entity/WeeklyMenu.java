package com.labuda.gdlunch.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Contains menu for the whole week
 */
@Entity
@Table(name = "weekly_menu")
public class WeeklyMenu {

    /**
     * Database ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Restaurant name
     */
    private String restaurantName;

    /**
     * Menu
     */
    @OneToMany(cascade = CascadeType.ALL)
    private List<DailyMenu> menu = new ArrayList<>();

    /**
     * Constructor
     */
    public WeeklyMenu() {

    }

    /**
     * Constructor
     *
     * @param restaurantName name of the restaurant
     */
    public WeeklyMenu(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public Long getId() {
        return id;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public List<DailyMenu> getMenu() {
        return menu;
    }

    public void setMenu(List<DailyMenu> menu) {
        this.menu = menu;
    }

    public List<DailyMenu> getDailyMenu() {
        return menu;
    }

    public void addDailyMenu(DailyMenu dailyMenu) {
        menu.add(dailyMenu);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof WeeklyMenu)) {
            return false;
        }

        WeeklyMenu that = (WeeklyMenu) o;

        if (restaurantName != null ? !restaurantName.equals(that.restaurantName)
                : that.restaurantName != null) {
            return false;
        }
        return menu != null ? menu.equals(that.menu) : that.menu == null;
    }

    @Override
    public int hashCode() {
        int result = restaurantName != null ? restaurantName.hashCode() : 0;
        result = 31 * result + (menu != null ? menu.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "WeeklyMenu{" +
                "menu=" + menu +
                '}';
    }
}
