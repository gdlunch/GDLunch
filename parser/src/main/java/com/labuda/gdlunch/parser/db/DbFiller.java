package com.labuda.gdlunch.parser.db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Instant DB filler
 */
@Service
public class DbFiller {

    /** Logger */
    private final static Logger log = LoggerFactory.getLogger(DbFiller.class);

    @Autowired
    private DailyMenusJob dailyMenusJob;

    @Autowired
    private WeeklyMenusJob weeklyMenusJob;

    /**
     * Fills the database with entries that were parsed from web
     */
    public void fill() {
        weeklyMenusJob.execute();
        dailyMenusJob.execute();
    }

}
