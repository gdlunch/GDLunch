package com.labuda.gdlunch.parser;

import com.labuda.gdlunch.entity.DailyMenu;
import com.labuda.gdlunch.entity.MenuItem;
import com.labuda.gdlunch.entity.WeeklyMenu;
import com.labuda.gdlunch.parser.entity.Restaurant;
import com.labuda.gdlunch.tools.DateUtils;
import com.labuda.gdlunch.tools.WebAddressesConfig;
import java.io.IOException;
import java.time.LocalDate;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Parses weekly menu from Amphone hotel
 */
public class AmphoneParser extends AbstractRestaurantWebParser implements WeeklyParser {

    /**
     * Logger
     */
    private final static Logger log = LoggerFactory.getLogger(AmphoneParser.class);

    /**
     * Constructor
     */
    public AmphoneParser(Restaurant restaurant) {
        super(restaurant);
    }

    @Override
    public WeeklyMenu parse() {
        WeeklyMenu result = new WeeklyMenu("Amphone");

        try {
            Document document = Jsoup.connect(restaurant.getParserUrl()).get();
            Elements elements = document.select(".uk-grid li");
            Elements soups = elements.select("p em");
            Elements mainCourses = elements.select("p ~ ol");

            LocalDate mondayOfCurrentWeek = DateUtils.getMondayOfCurrentWeek();

            for (int i = 0; i < mainCourses.size(); i++) {
                DailyMenu dailyMenu = new DailyMenu();
                dailyMenu.setDate(mondayOfCurrentWeek.plusDays(i));
                dailyMenu.setRestaurantName("Amphone Hotel");
                dailyMenu.getMenu().add(new MenuItem(soups.get(i).text(), 0.0f));

                Elements courses = mainCourses.get(i).select("li");
                for (Element course : courses) {
                    String item = course.text();
                    dailyMenu.getMenu().add(new MenuItem(item.substring(0, item.length() - 5),
                            parsePrice(course.text())));
                }

                result.addDailyMenu(dailyMenu);
            }
        } catch (IOException e) {
            log.error("Parsing failed", e);
        }

        return result;
    }

    /**
     * Helper method to parse price from menu item
     *
     * @param item item on the menu
     * @return price as float
     */
    private Float parsePrice(String item) {
        item = item.trim();
        String cropped = item.substring(item.length() - 5, item.length() - 2);
        return Float.parseFloat(cropped.trim());
    }
}
