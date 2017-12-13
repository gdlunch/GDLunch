package com.labuda.gdlunch.parser.db;

import com.labuda.gdlunch.dto.DailyMenuDTO;
import com.labuda.gdlunch.entity.DailyMenu;
import com.labuda.gdlunch.entity.WeeklyMenu;
import com.labuda.gdlunch.facade.DailyMenuFacade;
import com.labuda.gdlunch.parser.AmphoneParser;
import com.labuda.gdlunch.parser.BuddhaParser;
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
        BuddhaParser buddhaParser = new BuddhaParser();

        WeeklyMenu amphoneMenu = amphoneParser.parse();
        WeeklyMenu buddhaMenu = buddhaParser.parse();

        for (DailyMenu dailyMenu : amphoneMenu.getMenu()) {
            dailyMenuFacade.addDailyMenu(beanMappingService.mapTo(dailyMenu, DailyMenuDTO.class));
        }

        for (DailyMenu dailyMenu : buddhaMenu.getMenu()) {
            dailyMenuFacade.addDailyMenu(beanMappingService.mapTo(dailyMenu, DailyMenuDTO.class));
        }
    }
}
