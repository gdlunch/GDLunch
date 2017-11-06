package com.labuda.gdlunch.facade;

import com.labuda.gdlunch.dto.DailyMenuDTO;

/**
 * Daily menu facade interfacee
 */
public interface DailyMenuFacade {

    /**
     * Adds a new daily menu to database
     *
     * @param dailyMenu new daily menu
     */
    void addDailyMenu(DailyMenuDTO dailyMenu);
}
