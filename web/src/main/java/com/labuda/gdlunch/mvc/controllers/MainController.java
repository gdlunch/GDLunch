package com.labuda.gdlunch.mvc.controllers;

import com.labuda.gdlunch.dto.DailyMenuDTO;
import com.labuda.gdlunch.facade.DailyMenuFacade;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
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
    private DailyMenuFacade dailyMenuFacade;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model) {
        LocalDate now = LocalDate.now();
        // TODO Fix getAllMenusForDate() as soon as possible, as the following is a slow hack
        List<DailyMenuDTO> dailyMenus = dailyMenuFacade.getAllMenus();
        List<DailyMenuDTO> dailyMenusForToday = dailyMenus.stream()
                .filter(menu -> menu.getDate().equals(now)).collect(
                        Collectors.toList());

        model.addAttribute("dailyMenus", dailyMenusForToday);
        model.addAttribute("currentDate", now.format(DateTimeFormatter.ofPattern("dd.MM.YYYY")));
        return "index";
    }

}
