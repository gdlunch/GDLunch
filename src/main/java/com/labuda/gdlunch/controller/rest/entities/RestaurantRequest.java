package com.labuda.gdlunch.controller.rest.entities;

/**
 * Simple restaurant request from the REST API
 */
public class RestaurantRequest {

    /**
     * Restaurant name
     */
    private String restaurantName;

    /**
     * Constructor
     *
     * @param restaurantName restaurant name
     */
    public RestaurantRequest(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    /**
     * Constructor
     */
    public RestaurantRequest() {

    }

    /**
     * Getter for restaurant name
     *
     * @return restaurant name
     */
    public String getRestaurantName() {
        return restaurantName;
    }

    /**
     * Setter for restaurant name
     *
     * @param restaurantName restaurant name
     */
    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    @Override
    public String toString() {
        return "RestaurantRequest{" +
                "restaurantName='" + restaurantName + '\'' +
                '}';
    }
}
