package com.labuda.gdlunch.parser;

import com.labuda.gdlunch.repository.entity.DailyMenu;
import com.labuda.gdlunch.repository.entity.MenuItem;
import com.labuda.gdlunch.repository.entity.Restaurant;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Parses daily menu from Zlata Lod restaurant
 */
public class ZlataLodParser extends AbstractRestaurantWebParser implements DailyParser {

    /**
     * Logger
     */
    private final static Logger log = LoggerFactory.getLogger(ZlataLodParser.class);

    /**
     * Constructor
     *
     * @param restaurant restaurant details
     */
    public ZlataLodParser(Restaurant restaurant) {
        super(restaurant);
    }

    @Override
    public DailyMenu parse() {
        DailyMenu result = new DailyMenu();
        result.setRestaurant(restaurant);
        result.setDate(LocalDate.now());

        try {
            Document document = Jsoup.connect(restaurant.getParserUrl()).get();
            Elements items = document.selectFirst(".menu-one-day").select("td");
            List<String> names = items.stream()
                    .filter(element -> items.indexOf(element) % 2 == 0)
                    .map(Element::text)
                    .collect(Collectors.toList());
            List<Float> prices = items.stream()
                    .filter(element -> items.indexOf(element) % 2 == 1)
                    .map(element -> parsePrice(element.text()))
                    .collect(Collectors.toList());

            for (int i = 0; i < names.size(); i++) {
                result.getMenu().add(new MenuItem(
                        names.get(i),
                        prices.get(i)
                ));
            }
        } catch (Exception e) {
            log.error("Parsing failed", e);
        }

        return result;
    }
}
