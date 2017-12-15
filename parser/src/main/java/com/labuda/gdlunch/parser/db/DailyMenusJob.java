package com.labuda.gdlunch.parser.db;

import com.labuda.gdlunch.dto.DailyMenuDTO;
import com.labuda.gdlunch.facade.DailyMenuFacade;
import com.labuda.gdlunch.parser.zomato.FormankaParser;
import com.labuda.gdlunch.parser.zomato.SborovnaParser;
import com.labuda.gdlunch.parser.zomato.SuziesParser;
import com.labuda.gdlunch.parser.zomato.VeselaVaciceParser;
import com.labuda.gdlunch.services.BeanMappingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class DailyMenusJob {

    @Autowired
    private DailyMenuFacade dailyMenuFacade;

    @Autowired
    private BeanMappingService beanMappingService;

    @Scheduled(cron="0 0 8 * * *")
    public void execute() {
        dailyMenuFacade.addDailyMenu(
                beanMappingService.mapTo(new FormankaParser().parse(), DailyMenuDTO.class));
        dailyMenuFacade.addDailyMenu(
                beanMappingService.mapTo(new SborovnaParser().parse(), DailyMenuDTO.class));
        dailyMenuFacade.addDailyMenu(
                beanMappingService.mapTo(new SuziesParser().parse(), DailyMenuDTO.class));
        dailyMenuFacade.addDailyMenu(
                beanMappingService.mapTo(new VeselaVaciceParser().parse(), DailyMenuDTO.class));
    }
}
