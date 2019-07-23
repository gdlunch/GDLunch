package com.labuda.gdlunch.repository;

import com.labuda.gdlunch.repository.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Restaurant repository
 */
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

}
