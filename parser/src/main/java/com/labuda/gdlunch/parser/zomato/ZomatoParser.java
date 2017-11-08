package com.labuda.gdlunch.parser.zomato;

import com.labuda.gdlunch.entity.DailyMenu;
import com.labuda.gdlunch.entity.MenuItem;
import com.labuda.gdlunch.parser.AbstractRestaurantWebParser;
import com.labuda.gdlunch.parser.DailyParser;
import java.time.LocalDate;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Basic Zomato parser
 */
public abstract class ZomatoParser extends AbstractRestaurantWebParser implements DailyParser {

    /**
     * Zomato REST client
     */
    private ZomatoRestClient zomatoRestClient = new ZomatoRestClient();

    protected ZomatoParser(String webAddress) {
        super(webAddress);
    }

    /**
     * Obtains daily menu
     *
     * @return daily menu as JSON
     */
    protected JSONObject getDailyMenu() {
        return zomatoRestClient.getDailyMenu(webAddress);
    }

    @Override
    public DailyMenu parse() {
        DailyMenu result = new DailyMenu();
        result.setDate(LocalDate.now());

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
