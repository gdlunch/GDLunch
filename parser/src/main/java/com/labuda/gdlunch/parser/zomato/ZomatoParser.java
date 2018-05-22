package com.labuda.gdlunch.parser.zomato;

import com.labuda.gdlunch.entity.DailyMenu;
import com.labuda.gdlunch.entity.MenuItem;
import com.labuda.gdlunch.parser.AbstractRestaurantWebParser;
import com.labuda.gdlunch.parser.DailyParser;
import com.labuda.gdlunch.parser.entity.Restaurant;
import java.time.LocalDate;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Basic Zomato parser
 */
public class ZomatoParser extends AbstractRestaurantWebParser implements DailyParser {

    /**
     * Logger
     */
    private final static Logger log = LoggerFactory.getLogger(ZomatoParser.class);

    /**
     * Zomato REST client
     */
    private ZomatoRestClient zomatoRestClient = new ZomatoRestClient();

    /**
     * Constructor
     * @param restaurant restaurant
     */
    public ZomatoParser(Restaurant restaurant) {
        super(restaurant);
    }

    /**
     * Obtains daily menu
     *
     * @return daily menu as JSON
     */
    protected JSONObject getDailyMenu() {
        return zomatoRestClient.getDailyMenu(restaurant.getParserUrl());
    }

    @Override
    public DailyMenu parse() {
        DailyMenu result = new DailyMenu();
        result.setDate(LocalDate.now());

        try {
            JSONObject dailyMenu = getDailyMenu();
            JSONArray dishes = dailyMenu.getJSONArray("daily_menus").getJSONObject(0)
                    .getJSONObject("daily_menu").getJSONArray("dishes");

            for (int i = 0; i < dishes.length(); i++) {
                JSONObject dish = dishes.getJSONObject(i).getJSONObject("dish");

                MenuItem menuItem = new MenuItem();
                menuItem.setName(dish.getString("name").trim());
                menuItem.setPrice(parsePriceWithKcAtTheEnd(dish.getString("price")));

                result.getMenu().add(menuItem);
            }
        } catch (JSONException e) {
            log.error("Parsing of: " + restaurant + "has failed.", e);
        }
        result.setRestaurantName(restaurant.getName());

        return result;
    }

    /**
     * Helper method to parse price from menu item with " Kc" ending
     *
     * @param item item on the menu
     * @return price as float
     */
    protected Float parsePriceWithKcAtTheEnd(String item) {
        if (item == null || item.isEmpty()) {
            return 0.0f;
        }

        item = item.trim();
        String cropped = "";

        if (item.length() >= 2) {
            cropped = item.substring(0, item.length() - 3);
        }

        return Float.parseFloat(cropped.trim());
    }
}
