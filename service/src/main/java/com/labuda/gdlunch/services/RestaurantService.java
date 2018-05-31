package com.labuda.gdlunch.services;

import com.labuda.gdlunch.entity.Restaurant;

public interface RestaurantService {

    Restaurant create(Restaurant restaurant);
    Restaurant delete(Restaurant restaurant);
    Restaurant update(Restaurant restaurant);

}
