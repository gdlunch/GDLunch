package com.labuda.gdlunch.service;

import com.labuda.gdlunch.repository.entity.Restaurant;

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

}
