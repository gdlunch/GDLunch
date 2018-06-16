package com.labuda.gdlunch.repository;

import com.labuda.gdlunch.entity.DailyMenu;
import java.time.temporal.Temporal;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Daily menu repository
 */
public interface DailyMenuRepository extends JpaRepository<DailyMenu, Long> {

    /**
     * Finds daily menus by date
     *
     * @param date menu date
     * @return list of daily menus with given date
     */
    List<DailyMenu> findByDate(Temporal date);

    /**
     * Finds a daily menu by date and restaurant name
     *
     * @param restaurantName restaurant name
     * @param date date
     * @return daily menu for given restaurant
     */
    DailyMenu findDailyMenuByRestaurantNameAndDate(String restaurantName, Temporal date);

}
