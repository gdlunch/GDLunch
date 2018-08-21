package com.labuda.gdlunch.services.facadeimpl;

import com.labuda.gdlunch.dto.RestaurantDTO;
import com.labuda.gdlunch.entity.Restaurant;
import com.labuda.gdlunch.facade.RestaurantFacade;
import com.labuda.gdlunch.services.BeanMappingService;
import com.labuda.gdlunch.services.RestaurantService;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Restaurant facade implementation
 */
@Service
@Transactional
public class RestaurantFacadeImpl implements RestaurantFacade {

    /**
     * Restaurant service
     */
    @Autowired
    private RestaurantService restaurantService;

    /**
     * Mapper
     */
    @Autowired
    private BeanMappingService beanMappingService;

    @Override
    public void add(RestaurantDTO restaurant) {
        if (restaurant == null) {
            throw new IllegalArgumentException("DailyMenuDTO was null");
        }

        Restaurant entity = beanMappingService.mapTo(restaurant, Restaurant.class);
        restaurantService.create(entity);
        restaurant.setId(entity.getId());
    }

    @Override
    public List<RestaurantDTO> getAllRestaurants() {
        return beanMappingService.mapTo(restaurantService.findAll(), RestaurantDTO.class);
    }

    @Override
    public RestaurantDTO getRestaurantByName(String name) {
        return beanMappingService.mapTo(restaurantService.findByName(name), RestaurantDTO.class);
    }
}
