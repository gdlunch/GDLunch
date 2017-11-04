package com.labuda.gd_lunch.repository;

import com.labuda.gd_lunch.entity.DailyMenu;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Daily menu repository
 */
public interface DailyMenuRepository extends JpaRepository<DailyMenu, Long> {

}
