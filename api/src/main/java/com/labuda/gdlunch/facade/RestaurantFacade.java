package com.labuda.gdlunch.facade;

import com.labuda.gdlunch.dto.RestaurantDTO;
import java.util.List;

/**
 * Restaurant facade interface
 */
public interface RestaurantFacade {

    /**
     * Adds new restaurant to database
     *
     * @param restaurant restaurant
     */
    void add(RestaurantDTO restaurant);

    /**
     * Gets all restaurants stored in database
     *
     * @return list of restaurants from DB
     */
    List<RestaurantDTO> getAllRestaurants();

    /**
     * Gets restaurant with the given name
     *
     * @param name restaurant name
     * @return restaurant entity with the name
     */
    RestaurantDTO getRestaurantByName(String name);
}
