package com.labuda.gdlunch.services.impl;

import com.labuda.gdlunch.entity.DailyMenu;
import com.labuda.gdlunch.repository.DailyMenuRepository;
import com.labuda.gdlunch.services.DailyMenuService;
import java.time.LocalDate;
import java.util.List;
import javax.annotation.Resource;
import javax.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Daily menu service implementation
 */
@Service
public class DailyMenuServiceImpl implements DailyMenuService {

    /**
     * Logger
     */
    private final static Logger log = LoggerFactory.getLogger(DailyMenuServiceImpl.class);

    /**
     * Daily menu repository
     */
    @Resource
    private DailyMenuRepository dailyMenuRepository;

    @Override
    @Transactional
    public DailyMenu create(DailyMenu dailyMenu) {
        DailyMenu newDailyMenu = dailyMenu;
        dailyMenuRepository.save(newDailyMenu);
        return newDailyMenu;
    }

    @Override
    @Transactional
    public DailyMenu delete(DailyMenu dailyMenu) {
        DailyMenu deletedDailyMenu = dailyMenuRepository.findOne(dailyMenu.getId());

        if (deletedDailyMenu == null) {
            log.error("DailyMenu that should have been deleted was not found:" + dailyMenu);
            return null;
        }

        dailyMenuRepository.delete(deletedDailyMenu);
        return deletedDailyMenu;
    }

    @Override
    @Transactional
    public DailyMenu update(DailyMenu dailyMenu) {
        DailyMenu updatedDailyMenu = dailyMenuRepository.findOne(dailyMenu.getId());

        if (updatedDailyMenu == null) {
            log.error("DailyMenu that should have been updated was not found:" + dailyMenu);
            return null;
        }

        dailyMenuRepository.save(dailyMenu);
        return dailyMenu;
    }

    @Override
    public List<DailyMenu> findAll() {
        return dailyMenuRepository.findAll();
    }

    @Override
    public List<DailyMenu> findAllByDate(LocalDate date) {
        return dailyMenuRepository.findByDate(date);
    }

    @Override
    public DailyMenu findDailyMenuByRestaurantNameAndDate(String restaurantName, LocalDate date) {
        return dailyMenuRepository.findDailyMenuByRestaurantNameAndDate(restaurantName, date);
    }
}
