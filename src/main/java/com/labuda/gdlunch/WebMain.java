package com.labuda.gdlunch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Main starting class
 */
@SpringBootApplication(scanBasePackages = "com.labuda.gdlunch")
@EnableJpaRepositories("com.labuda.gdlunch")
@EntityScan("com.labuda.gdlunch")
@EnableScheduling
public class WebMain {

    /**
     * Logger
     */
    private final static Logger log = LoggerFactory.getLogger(WebMain.class);

    /**
     * Main method
     *
     * @param args command line args
     */
    public static void main(String... args) {
        log.info("Starting the web application");
        SpringApplication.run(WebMain.class, args);
    }
}
