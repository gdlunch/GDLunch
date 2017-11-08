package com.labuda.gdlunch.services.facadeimpl;

import com.labuda.gdlunch.dto.WeeklyMenuDTO;
import com.labuda.gdlunch.entity.WeeklyMenu;
import com.labuda.gdlunch.facade.WeeklyMenuFacade;
import com.labuda.gdlunch.services.BeanMappingService;
import com.labuda.gdlunch.services.WeeklyMenuService;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Weekly menu facade implementation
 */
@Service
@Transactional
public class WeeklyMenuFacadeImpl implements WeeklyMenuFacade {

    /**
     * Weekly menu service
     */
    @Autowired
    private WeeklyMenuService weeklyMenuService;

    /**
     * Mapper
     */
    @Autowired
    private BeanMappingService beanMappingService;

    @Override
    public void addWeeklyMenu(WeeklyMenuDTO weeklyMenu) {
        if (weeklyMenu == null) {
            throw new IllegalArgumentException("WeeklyMenuDTO was null");
        }

        WeeklyMenu entity = beanMappingService.mapTo(weeklyMenu, WeeklyMenu.class);
        weeklyMenuService.create(entity);
        weeklyMenu.setId(entity.getId());
    }
}
