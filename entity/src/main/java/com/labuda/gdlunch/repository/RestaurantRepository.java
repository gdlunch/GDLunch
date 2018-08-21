package com.labuda.gdlunch.repository;

import com.labuda.gdlunch.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Restaurant repository
 */
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

    /**
     * Finds restaurant by name
     *
     * @param name restaurant name
     * @return restaurant with matching name
     */
    Restaurant findByName(String name);

}
