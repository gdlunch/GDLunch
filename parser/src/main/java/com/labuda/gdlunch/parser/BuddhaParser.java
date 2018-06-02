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
import org.jsoup.nodes.TextNode;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Parses weekly menu from Buddha restaurant
 */
public class BuddhaParser extends AbstractRestaurantWebParser implements WeeklyParser {

    /**
     * Logger
     */
    private final static Logger log = LoggerFactory.getLogger(BuddhaParser.class);

    /**
     * Constructor
     *
     * @param restaurant restaurant details
     */
    public BuddhaParser(Restaurant restaurant) {
        super(restaurant);
    }

    @Override
    public List<DailyMenu> parse() {
        List<DailyMenu> result = new ArrayList<>();

        try {
            Document document = Jsoup.connect(restaurant.getParserUrl()).get();
            Elements all = document.select("p.textmenu");
            LocalDate mondayOfCurrentWeek = DateUtils.getMondayOfCurrentWeek();

            List<Element> days = all;

            for (int i = 0; i < days.size(); i++) {
                DailyMenu dailyMenu = new DailyMenu();
                dailyMenu.setDate(mondayOfCurrentWeek.plusDays(i));
                dailyMenu.setRestaurant(restaurant);

                List<TextNode> day = days.get(i).textNodes();
                for (TextNode node : day) {
                    String nodeText = Parser.unescapeEntities(node.text(), false).replaceAll("(^\\h*)|(\\h*$)", "");
                    if (!nodeText.trim().isEmpty() && nodeText.startsWith("*")) {
                        dailyMenu.getMenu().add(
                                new MenuItem(nodeText.substring(0, nodeText.length() - 9), parsePrice(nodeText))
                        );
                    }
                }
                result.add(dailyMenu);
            }

        } catch (Exception e) {
            log.error("Parsing using the BuddhaParser has failed", e);
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
        String cropped = item.substring(item.length() - 8, item.length() - 5);
        return Float.parseFloat(cropped.trim());
    }
}
