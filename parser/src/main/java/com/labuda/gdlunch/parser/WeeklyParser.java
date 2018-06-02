package com.labuda.gdlunch.parser;

import com.labuda.gdlunch.entity.DailyMenu;
import java.util.List;

/**
 * Interface for parsing weekly menu
 */
public interface WeeklyParser {

    /**
     * Parses the menu for the whole week
     *
     * @return parsed weekly menu
     */
    List<DailyMenu> parse();

}
