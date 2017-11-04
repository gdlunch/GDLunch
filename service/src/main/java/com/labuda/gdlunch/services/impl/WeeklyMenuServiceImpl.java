package com.labuda.gdlunch.services.impl;

import com.labuda.gdlunch.entity.WeeklyMenu;
import com.labuda.gdlunch.repository.WeeklyMenuRepository;
import com.labuda.gdlunch.services.WeeklyMenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;

@Service
public class WeeklyMenuServiceImpl implements WeeklyMenuService {

    private final static Logger log = LoggerFactory.getLogger(WeeklyMenuServiceImpl.class);

    @Resource
    private WeeklyMenuRepository weeklyMenuRepository;

    @Override
    @Transactional
    public WeeklyMenu create(WeeklyMenu weeklyMenu) {
        WeeklyMenu newWeeklyMenu = weeklyMenu;
        weeklyMenuRepository.save(newWeeklyMenu);
        return newWeeklyMenu;
    }

    @Override
    @Transactional
    public WeeklyMenu delete(WeeklyMenu weeklyMenu) {
        WeeklyMenu deletedWeeklyMenu = weeklyMenuRepository.findOne(weeklyMenu.getId());

        if (deletedWeeklyMenu == null) {
            log.error("WeeklyMenu that should have been deleted was not found:" + weeklyMenu);
            return null;
        }

        weeklyMenuRepository.delete(deletedWeeklyMenu);
        return deletedWeeklyMenu;
    }

    @Override
    @Transactional
    public WeeklyMenu update(WeeklyMenu weeklyMenu) {
        WeeklyMenu updatedWeeklyMenu = weeklyMenuRepository.findOne(weeklyMenu.getId());

        if (updatedWeeklyMenu == null) {
            log.error("WeeklyMenu that should have been updated was not found:" + weeklyMenu);
            return null;
        }

        weeklyMenuRepository.save(weeklyMenu);
        return weeklyMenu;
    }

    @Override
    @Transactional
    public WeeklyMenu findByRestaurantName(String restaurantName) {
        return weeklyMenuRepository.findByRestaurantName(restaurantName);
    }
}
