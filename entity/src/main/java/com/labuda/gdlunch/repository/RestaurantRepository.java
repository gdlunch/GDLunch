package com.labuda.gdlunch.repository;

import com.labuda.gdlunch.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Restaurant repository
 */
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

}
