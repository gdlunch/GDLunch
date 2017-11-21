package com.labuda.gdlunch.parser.zomato;

import com.labuda.gdlunch.entity.DailyMenu;
import com.labuda.gdlunch.tools.WebAddressesConfig;

/**
 * Parses daily menu from Suzies restaurant
 */
public class SuziesParser extends ZomatoParser {

    public SuziesParser() {
        super(WebAddressesConfig.getInstance().getString("suzies"));
    }

    @Override
    public DailyMenu parse() {
        DailyMenu result = super.parse();
        result.setRestaurantName("Suzie's restaurant");
        return result;
    }

}
