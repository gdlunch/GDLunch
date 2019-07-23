package com.labuda.gdlunch.parser.db;

import com.labuda.gdlunch.dto.DailyMenuDTO;
import com.labuda.gdlunch.repository.entity.DailyMenu;
import com.labuda.gdlunch.repository.entity.Restaurant;
import com.labuda.gdlunch.dto.facade.DailyMenuFacade;
import com.labuda.gdlunch.parser.RestaurantsConfig;
import com.labuda.gdlunch.parser.WeeklyParser;
import com.labuda.gdlunch.parser.entity.ParserConfig;
import com.labuda.gdlunch.parser.entity.ParserDefinition;
import com.labuda.gdlunch.service.BeanMappingService;
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
 * Job to parse all daily menus that are made publicly available on weekly basis
 */
@Service
public class WeeklyMenusJob {

    /**
     * Logger
     */
    private final static Logger log = LoggerFactory.getLogger(WeeklyMenusJob.class);

    /**
     * CRON trigger for every monday at 10 AM
     */
    private final static String CRON_TRIGGER = "0 0 10 * * Mon";

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
     * Executes the parsing jobs for menus that are published weekly
     */
    @Scheduled(cron = CRON_TRIGGER)
    public void execute() {
        ParserConfig restaurantConfig = RestaurantsConfig.obtain();

        // Obtains all the definitions that have weekly frequency from the config
        List<ParserDefinition> dailyRestaurants = restaurantConfig.getConfig().stream()
                .filter(config -> config.getRefreshFrequency().equals("weekly"))
                .collect(Collectors.toList());

        // Goes through the defined restaurants, uses reflection to instantiate the correct parser and parses the menu
        for (ParserDefinition definition : dailyRestaurants) {
            for (Restaurant restaurant : definition.getRestaurants()) {
                try {
                    Class<?> clazz = Class.forName(definition.getParserClass());
                    Constructor<?> constructor = clazz.getConstructor(Restaurant.class);
                    Object instance = constructor.newInstance(restaurant);
                    WeeklyParser parser = (WeeklyParser) instance;

                    for (DailyMenu dailyMenu : parser.parse()) {
                        dailyMenuFacade.addDailyMenu(
                                beanMappingService.mapTo(dailyMenu, DailyMenuDTO.class)
                        );
                    }
                } catch (InstantiationException | IllegalAccessException | ClassNotFoundException
                        | NoSuchMethodException | InvocationTargetException e) {
                    log.error("Parsing of the daily menu for " + restaurant + " has failed", e);
                }
            }
        }
    }
}
