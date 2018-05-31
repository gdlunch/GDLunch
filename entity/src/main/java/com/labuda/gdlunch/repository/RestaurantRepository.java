package com.labuda.gdlunch.repository;

import com.labuda.gdlunch.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

}
