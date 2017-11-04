package com.labuda.gdlunch.services.impl;

import com.labuda.gdlunch.entity.MenuItem;
import com.labuda.gdlunch.repository.MenuItemRepository;
import com.labuda.gdlunch.services.MenuItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;

@Service
public class MenuItemServiceImpl implements MenuItemService {

    private final static Logger log = LoggerFactory.getLogger(MenuItemServiceImpl.class);

    @Resource
    private MenuItemRepository menuItemRepository;

    @Override
    @Transactional
    public MenuItem create(MenuItem menuItem) {
        MenuItem newMenuItem = menuItem;
        menuItemRepository.save(newMenuItem);
        return newMenuItem;
    }

    @Override
    @Transactional
    public MenuItem delete(MenuItem menuItem) {
        MenuItem deletedMenuItem = menuItemRepository.findOne(menuItem.getId());

        if (deletedMenuItem == null) {
            log.error("MenuItem that should have been deleted was not found:" + deletedMenuItem);
            return null;
        }

        menuItemRepository.delete(deletedMenuItem);
        return deletedMenuItem;
    }

    @Override
    @Transactional
    public MenuItem update(MenuItem menuItem) {
        MenuItem updatedMenuItem = menuItemRepository.findOne(menuItem.getId());

        if (updatedMenuItem == null) {
            log.error("MenuItem that should have been updated was not found:" + updatedMenuItem);
            return null;
        }

        menuItemRepository.save(menuItem);
        return menuItem;
    }
}
