package com.labuda.gdlunch.mvc.controllers;

import com.labuda.gdlunch.dto.DailyMenuDTO;
import com.labuda.gdlunch.facade.DailyMenuFacade;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    @Autowired
    private DailyMenuFacade dailyMenuFacade;

    @RequestMapping(value = "/json", method = RequestMethod.GET)
    public List<DailyMenuDTO> menus() {
        LocalDate now = LocalDate.now();
        // TODO Fix getAllMenusForDate() as soon as possible, as the following is a slow hack
        List<DailyMenuDTO> dailyMenus = dailyMenuFacade.getAllMenus();
        List<DailyMenuDTO> dailyMenusForToday = dailyMenus.stream()
                .filter(menu -> menu.getDate().equals(now)).collect(
                        Collectors.toList());

        dailyMenusForToday.sort(
                (o1, o2) -> o1.getRestaurantName().compareToIgnoreCase(o2.getRestaurantName())
        );

        return dailyMenusForToday;
    }
}
