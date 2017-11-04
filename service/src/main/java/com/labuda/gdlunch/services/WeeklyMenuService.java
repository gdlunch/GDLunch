package com.labuda.gdlunch.services;

import com.labuda.gdlunch.entity.WeeklyMenu;

public interface WeeklyMenuService {

    /**
     * Creates new weekly menu entry
     *
     * @param weeklyMenu weekly menu
     * @return freshly created weekly menu
     */
    WeeklyMenu create(WeeklyMenu weeklyMenu);

    /**
     * Deletes weekly menu entry
     *
     * @param weeklyMenu weekly menu
     * @return deleted weekly menu
     */
    WeeklyMenu delete(WeeklyMenu weeklyMenu);

    /**
     * Updates weekly menu entry
     *
     * @param weeklyMenu weekly menu
     * @return updated weekly menu
     */
    WeeklyMenu update(WeeklyMenu weeklyMenu);

    /**
     * Finds weekly menu by restaurant name
     *
     * @param restaurantName restaurant name
     * @return found weekly menu
     */
    WeeklyMenu findByRestaurantName(String restaurantName);
}
