package com.labuda.gdlunch.parser.db;

import com.labuda.gdlunch.dto.DailyMenuDTO;
import com.labuda.gdlunch.entity.DailyMenu;
import com.labuda.gdlunch.entity.WeeklyMenu;
import com.labuda.gdlunch.facade.DailyMenuFacade;
import com.labuda.gdlunch.parser.AmphoneParser;
import com.labuda.gdlunch.services.BeanMappingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class WeeklyMenusJob {

    @Autowired
    private DailyMenuFacade dailyMenuFacade;

    @Autowired
    private BeanMappingService beanMappingService;

    @Scheduled(cron="0 0 8 * * Mon")
    public void execute() {
        AmphoneParser amphoneParser = new AmphoneParser();
        WeeklyMenu weeklyMenu = amphoneParser.parse();

        for (DailyMenu dailyMenu : weeklyMenu.getMenu()) {
            dailyMenuFacade.addDailyMenu(beanMappingService.mapTo(dailyMenu, DailyMenuDTO.class));
        }
    }
}
