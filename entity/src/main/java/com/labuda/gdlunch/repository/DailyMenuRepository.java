package com.labuda.gdlunch.repository;

import com.labuda.gdlunch.entity.DailyMenu;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Daily menu repository
 */
public interface DailyMenuRepository extends JpaRepository<DailyMenu, Long> {

    /**
     * Finds daily menus by date
     *
     * @param date menu date
     * @return list of daily menus with common date
     */
    List<DailyMenu> findByDate(java.time.temporal.Temporal date);

}
