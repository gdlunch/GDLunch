package com.labuda.gdlunch.parser;

import com.labuda.gdlunch.repository.entity.DailyMenu;
import com.labuda.gdlunch.repository.entity.MenuItem;
import com.labuda.gdlunch.repository.entity.Restaurant;
import com.labuda.gdlunch.util.DateUtils;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Parses daily menu from Forky's restaurant
 */
public class ForkysParser extends AbstractRestaurantWebParser implements WeeklyParser {

    /**
     * Logger
     */
    private final static Logger log = LoggerFactory.getLogger(ForkysParser.class);

    /**
     * Constructor
     *
     * @param restaurant restaurant details
     */
    public ForkysParser(Restaurant restaurant) {
        super(restaurant);
    }

    @Override
    public List<DailyMenu> parse() {
        List<DailyMenu> result = new ArrayList<>();

        try {
            Document document = Jsoup.connect(restaurant.getParserUrl()).get();

            LocalDate mondayOfCurrentWeek = DateUtils.getMondayOfCurrentWeek();

            Elements menus = document.select("article");

            for (int i = 0; i < 5; i++) {
                result.add(
                        new DailyMenu(
                                mondayOfCurrentWeek.plusDays(i),
                                restaurant,
                                menus.get(i).select(".item").stream().map(course ->
                                        new MenuItem(
                                                course.select(".title").text(),
                                                parsePrice(course.select(".price").text())
                                        )).collect(Collectors.toList())
                        )
                );
            }
        } catch (Exception e) {
            log.error("Parsing failed", e);
        }

        return result;
    }
}
