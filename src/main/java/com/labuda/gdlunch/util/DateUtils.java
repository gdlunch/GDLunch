package com.labuda.gdlunch.util;

import java.time.LocalDate;

/**
 * Date utilities
 */
public class DateUtils {

    /**
     * Returns monday of the current week
     *
     * @return date that is the monday of the current week
     */
    public static LocalDate getMondayOfCurrentWeek() {
        LocalDate today = LocalDate.now();
        return today.minusDays(today.getDayOfWeek().getValue() - 1);
    }
}
