package com.labuda.gdlunch.parser.zomato;

import com.labuda.gdlunch.entity.DailyMenu;
import com.labuda.gdlunch.tools.WebAddressesConfig;

/**
 * Parses weekly menu from Formanka restaurant
 */
public class VeselaVaciceParser extends ZomatoParser {

    public VeselaVaciceParser() {
        super(WebAddressesConfig.getInstance().getString("vacice"));
    }

    @Override
    public DailyMenu parse() {
        DailyMenu result = super.parse();
        result.setRestaurantName("Veselá Vačice");
        return result;
    }
}
