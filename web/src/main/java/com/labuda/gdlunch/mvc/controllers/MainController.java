package com.labuda.gdlunch.mvc.controllers;

import static com.labuda.gdlunch.mvc.controllers.ControllerUtils.getCurrentMenu;

import com.labuda.gdlunch.dto.RestaurantDTO;
import com.labuda.gdlunch.dto.RestaurantWithCurrentMenuDTO;
import com.labuda.gdlunch.facade.RestaurantFacade;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Index page controller
 */
@Controller
public class MainController {

    @Autowired
    private RestaurantFacade restaurantFacade;

    /**
     * Random generator
     */
    private final Random random = new Random();

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model) {
        List<RestaurantDTO> allRestaurants = restaurantFacade.getAllRestaurants();

        allRestaurants.sort(
                (r1, r2) -> r1.getName().compareToIgnoreCase(r2.getName())
        );

        List<RestaurantWithCurrentMenuDTO> restaurantsWithCurrentMenu = allRestaurants.stream()
                .map(ControllerUtils::getCurrentMenu).collect(Collectors.toList());

        model.addAttribute("restaurants", restaurantsWithCurrentMenu);
        model.addAttribute("currentDate", LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM.YYYY")));
        return "index";
    }

    @RequestMapping(value = "/lucky", method = RequestMethod.GET)
    public String lucky(Model model) {
        List<RestaurantDTO> allRestaurants = restaurantFacade.getAllRestaurants();

        model.addAttribute("restaurants",
                Collections.singletonList(
                        getCurrentMenu(
                                allRestaurants.get(random.nextInt(allRestaurants.size()))
                        )
                )
        );
        model.addAttribute("currentDate", LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM.YYYY")));
        return "index";
    }

}
