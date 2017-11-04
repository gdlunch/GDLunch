package com.labuda.gd_lunch.services;

import com.labuda.gd_lunch.entity.WeeklyMenu;

public interface WeeklyMenuService {
    WeeklyMenu create(WeeklyMenu weeklyMenu);
    WeeklyMenu delete(WeeklyMenu weeklyMenu);
    WeeklyMenu update(WeeklyMenu weeklyMenu);
    WeeklyMenu findByRestaurantName(String restaurantName);
}
