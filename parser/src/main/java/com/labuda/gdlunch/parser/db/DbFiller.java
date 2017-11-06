package com.labuda.gdlunch.parser.db;

import com.labuda.gdlunch.dto.DailyMenuDTO;
import com.labuda.gdlunch.facade.DailyMenuFacade;
import com.labuda.gdlunch.parser.DailyParser;
import com.labuda.gdlunch.parser.zomato.SborovnaParser;
import com.labuda.gdlunch.services.BeanMappingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DbFiller {

    @Autowired
    private DailyMenuFacade dailyMenuFacade;

    @Autowired
    private BeanMappingService beanMappingService;

    public void fill() {
        DailyParser sborovna = new SborovnaParser();
        dailyMenuFacade.addDailyMenu(beanMappingService.mapTo(sborovna.parse(), DailyMenuDTO.class));
    }

}
