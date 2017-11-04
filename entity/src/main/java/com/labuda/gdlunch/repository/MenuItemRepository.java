package com.labuda.gdlunch.repository;

import com.labuda.gdlunch.entity.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Menu item repository
 */
public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {

}
