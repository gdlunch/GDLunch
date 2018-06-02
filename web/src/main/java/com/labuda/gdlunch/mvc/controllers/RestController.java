package com.labuda.gdlunch.mvc.controllers;

import com.labuda.gdlunch.dto.DailyMenuDTO;
import com.labuda.gdlunch.facade.DailyMenuFacade;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    @Autowired
    private DailyMenuFacade dailyMenuFacade;

    @RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
    public List<DailyMenuDTO> menus() {
        return dailyMenuFacade.getAllMenusForDate(LocalDate.now());
    }
}
