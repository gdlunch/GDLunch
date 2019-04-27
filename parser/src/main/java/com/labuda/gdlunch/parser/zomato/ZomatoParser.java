package com.labuda.gdlunch.parser.zomato;

import com.labuda.gdlunch.entity.DailyMenu;
import com.labuda.gdlunch.entity.MenuItem;
import com.labuda.gdlunch.entity.Restaurant;
import com.labuda.gdlunch.parser.AbstractRestaurantWebParser;
import com.labuda.gdlunch.parser.DailyParser;
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
     *
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
        result.setRestaurant(restaurant);

        try {
            JSONObject dailyMenu = getDailyMenu();
            JSONArray dishes = dailyMenu.getJSONArray("daily_menus").getJSONObject(0)
                    .getJSONObject("daily_menu").getJSONArray("dishes");

            for (int i = 0; i < dishes.length(); i++) {
                JSONObject dish = dishes.getJSONObject(i).getJSONObject("dish");

                MenuItem menuItem = new MenuItem();
                menuItem.setName(dish.getString("name").trim());
                menuItem.setPrice(parsePrice(dish.getString("price")));

                result.getMenu().add(menuItem);
            }
        } catch (JSONException e) {
            log.error("Parsing of: " + restaurant + "has failed.", e);
        }

        addTranslations(result);
        return result;
    }
}
