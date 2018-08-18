package com.labuda.gdlunch.parser;

import com.labuda.gdlunch.entity.DailyMenu;
import com.labuda.gdlunch.entity.MenuItem;
import com.labuda.gdlunch.entity.Restaurant;
import com.labuda.gdlunch.tools.DateUtils;
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
 * Parses weekly menu from Na Svabce restaurant
 */
public class NaSvabceParser extends AbstractRestaurantWebParser implements WeeklyParser {

    /**
     * Logger
     */
    private final static Logger log = LoggerFactory.getLogger(NaSvabceParser.class);

    /**
     * Constructor
     */
    public NaSvabceParser(Restaurant restaurant) {
        super(restaurant);
    }

    @Override
    public List<DailyMenu> parse() {
        List<DailyMenu> result = new ArrayList<>();

        try {
            Document document = Jsoup.connect(restaurant.getParserUrl()).validateTLSCertificates(false).get();

            LocalDate mondayOfCurrentWeek = DateUtils.getMondayOfCurrentWeek();

            Elements menus = document.select(".pm-item");
            for (int i = 0; i < menus.size(); i++) {
                result.add(
                        new DailyMenu(
                                mondayOfCurrentWeek.plusDays(i),
                                restaurant,
                                getListOfMenuItems(menus.get(i))
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
     * @param dailyMenus list of elements containing menu for a single day
     * @return list of menu item for a single day
     */
    private List<MenuItem> getListOfMenuItems(Element dailyMenus) {
        List<MenuItem> dailyMenuItems = new ArrayList<>();

        Elements tableRows = dailyMenus.select("tr");

        // Skip the last one, it is always empty
        for (int i = 0; i < tableRows.size() - 1; i++) {
            String course = tableRows.get(i).select(".pmtitle").text();

            if (course.contains(":")) {
                course = course.substring(course.indexOf(':') + 1);
            }

            String priceString = tableRows.get(i).select(".pmprice").text();
            if (priceString == null || priceString.isEmpty()) {
                priceString = course.substring(course.lastIndexOf('/') + 1);
            }

            // Trim the price
            course = course.substring(0, course.lastIndexOf('/') > 0 ? course.lastIndexOf('/') + 1 : 0);

            dailyMenuItems.add(
                    new MenuItem(
                            course,
                            parsePrice(priceString)
                    )
            );
        }

        return dailyMenuItems;
    }
}
