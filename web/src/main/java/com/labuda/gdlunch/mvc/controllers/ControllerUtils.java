package com.labuda.gdlunch.mvc.controllers;

import com.labuda.gdlunch.dto.DailyMenuDTO;
import com.labuda.gdlunch.dto.RestaurantDTO;
import com.labuda.gdlunch.dto.RestaurantWithCurrentMenuDTO;
import java.time.LocalDate;
import java.util.Optional;

/**
 * Utils for web controllers
 */
public class ControllerUtils {

    /**
     * Gets current menu for the restaurant
     *
     * @param restaurant restaurant
     * @return restaurant containing the current daily menu along with the basic information
     */
    public static RestaurantWithCurrentMenuDTO getCurrentMenu(RestaurantDTO restaurant) {
        Optional<DailyMenuDTO> optionalDailyMenu = restaurant.getDailyMenus().stream().filter(
                dailyMenu -> dailyMenu.getDate().equals(LocalDate.now())
        ).findFirst();

        DailyMenuDTO todayMenu = optionalDailyMenu.orElseGet(DailyMenuDTO::new);
        return new RestaurantWithCurrentMenuDTO(
                restaurant.getName(),
                restaurant.getUrl(),
                todayMenu
        );
    }

}
