package com.labuda.gdlunch.controller;

import com.labuda.gdlunch.dto.DailyMenuDTO;
import com.labuda.gdlunch.dto.facade.DailyMenuFacade;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Index page controller
 */
@Controller
public class MainController {

    @Autowired
    private DailyMenuFacade dailyMenuFacade;

    private final Random random = new Random();

    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("EEE dd.MM.YYYY");

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String indexWithDate(@RequestParam(name = "date", required = false) Integer dateIndex, Model model) {
        LocalDate now = LocalDate.now();
        List<LocalDate> dates = now.with(DayOfWeek.MONDAY).datesUntil(now.with(DayOfWeek.SUNDAY).plusDays(1))
                .collect(Collectors.toList());

        List<String> formattedDates = new ArrayList<>();
        dates.forEach(date -> formattedDates.add(date.format(dateFormatter)));

        if(dateIndex == null) {
            dateIndex = dates.indexOf(now);
        } else if(dateIndex != dates.indexOf(now)) {
            model.addAttribute("luckyDisabled", true);
        }

        List<DailyMenuDTO> dailyMenus = dailyMenuFacade.getAllMenusForDate(dates.get(dateIndex));
        dailyMenus.sort(
                (o1, o2) -> o1.getRestaurant().getName().compareToIgnoreCase(o2.getRestaurant().getName())
        );

        model.addAttribute("dates", formattedDates);
        model.addAttribute("currentDateIndex", dateIndex);
        model.addAttribute("dailyMenus", dailyMenus);
        return "index";
    }

    @RequestMapping(value = "/lucky", method = RequestMethod.GET)
    public String lucky(Model model) {
        List<LocalDate> dates = LocalDate.now().with(DayOfWeek.MONDAY).datesUntil(LocalDate.now().with(DayOfWeek.SUNDAY).plusDays(1))
                .collect(Collectors.toList());

        List<String> formattedDates = new ArrayList<>();
        dates.forEach(date -> formattedDates.add(date.format(dateFormatter)));

        int dateIndex = dates.indexOf(LocalDate.now());

        List<DailyMenuDTO> dailyMenus = dailyMenuFacade.getAllMenusForDate(dates.get(dateIndex));
        model.addAttribute("dates", formattedDates);
        model.addAttribute("currentDateIndex", dateIndex);
        model.addAttribute("dailyMenus", dailyMenus.get(random.nextInt(dailyMenus.size())));
        return "index";
    }
}
