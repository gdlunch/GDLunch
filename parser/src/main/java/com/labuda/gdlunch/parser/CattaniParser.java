package com.labuda.gdlunch.parser;

import com.labuda.gdlunch.entity.DailyMenu;
import com.labuda.gdlunch.entity.MenuItem;
import com.labuda.gdlunch.entity.Restaurant;
import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Parses daily menu from Cattani restaurant
 */
public class CattaniParser extends AbstractRestaurantWebParser implements DailyParser {

    /**
     * Logger
     */
    private final static Logger log = LoggerFactory.getLogger(CattaniParser.class);

    /**
     * Constructor
     *
     * @param restaurant restaurant details
     */
    public CattaniParser(Restaurant restaurant) {
        super(restaurant);
    }

    @Override
    public DailyMenu parse() {
        DailyMenu result = new DailyMenu();
        result.setRestaurant(restaurant);
        result.setDate(LocalDate.now());

        try {
            Document document = Jsoup.connect(restaurant.getParserUrl()).get();

            Elements menu = document.select(".locationMenu.veveri .menu");
            if (menu.size() > 0) {
                Elements courses = menu.first().getElementsByTag("li");

                for (Element course : courses) {
                    result.getMenu().add(new MenuItem(
                            course.select("[lang=cs]").first().text(),
                            parsePrice(course.select(".price .amount").first().text())
                    ));
                }
            }
        } catch (Exception e) {
            log.error("Parsing failed", e);
        }

        return result;
    }

    /**
     * Parses the price
     *
     * @param priceString price
     * @return parsed price if successful, -1 if not found
     */
    private Float parsePrice(String priceString) {
        Pattern p = Pattern.compile("([0-9]+)");
        Matcher m = p.matcher(priceString);

        if (m.find()) {
            float price = -1f;
            try {
                price = Float.parseFloat(m.group());
            } catch (Exception e) {
                log.error("Couldn't parse the price correctly from = " + m.group(), e);
            }
            return price;
        } else {
            return 0f;
        }
    }
}
