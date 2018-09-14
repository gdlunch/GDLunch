package com.labuda.gdlunch.parser.brno;

import com.labuda.gdlunch.entity.DailyMenu;
import com.labuda.gdlunch.entity.MenuItem;
import com.labuda.gdlunch.entity.Restaurant;
import com.labuda.gdlunch.parser.AbstractRestaurantWebParser;
import com.labuda.gdlunch.parser.WeeklyParser;
import com.labuda.gdlunch.tools.DateUtils;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Parses daily menu from Siam Thai restaurant
 */
public class SiamThaiParser extends AbstractRestaurantWebParser implements WeeklyParser {

    /**
     * Logger
     */
    private final static Logger log = LoggerFactory.getLogger(SiamThaiParser.class);

    /**
     * Constructor
     *
     * @param restaurant restaurant details
     */
    public SiamThaiParser(Restaurant restaurant) {
        super(restaurant);
    }

    @Override
    public List<DailyMenu> parse() {
        List<DailyMenu> result = new ArrayList<>();

        try {
            Document document = Jsoup.connect(restaurant.getParserUrl()).get();

            LocalDate mondayOfCurrentWeek = DateUtils.getMondayOfCurrentWeek();

            Elements listOfMenus = document.select("#tabs-1 .food");

            for (int i = 0; i < 5; i++) {
                DailyMenu dailyMenu = new DailyMenu();
                dailyMenu.setDate(mondayOfCurrentWeek.plusDays(i));

                Elements originalParagraphs = listOfMenus
                        .get(i)
                        .select("p");

                List<Element> filteredParagraphs = originalParagraphs
                        .stream()
                        .filter(val -> val.text().startsWith("Polévka")
                                || val.text().startsWith("Menu")
                                || val.text().startsWith("Dezert")
                        )
                        .filter(val -> val.select("strong").size() > 0)
                        .collect(Collectors.toList());

                for (int j = 0; j < 4; j++) {
                    String course = filteredParagraphs.get(j).text();

                    // Remove the course "type"
                    if (course.contains(":")) {
                        course = course.substring(course.indexOf(':') + 1);
                    }

                    // Obtain price
                    float price = parsePrice(course);

                    // Remove price from the course
                    course = course.replaceFirst("^(\\D+).*$", "$1");

                    // Add the czech meal description, that is located one paragraph below the meal title
                    course = course + "(" + originalParagraphs
                            .get(originalParagraphs.indexOf(filteredParagraphs.get(j)) + 1).text() + ")";

                    dailyMenu.getMenu().add(new MenuItem(course, price));
                }

                result.add(dailyMenu);
            }
        } catch (Exception e) {
            log.error("Parsing failed", e);
        }

        return result;
    }
}
