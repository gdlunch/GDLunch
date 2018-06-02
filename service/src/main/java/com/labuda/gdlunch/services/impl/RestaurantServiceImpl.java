package com.labuda.gdlunch.services.impl;

import com.labuda.gdlunch.entity.Restaurant;
import com.labuda.gdlunch.repository.RestaurantRepository;
import com.labuda.gdlunch.services.RestaurantService;
import javax.annotation.Resource;
import javax.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Restaurant service implementation
 */
@Service
public class RestaurantServiceImpl implements RestaurantService {

    /**
     * Logger
     */
    private final static Logger log = LoggerFactory.getLogger(RestaurantServiceImpl.class);

    /**
     * Restaurant repository
     */
    @Resource
    private RestaurantRepository restaurantRepository;

    @Override
    @Transactional
    public Restaurant create(Restaurant restaurant) {
        Restaurant newRestaurant = restaurant;
        restaurantRepository.save(newRestaurant);
        return newRestaurant;
    }

    @Override
    @Transactional
    public Restaurant delete(Restaurant restaurant) {
        Restaurant deletedRestaurant = restaurantRepository.findOne(restaurant.getId());

        if (deletedRestaurant == null) {
            log.error("Restaurant that should have been deleted was not found:" + deletedRestaurant);
            return null;
        }

        restaurantRepository.delete(deletedRestaurant);
        return deletedRestaurant;
    }

    @Override
    @Transactional
    public Restaurant update(Restaurant restaurant) {
        Restaurant updatedRestaurant = restaurantRepository.findOne(restaurant.getId());

        if (updatedRestaurant == null) {
            log.error("Restaurant that should have been updated was not found:" + updatedRestaurant);
            return null;
        }

        restaurantRepository.save(restaurant);
        return restaurant;
    }

}
