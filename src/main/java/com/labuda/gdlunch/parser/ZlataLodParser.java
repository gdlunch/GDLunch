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
            Element menu = document.selectFirst(".menu-box menu");
            String menuHtml = menu.html();

            Element soups = Jsoup
                    .parseBodyFragment(menuHtml.substring(menuHtml.indexOf("POLÉVKY"), menuHtml.indexOf("<ol>")))
                    .body();
            Elements mainCourses = menu.select("li");
            Element theRest = Jsoup.parseBodyFragment(menuHtml.substring(menuHtml.indexOf("</ol>"))).body();

            List<TextNode> soupsTexts = soups.textNodes().stream().filter(textNode -> !textNode.isBlank())
                    .collect(Collectors.toList());
            Elements soupsPrices = soups.select("em");
            for (int i = 1; i < soupsTexts.size(); i++) {
                result.getMenu().add(new MenuItem(
                        soupsTexts.get(i).text(),
                        parsePrice(soupsPrices.get(i - 1).text())
                ));
            }

            mainCourses.forEach(course -> {
                float price = parsePrice(course.selectFirst("em").text());
                result.getMenu().add(new MenuItem(course.textNodes().get(0).text(), price));
            });

            List<TextNode> otherDishes = theRest.textNodes().stream().filter(textNode -> !textNode.isBlank())
                    .collect(Collectors.toList());
            Elements otherPrices = theRest.select("em");
            for (int i = 0; i < otherDishes.size(); i++) {
                result.getMenu().add(new MenuItem(
                        otherDishes.get(i).text(),
                        parsePrice(otherPrices.get(i).text())
                ));
            }
        } catch (Exception e) {
            log.error("Parsing failed", e);
        }

        return result;
    }
}
