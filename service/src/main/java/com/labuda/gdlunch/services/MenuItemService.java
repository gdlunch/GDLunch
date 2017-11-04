package com.labuda.gdlunch.services;

import com.labuda.gdlunch.entity.MenuItem;

/**
 * Menu item service interface
 */
public interface MenuItemService {

    /**
     * Creates new menu item entry
     *
     * @param menuItem menu item
     * @return freshly created menu item
     */
    MenuItem create(MenuItem menuItem);

    /**
     * Deletes menu item entry
     *
     * @param menuItem menu item
     * @return deleted menu item
     */
    MenuItem delete(MenuItem menuItem);

    /**
     * Updates menu item entry
     *
     * @param menuItem menu item
     * @return updated menu item
     */
    MenuItem update(MenuItem menuItem);
}
