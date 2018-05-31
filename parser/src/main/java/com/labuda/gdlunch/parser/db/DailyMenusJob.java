package com.labuda.gdlunch.parser.db;

import com.labuda.gdlunch.dto.DailyMenuDTO;
import com.labuda.gdlunch.facade.DailyMenuFacade;
import com.labuda.gdlunch.parser.DailyParser;
import com.labuda.gdlunch.parser.RestaurantsConfig;
import com.labuda.gdlunch.parser.entity.ParserConfig;
import com.labuda.gdlunch.parser.entity.ParserDefinition;
import com.labuda.gdlunch.entity.Restaurant;
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

@Service
/**
 * Job to parse all daily menus that are made publicly available on daily basis
 */
public class DailyMenusJob {

    /** Logger */
    private final static Logger log = LoggerFactory.getLogger(DailyMenusJob.class);

    @Autowired
    private DailyMenuFacade dailyMenuFacade;

    @Autowired
    private BeanMappingService beanMappingService;

    @Scheduled(cron="0 0 8 * * *")
    public void execute() {
        ParserConfig restaurantConfig = RestaurantsConfig.obtain();

        List<ParserDefinition> dailyRestaurants = restaurantConfig.getConfig().stream()
                .filter(config -> config.getRefreshFrequency().equals("daily"))
                .collect(Collectors.toList());

        for (ParserDefinition definition : dailyRestaurants) {
            for (Restaurant restaurant : definition.getRestaurants()) {
                try {
                    Class<?> clazz = Class.forName(definition.getParserClass());
                    Constructor<?> constructor = clazz.getConstructor(Restaurant.class);
                    Object instance = constructor.newInstance(restaurant);
                    DailyParser parser = (DailyParser) instance;

                    dailyMenuFacade.addDailyMenu(beanMappingService.mapTo(parser.parse(), DailyMenuDTO.class));
                } catch (InstantiationException | IllegalAccessException | ClassNotFoundException | NoSuchMethodException | InvocationTargetException e) {
                    log.error("Parsing of the daily menu has failed", e);
                }
            }
        }

    }
}
