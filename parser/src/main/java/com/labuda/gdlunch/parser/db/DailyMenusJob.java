package com.labuda.gdlunch.parser.db;

import com.labuda.gdlunch.dto.DailyMenuDTO;
import com.labuda.gdlunch.entity.Restaurant;
import com.labuda.gdlunch.facade.DailyMenuFacade;
import com.labuda.gdlunch.parser.DailyParser;
import com.labuda.gdlunch.parser.RestaurantsConfig;
import com.labuda.gdlunch.parser.entity.ParserConfig;
import com.labuda.gdlunch.parser.entity.ParserDefinition;
import com.labuda.gdlunch.services.BeanMappingService;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * Job to parse all daily menus that are made publicly available on daily basis
 */
@Service
public class DailyMenusJob {

    /**
     * Logger
     */
    private final static Logger log = LoggerFactory.getLogger(DailyMenusJob.class);

    /**
     * CRON trigger for every day at 8 AM
     */
    private final static String CRON_TRIGGER = "0 0 8 * * *";

    /**
     * Daily menu facade so that we can save parsed daily menus
     */
    @Autowired
    private DailyMenuFacade dailyMenuFacade;

    /**
     * Mapping service
     *
     * Maps the DTOs and entity classes
     */
    @Autowired
    private BeanMappingService beanMappingService;

    /**
     * Executes the parsing jobs for menus that are published daily
     */
    @Scheduled(cron = CRON_TRIGGER)
    public void execute() {
        ParserConfig restaurantConfig = RestaurantsConfig.obtain();

        // Obtains all the definitions that have daily frequency from the config
        List<ParserDefinition> dailyRestaurants = restaurantConfig.getConfig().stream()
                .filter(config -> config.getRefreshFrequency().equals("daily"))
                .collect(Collectors.toList());

        // Goes through the defined restaurants, uses reflection to instantiate the correct parser and parses the menu
        for (ParserDefinition definition : dailyRestaurants) {
            for (Restaurant restaurant : definition.getRestaurants()) {
                try {
                    Class<?> clazz = Class.forName(definition.getParserClass());
                    Constructor<?> constructor = clazz.getConstructor(Restaurant.class);
                    Object instance = constructor.newInstance(restaurant);
                    DailyParser parser = (DailyParser) instance;

                    dailyMenuFacade.addDailyMenu(
                            beanMappingService.mapTo(parser.parse(), DailyMenuDTO.class)
                    );
                } catch (InstantiationException | IllegalAccessException | ClassNotFoundException
                        | NoSuchMethodException | InvocationTargetException e) {
                    log.error("Parsing of the daily menu for " + restaurant + " has failed", e);
                }
            }
        }

    }
}
