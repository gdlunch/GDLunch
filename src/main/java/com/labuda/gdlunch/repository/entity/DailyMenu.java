package com.labuda.gdlunch.repository.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * This class represents menu for a single day
 */
@Entity
@Table(name = "dailyMenu")
public class DailyMenu {

    /**
     * Database ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Date
     */
    private LocalDate date;

    /**
     * Restaurant
     */
    @OneToOne(targetEntity = Restaurant.class, cascade = CascadeType.ALL)
    private Restaurant restaurant;

    /**
     * List of courses on the menu on specific day
     */
    @OneToMany(cascade = CascadeType.ALL)
    private List<MenuItem> menu = new ArrayList<>();

    /**
     * Constructor
     */
    public DailyMenu() {

    }

    /**
     * Constructor
     *
     * @param date daily menu date
     * @param restaurant restaurant details
     * @param menu list of menu items
     */
    public DailyMenu(LocalDate date, Restaurant restaurant, List<MenuItem> menu) {
        this.date = date;
        this.restaurant = restaurant;
        this.menu = menu;
    }

    public Long getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public List<MenuItem> getMenu() {
        return menu;
    }

    public void setMenu(List<MenuItem> menu) {
        this.menu = menu;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DailyMenu)) {
            return false;
        }
        DailyMenu dailyMenu = (DailyMenu) o;
        return Objects.equals(getDate(), dailyMenu.getDate()) &&
                Objects.equals(getRestaurant(), dailyMenu.getRestaurant()) &&
                Objects.equals(getMenu(), dailyMenu.getMenu());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDate(), getRestaurant(), getMenu());
    }

    @Override
    public String toString() {
        return "DailyMenu{" +
                "date=" + date +
                ", restaurant=" + restaurant +
                ", menu=" + menu +
                '}' + System.lineSeparator();
    }
}
