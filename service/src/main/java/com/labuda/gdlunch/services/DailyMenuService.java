package com.labuda.gdlunch.services;

import com.labuda.gdlunch.entity.DailyMenu;

/**
 * Daily menu service interface
 */
public interface DailyMenuService {

    DailyMenu create(DailyMenu dailyMenu);
    DailyMenu delete(DailyMenu dailyMenu);
    DailyMenu update(DailyMenu dailyMenu);

}
