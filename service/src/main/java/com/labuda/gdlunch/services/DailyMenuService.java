package com.labuda.gdlunch.services;

import com.labuda.gdlunch.entity.DailyMenu;

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

}
