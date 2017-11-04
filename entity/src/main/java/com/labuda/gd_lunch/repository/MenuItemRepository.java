package com.labuda.gd_lunch.repository;

import com.labuda.gd_lunch.entity.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Menu item repository
 */
public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {
}
