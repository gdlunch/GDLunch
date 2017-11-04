package com.labuda.gd_lunch.services.facadeimpl;

import com.labuda.gd_lunch.dto.WeeklyMenuDTO;
import com.labuda.gd_lunch.entity.WeeklyMenu;
import com.labuda.gd_lunch.facade.WeeklyMenuFacade;
import com.labuda.gd_lunch.services.BeanMappingService;
import com.labuda.gd_lunch.services.WeeklyMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class WeeklyMenuFacadeImpl implements WeeklyMenuFacade {

    @Autowired
    private WeeklyMenuService weeklyMenuService;

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
