package com.labuda.gdlunch.mvc.controllers.rest;

import com.labuda.gdlunch.dto.DailyMenuDTO;
import com.labuda.gdlunch.dto.RestaurantWithCurrentMenuDTO;
import com.labuda.gdlunch.entity.Restaurant;
import com.labuda.gdlunch.facade.DailyMenuFacade;
import com.labuda.gdlunch.facade.RestaurantFacade;
import com.labuda.gdlunch.mvc.controllers.ControllerUtils;
import com.labuda.gdlunch.mvc.controllers.rest.entities.RestaurantRequest;
import com.labuda.gdlunch.parser.RestaurantsConfig;
import com.labuda.gdlunch.parser.entity.ParserDefinition;
import java.text.Collator;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletResponse;
import org.dozer.MappingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Simple REST controller
 */
@org.springframework.web.bind.annotation.RestController
public class RestController {

    /**
     * Logger
     */
    private final static Logger log = LoggerFactory.getLogger(RestController.class);

    private final Random random = new Random();

    /**
     * Facade to access the daily menus
     */
    @Autowired
    private DailyMenuFacade dailyMenuFacade;

    /**
     * Facade to ascces the restaurants
     */
    @Autowired
    private RestaurantFacade restaurantFacade;

    // TODO this should be changed to list the restaurants from DB, but it requires parser overhaul and restaurants are stored multiple times in the DB

    /**
     * Lists name of restaurants that are available from the config file
     *
     * @return available restaurant names
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = "application/json")
    public List<String> listRestaurants() {
        return getRestaurantsFromConfig().stream().map(Restaurant::getName).sorted().collect(Collectors.toList());
    }

    /**
     * Gets you a random daily menu
     *
     * @return randomly selected daily menu
     */
    @RequestMapping(value = "/lucky", method = RequestMethod.GET, produces = "application/json")
    public DailyMenuDTO lucky() {
        List<DailyMenuDTO> allMenus = dailyMenuFacade.getAllMenusForDate(LocalDate.now());
        return allMenus.get(random.nextInt(allMenus.size()));
    }

    /**
     * Gets daily menu based on the request
     *
     * @param restaurantRequest request for the restaurant menu
     * @param response response
     * @return 204 No Content if no restaurant matching the request is found, 200 with menu otherwise
     */
    @RequestMapping(value = "/", method = RequestMethod.POST, produces = "application/json")
    public RestaurantWithCurrentMenuDTO getMenu(@RequestBody RestaurantRequest restaurantRequest,
            HttpServletResponse response) {
        List<Restaurant> restaurantsFromConfig = getRestaurantsFromConfig();

        // Collator set to level to ignore diacritics and other national symbols
        Collator collator = Collator.getInstance();
        collator.setStrength(Collator.PRIMARY);

        String[] splitRequestName = restaurantRequest.getRestaurantName().split("\\s+");

        List<Restaurant> restaurantsMatchingRequest = new ArrayList<>();
        for (Restaurant restaurant : restaurantsFromConfig) {
            String[] splitName = restaurant.getName().split("\\s+");

            if (matchesAnyPartOfRestaurantName(collator, splitName, splitRequestName)) {
                restaurantsMatchingRequest.add(restaurant);
            }
        }

        // TODO Handle the situation when more matches are found, ideally let the user know

        // When no matches are found return 204 and empty daily menu
        if (restaurantsMatchingRequest.size() == 0) {
            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
            return new RestaurantWithCurrentMenuDTO();
        }

        // Return daily menu for the first match
        response.setStatus(HttpServletResponse.SC_OK);
        Restaurant firstMatch = restaurantsMatchingRequest.get(0);

        // TODO Remove the try/catch if you fix the restaurant name fetching from the database, the mapping exception won't happen anymore
        try {
            return ControllerUtils.getCurrentMenu(
                    restaurantFacade.getRestaurantByName(firstMatch.getName()));
        } catch (MappingException e) {
            log.error("Mapping has failed", e);
            DailyMenuDTO dailyMenuDTO = new DailyMenuDTO();
            dailyMenuDTO.setDate(LocalDate.now());

            RestaurantWithCurrentMenuDTO restaurantWithCurrentMenuDTO = new RestaurantWithCurrentMenuDTO();
            restaurantWithCurrentMenuDTO.setName(firstMatch.getName());
            restaurantWithCurrentMenuDTO.setUrl(firstMatch.getUrl());
            restaurantWithCurrentMenuDTO.setDailyMenu(dailyMenuDTO);

            return restaurantWithCurrentMenuDTO;
        }
    }

    /**
     * Checks whether any part of request matches the restaurant name
     *
     * @param collator collator
     * @param splitRestaurantName restaurant name split by whitespaces
     * @param splitRequestName request restaurant name split by whitespaces
     * @return true if some match is found, false if none was found
     */
    private boolean matchesAnyPartOfRestaurantName(Collator collator, String[] splitRestaurantName,
            String[] splitRequestName) {
        for (String part : splitRestaurantName) {
            for (String request : splitRequestName) {
                if (collator.equals(part, request)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Gets restaurant list from the config file
     *
     * @return list of restaurants
     */
    private List<Restaurant> getRestaurantsFromConfig() {
        List<Restaurant> restaurantsFromConfig = new ArrayList<>();

        for (ParserDefinition parserDefinition : Objects.requireNonNull(RestaurantsConfig.obtain()).getConfig()) {
            restaurantsFromConfig.addAll(parserDefinition.getRestaurants());
        }

        return restaurantsFromConfig;
    }

}
