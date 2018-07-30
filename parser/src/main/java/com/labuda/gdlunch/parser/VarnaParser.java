package com.labuda.gdlunch.parser;

import com.labuda.gdlunch.entity.DailyMenu;
import com.labuda.gdlunch.entity.MenuItem;
import com.labuda.gdlunch.entity.Restaurant;
import java.time.LocalDate;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Parses daily menu from Varna restaurant
 */
public class VarnaParser extends AbstractRestaurantWebParser implements DailyParser {

    /**
     * Logger
     */
    private final static Logger log = LoggerFactory.getLogger(VarnaParser.class);

    /**
     * Constructor
     *
     * @param restaurant restaurant details
     */
    public VarnaParser(Restaurant restaurant) {
        super(restaurant);
    }

    @Override
    public DailyMenu parse() {
        DailyMenu result = new DailyMenu();
        result.setRestaurant(restaurant);
        result.setDate(LocalDate.now());

        try {
            Document document = Jsoup.connect(restaurant.getParserUrl()).get();
            List<Element> dailyOffer = document.select("table.restaurace-menu").subList(0, 7);

            // First one is soup without price
            result.getMenu().add(new MenuItem(dailyOffer.get(0).select("td").text(), 0f));

            for (int i = 1; i < 7; i++) {
                Elements names = dailyOffer.get(i).select(".nazev");
                Elements prices = dailyOffer.get(i).select(".cena");

                for (int j = 0; j < names.size(); j++) {
                    result.getMenu().add(new MenuItem(
                            names.get(j).text(),
                            parsePrice(prices.get(j).text())
                    ));
                }
            }
        } catch (Exception e) {
            log.error("Parsing failed", e);
        }

        return result;
    }
}
