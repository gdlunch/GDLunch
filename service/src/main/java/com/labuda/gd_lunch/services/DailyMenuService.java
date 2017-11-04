package com.labuda.gd_lunch.services;

import com.labuda.gd_lunch.entity.DailyMenu;

/**
 * Daily menu service interface
 */
public interface DailyMenuService {

    DailyMenu create(DailyMenu dailyMenu);
    DailyMenu delete(DailyMenu dailyMenu);
    DailyMenu update(DailyMenu dailyMenu);

}
