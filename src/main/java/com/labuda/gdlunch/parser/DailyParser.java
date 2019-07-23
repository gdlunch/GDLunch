package com.labuda.gdlunch.parser;

import com.labuda.gdlunch.repository.entity.DailyMenu;

/**
 * Daily menu parser
 */
public interface DailyParser {

    /**
     * Parses menu for a single day
     *
     * @return daily menu
     */
    DailyMenu parse();

}
