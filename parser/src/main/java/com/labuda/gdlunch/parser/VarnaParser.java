package com.labuda.gdlunch.parser;

import com.labuda.gdlunch.entity.DailyMenu;
import com.labuda.gdlunch.entity.MenuItem;
import com.labuda.gdlunch.entity.Restaurant;
import java.time.LocalDate;
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

            Element firstH2 = document.select("article.clanek").select("h2").first();
            Elements h2Siblings = firstH2.siblingElements();

            Elements dailyOffer = new Elements();
            for(int i = 0; i < h2Siblings.size(); i++) {
                if (h2Siblings.get(i).tagName().equals("h2")) {
                    break;
                } else {
                    dailyOffer.add(h2Siblings.get(i));
                }
            }

            dailyOffer = dailyOffer.select("table.restaurace-menu");

            // First one is soup without price
            result.getMenu().add(new MenuItem(dailyOffer.get(0).select("td").text(), 0f));

            for (int i = 1; i < dailyOffer.size(); i++) {
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
