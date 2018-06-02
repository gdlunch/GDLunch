package com.labuda.gdlunch.parser.db;

import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Database filler <p> This class provides an entry point to application that will fill the database
 * with daily menu entries
 */
@SpringBootApplication(scanBasePackages = "com.labuda.gdlunch")
public class DbFillerMain {

    /**
     * Database filler
     */
    @Autowired
    private DbFiller dbFiller;

    /**
     * Basic main method, just to fill the database without running the web
     *
     * @param args command line args
     */
    public static void main(String... args) {
        SpringApplication springApplication = new SpringApplication(DbFillerMain.class);
        springApplication.setWebEnvironment(false);
        springApplication.run(args);
    }

    /**
     * Runs the db filler method to fill the database
     */
    @PostConstruct
    public void fill() {
        dbFiller.fill();
    }
}
