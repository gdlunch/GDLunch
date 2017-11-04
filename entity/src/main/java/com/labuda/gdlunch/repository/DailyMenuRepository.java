package com.labuda.gdlunch.repository;

import com.labuda.gdlunch.entity.DailyMenu;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Daily menu repository
 */
public interface DailyMenuRepository extends JpaRepository<DailyMenu, Long> {

}
