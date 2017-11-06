package com.labuda.gdlunch.services.facadeimpl;

import com.labuda.gdlunch.dto.DailyMenuDTO;
import com.labuda.gdlunch.entity.DailyMenu;
import com.labuda.gdlunch.facade.DailyMenuFacade;
import com.labuda.gdlunch.services.BeanMappingService;
import com.labuda.gdlunch.services.DailyMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Daily menu facade implementation
 */
@Service
@Transactional
public class DailyMenuFacadeImpl implements DailyMenuFacade {

    /**
     * Daily menu service
     */
    @Autowired
    private DailyMenuService dailyMenuService;

    /**
     * Mapper
     */
    @Autowired
    private BeanMappingService beanMappingService;

    @Override
    public void addDailyMenu(DailyMenuDTO dailyMenu) {
        if (dailyMenu == null) {
            throw new IllegalArgumentException("DailyMenuDTO was null");
        }

        DailyMenu entity = beanMappingService.mapTo(dailyMenu, DailyMenu.class);
        dailyMenuService.create(entity);
        dailyMenu.setId(entity.getId());
    }

}
