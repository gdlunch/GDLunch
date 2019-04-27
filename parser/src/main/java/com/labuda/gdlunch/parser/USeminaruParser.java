package com.labuda.gdlunch.parser;

import com.labuda.gdlunch.entity.DailyMenu;
import com.labuda.gdlunch.entity.MenuItem;
import com.labuda.gdlunch.entity.Restaurant;
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
            Document document = Jsoup.connect(restaurant.getParserUrl()).get();

            LocalDate mondayOfCurrentWeek = DateUtils.getMondayOfCurrentWeek();

            List<Element> listOfMenus = document
                    .select(".meni1 tr")
                    .stream()
                    .filter(Element::hasText)
                    .collect(Collectors.toList());

            // Parse each day
            for (int i = 0; i < 5; i++) {
                result.add(
                        new DailyMenu(
                                mondayOfCurrentWeek.plusDays(i),
                                restaurant,
                                getDailyMenus(
                                        // Every fourth item is a day headline
                                        listOfMenus.subList(1 + 4 * i, 4 + 4 * i)
                                )
                        )
                );
            }
        } catch (Exception e) {
            log.error("Parsing failed", e);
        }

        addTranslations(result);
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

        for (Element element : dailyMenuElements) {
            Elements tableCells = element.select("td");

            dailyMenuItems.add(
                    new MenuItem(
                            tableCells.get(1).text(),
                            parsePrice(tableCells.get(2).text())
                    )
            );
        }

        return dailyMenuItems;
    }
}
