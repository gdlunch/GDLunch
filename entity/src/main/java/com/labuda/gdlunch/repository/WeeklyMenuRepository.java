package com.labuda.gdlunch.repository;

import com.labuda.gdlunch.entity.WeeklyMenu;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Weekly menu repository
 */
public interface WeeklyMenuRepository extends JpaRepository<WeeklyMenu, Long> {

    /**
     * Finds weekly menu by restaurant name
     *
     * @param restaurantName restaurant name
     * @return corresponding weekly menu
     */
    WeeklyMenu findByRestaurantName(String restaurantName);
}
