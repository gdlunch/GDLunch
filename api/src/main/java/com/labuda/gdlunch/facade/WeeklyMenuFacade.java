package com.labuda.gdlunch.facade;

import com.labuda.gdlunch.dto.WeeklyMenuDTO;

/**
 * Weekly menu facade interface
 */
public interface WeeklyMenuFacade {

    /**
     * Adds a new weekly menu to database
     *
     * @param weeklyMenu new weekly menu
     */
    void addWeeklyMenu(WeeklyMenuDTO weeklyMenu);

}
