package com.labuda.gdlunch.parser;

import com.labuda.gdlunch.repository.entity.DailyMenu;
import com.labuda.gdlunch.repository.entity.MenuItem;
import com.labuda.gdlunch.repository.entity.Restaurant;
import com.labuda.gdlunch.util.DateUtils;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Parses daily menu from U Seminaru restaurant
 */
public class USeminaruParser extends AbstractRestaurantWebParser implements WeeklyParser {

    /**
     * Logger
     */
    private final static Logger log = LoggerFactory.getLogger(USeminaruParser.class);

    /**
     * Constructor
     *
     * @param restaurant restaurant details
     */
    public USeminaruParser(Restaurant restaurant) {
        super(restaurant);
    }

    @Override
    public List<DailyMenu> parse() {
        List<DailyMenu> result = new ArrayList<>();

        try {
            LocalDate mondayOfCurrentWeek = DateUtils.getMondayOfCurrentWeek();

            Document document = Jsoup.connect(restaurant.getParserUrl()).get();
            Elements days = document.select("table[id]");

            for (int i = 0; i < days.size(); i++) {
                result.add(
                        new DailyMenu(
                                mondayOfCurrentWeek.plusDays(i),
                                restaurant,
                                getDailyMenus(days.get(i).select("td"))
                        )
                );
            }
        } catch (Exception e) {
            log.error("Parsing failed", e);
        }

        return result;
    }

    /**
     * Parses menu items from the list of suitable elements
     *
     * @param dailyMenuElements list of elements containing menu for a single day
     * @return list of menu item for a single day
     */
    private List<MenuItem> getDailyMenus(List<Element> dailyMenuElements) {
        List<MenuItem> dailyMenuItems = new ArrayList<>();

        String soup = dailyMenuElements.get(0).text();
        if (!soup.isBlank()) {
            dailyMenuItems.add(
                    new MenuItem(
                            soup,
                            0f
                    )
            );
        }

        for (int j = 2; j < dailyMenuElements.size() - 1; j += 3) {
            String course = dailyMenuElements.get(j).text();
            if (!course.isBlank()) {
                dailyMenuItems.add(
                        new MenuItem(
                                course,
                                parsePrice(dailyMenuElements.get(j + 1).text())
                        )
                );
            }
        }

        return dailyMenuItems;
    }
}
