package com.labuda.gdlunch.parser.zomato;

import com.labuda.gdlunch.parser.AbstractRestaurantWebParser;
import com.labuda.gdlunch.parser.DailyParser;
import org.json.JSONObject;

/**
 * Basic Zomato parser
 */
public abstract class ZomatoParser extends AbstractRestaurantWebParser implements DailyParser {

    /**
     * Zomato REST client
     */
    private ZomatoRestClient zomatoRestClient;

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
}
