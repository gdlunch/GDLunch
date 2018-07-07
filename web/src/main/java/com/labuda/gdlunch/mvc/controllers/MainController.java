package com.labuda.gdlunch.mvc.controllers;

import com.labuda.gdlunch.dto.DailyMenuDTO;
import com.labuda.gdlunch.facade.DailyMenuFacade;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;
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
    private DailyMenuFacade dailyMenuFacade;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model) {
        List<DailyMenuDTO> dailyMenus = dailyMenuFacade.getAllMenusForDate(LocalDate.now());

        dailyMenus.sort(
                (o1, o2) -> o1.getRestaurant().getName().compareToIgnoreCase(o2.getRestaurant().getName())
        );

        model.addAttribute("dailyMenus", dailyMenus);
        model.addAttribute("currentDate", LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM.YYYY")));
        return "index";
    }

    @RequestMapping(value = "/lucky", method = RequestMethod.GET)
    public String lucky(Model model) {
        List<DailyMenuDTO> allMenus = dailyMenuFacade.getAllMenusForDate(LocalDate.now());
        model.addAttribute("dailyMenus", allMenus.get(new Random().nextInt(allMenus.size())));
        model.addAttribute("currentDate", LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM.YYYY")));
        return "index";
    }
}
