package com.labuda.gdlunch.facade;

import com.labuda.gdlunch.dto.DailyMenuDTO;
import java.time.LocalDate;
import java.util.List;

/**
 * Daily menu facade interface
 */
public interface DailyMenuFacade {

    /**
     * Adds a new daily menu to database
     *
     * @param dailyMenu new daily menu
     */
    void addDailyMenu(DailyMenuDTO dailyMenu);

    /**
     * Gets all daily menus stored in database
     */
    List<DailyMenuDTO> getAllMenus();

    /**
     * Gets all daily menus for given date
     *
     * @param date menu date
     * @return list of all daily menus with common date
     */
    List<DailyMenuDTO> getAllMenusForDate(LocalDate date);

    /**
     * Returns daily menu for given restaurant and date
     *
     * @param restaurantName restaurant name
     * @param date date
     * @return found daily menu
     */
    DailyMenuDTO findDailyMenuByRestaurantNameAndDate(String restaurantName, LocalDate date);
}
