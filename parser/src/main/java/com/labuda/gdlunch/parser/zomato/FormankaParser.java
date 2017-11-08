package com.labuda.gdlunch.parser.zomato;

import com.labuda.gdlunch.entity.DailyMenu;
import com.labuda.gdlunch.tools.WebAddressesConfig;

/**
 * Parses weekly menu from Formanka restaurant
 */
public class FormankaParser extends ZomatoParser {

    public FormankaParser() {
        super(WebAddressesConfig.getInstance().getString("formanka"));
    }

    @Override
    public DailyMenu parse() {
        DailyMenu result = super.parse();
        result.setRestaurantName("Formanka");
        return result;
    }
}
