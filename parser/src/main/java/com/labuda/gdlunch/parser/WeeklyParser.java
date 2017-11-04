package com.labuda.gdlunch.parser;

import com.labuda.gdlunch.entity.WeeklyMenu;

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
