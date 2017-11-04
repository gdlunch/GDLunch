package com.labuda.gd_lunch.services;

import com.labuda.gd_lunch.entity.MenuItem;

/**
 * Menu item service interface
 */
public interface MenuItemService {
    MenuItem create(MenuItem menuItem);
    MenuItem delete(MenuItem menuItem);
    MenuItem update(MenuItem menuItem);
}
