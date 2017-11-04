package com.labuda.gdlunch.services;

import com.labuda.gdlunch.entity.WeeklyMenu;

public interface WeeklyMenuService {
    WeeklyMenu create(WeeklyMenu weeklyMenu);
    WeeklyMenu delete(WeeklyMenu weeklyMenu);
    WeeklyMenu update(WeeklyMenu weeklyMenu);
    WeeklyMenu findByRestaurantName(String restaurantName);
}
