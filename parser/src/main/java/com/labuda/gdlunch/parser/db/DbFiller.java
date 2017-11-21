package com.labuda.gdlunch.parser.db;

import com.labuda.gdlunch.dto.DailyMenuDTO;
import com.labuda.gdlunch.entity.DailyMenu;
import com.labuda.gdlunch.entity.WeeklyMenu;
import com.labuda.gdlunch.facade.DailyMenuFacade;
import com.labuda.gdlunch.parser.AmphoneParser;
import com.labuda.gdlunch.parser.zomato.FormankaParser;
import com.labuda.gdlunch.parser.zomato.SborovnaParser;
import com.labuda.gdlunch.parser.zomato.SuziesParser;
import com.labuda.gdlunch.services.BeanMappingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * DB filler
 */
@Service
public class DbFiller {

    @Autowired
    private DailyMenuFacade dailyMenuFacade;

    @Autowired
    private BeanMappingService beanMappingService;

    /**
     * Fills the database with entries that were parsed from web
     */
    public void fill() {
        AmphoneParser amphoneParser = new AmphoneParser();
        WeeklyMenu weeklyMenu = amphoneParser.parse();

        for (DailyMenu dailyMenu : weeklyMenu.getMenu()) {
            dailyMenuFacade.addDailyMenu(beanMappingService.mapTo(dailyMenu, DailyMenuDTO.class));
        }

        dailyMenuFacade.addDailyMenu(
                beanMappingService.mapTo(new FormankaParser().parse(), DailyMenuDTO.class));
        dailyMenuFacade.addDailyMenu(
                beanMappingService.mapTo(new SborovnaParser().parse(), DailyMenuDTO.class));
        dailyMenuFacade.addDailyMenu(
                beanMappingService.mapTo(new SuziesParser().parse(), DailyMenuDTO.class));
    }

}
