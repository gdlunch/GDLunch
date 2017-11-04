package com.labuda.gdlunch.services;

import com.labuda.gdlunch.entity.MenuItem;

/**
 * Menu item service interface
 */
public interface MenuItemService {
    MenuItem create(MenuItem menuItem);
    MenuItem delete(MenuItem menuItem);
    MenuItem update(MenuItem menuItem);
}
