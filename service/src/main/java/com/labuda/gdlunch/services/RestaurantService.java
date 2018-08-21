package com.labuda.gdlunch.services;

import com.labuda.gdlunch.entity.Restaurant;
import java.util.List;

/**
 * Restaurant service interface
 */
public interface RestaurantService {

    /**
     * Creates new restaurant entry
     *
     * @param restaurant restaurant
     * @return freshly created restaurant
     */
    Restaurant create(Restaurant restaurant);

    /**
     * Deletes restaurant entry
     *
     * @param restaurant restaurant
     * @return deleted restaurant
     */
    Restaurant delete(Restaurant restaurant);

    /**
     * Updates restaurant entry
     *
     * @param restaurant restaurant
     * @return updated restaurant
     */
    Restaurant update(Restaurant restaurant);

    /**
     * Retrieves all restaurants from the database
     *
     * @return list of all restaurants
     */
    List<Restaurant> findAll();

    /**
     * Finds restaurant with matching name
     *
     * @param name restaurant name
     * @return restaurant with the provided name
     */
    Restaurant findByName(String name);
}
