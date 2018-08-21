package com.labuda.gdlunch.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
     * List of courses on the menu on specific day
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
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
     * @param menu list of menu items
     */
    public DailyMenu(LocalDate date, List<MenuItem> menu) {
        this.date = date;
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
                Objects.equals(getMenu(), dailyMenu.getMenu());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDate(), getMenu());
    }

    @Override
    public String toString() {
        return "DailyMenu{" +
                "date=" + date +
                ", menu=" + menu +
                '}' + System.lineSeparator();
    }
}
