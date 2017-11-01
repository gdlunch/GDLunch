package com.labuda.gd_lunch;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents menu for a single day
 */
public class DailyMenu {

    /** Date */
    private LocalDate date;

    /** List of courses on the menu on specific day */
    private List<MenuItem> menu = new ArrayList<>();

    /**
     * Constructor
     */
    public DailyMenu() {

    }

    /**
     * Constructor
     * @param date date
     * @param menu menu
     */
    public DailyMenu(LocalDate date, List<MenuItem> menu) {
        this.date = date;
        this.menu = menu;
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
        if (this == o) return true;
        if (!(o instanceof DailyMenu)) return false;

        DailyMenu dailyMenu = (DailyMenu) o;

        if (getDate() != null ? !getDate().equals(dailyMenu.getDate()) : dailyMenu.getDate() != null) return false;
        return getMenu() != null ? getMenu().equals(dailyMenu.getMenu()) : dailyMenu.getMenu() == null;
    }

    @Override
    public int hashCode() {
        int result = getDate() != null ? getDate().hashCode() : 0;
        result = 31 * result + (getMenu() != null ? getMenu().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "DailyMenu{" +
                "date=" + date +
                ", menu=" + menu +
                '}' + System.lineSeparator();
    }
}
