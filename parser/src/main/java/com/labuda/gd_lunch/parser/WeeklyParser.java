package com.labuda.gd_lunch.parser;

import com.labuda.gd_lunch.entity.WeeklyMenu;

/**
 * Interface for parsing weekly menu
 */
public interface WeeklyParser {

    /**
     * Parses the menu for the whole week
     *
     * @return parsed weekly menu
     */
    WeeklyMenu parse();

}
