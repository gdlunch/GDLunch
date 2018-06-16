package com.labuda.gdlunch.services;

import com.labuda.gdlunch.entity.DailyMenu;
import java.time.LocalDate;
import java.util.List;

/**
 * Daily menu service interface
 */
public interface DailyMenuService {

    /**
     * Creates new daily menu entry
     *
     * @param dailyMenu daily menu
     * @return freshly created daily menu
     */
    DailyMenu create(DailyMenu dailyMenu);

    /**
     * Deletes daily menu entry
     *
     * @param dailyMenu daily menu
     * @return deleted daily menu
     */
    DailyMenu delete(DailyMenu dailyMenu);

    /**
     * Updates daily menu entry
     *
     * @param dailyMenu daily menu
     * @return updated daily menu
     */
    DailyMenu update(DailyMenu dailyMenu);

    /**
     * Retrieves all daily menus from the database
     *
     * @return list of all daily menus
     */
    List<DailyMenu> findAll();

    /**
     * Retrieves all daily menus from the database that have the given date
     *
     * @param date menu date
     * @return list of all daily menus that have common date
     */
    List<DailyMenu> findAllByDate(LocalDate date);

    /**
     * Retrieves daily menu from the database that has the specified restaurant name and date
     *
     * @param restaurantName restaurant name
     * @param date date
     * @return daily menu from the database
     */
    DailyMenu findDailyMenuByRestaurantNameAndDate(String restaurantName, LocalDate date);
}
