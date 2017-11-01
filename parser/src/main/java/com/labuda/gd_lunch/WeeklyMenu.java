package com.labuda.gd_lunch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Contains menu for the whole week
 */
public class WeeklyMenu {

    /**
     * Logger
     */
    private final static Logger log = LoggerFactory.getLogger(WeeklyMenu.class);

    /**
     * Menu
     */
    private final List<DailyMenu> menu = new ArrayList<>();

    /**
     * Constructor
     */
    public WeeklyMenu() {

    }

    public List<DailyMenu> getMenuDay() {
        return menu;
    }

    public void addMenuDay(DailyMenu dailyMenu) {
        menu.add(dailyMenu);
    }

    @Override
    public String toString() {
        return "WeeklyMenu{" +
                "menu=" + menu +
                '}';
    }
}
