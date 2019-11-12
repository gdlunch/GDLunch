package gdlunch.parser.zomato

import com.labuda.gdlunch.parser.AbstractRestaurantWebParser
import com.labuda.gdlunch.parser.DailyParser
import com.labuda.gdlunch.repository.entity.DailyMenu
import com.labuda.gdlunch.repository.entity.MenuItem
import com.labuda.gdlunch.repository.entity.Restaurant
import java.time.LocalDate

/**
 * Basic Zomato parser for daily menus
 */
class ZomatoParser(restaurant: Restaurant) : AbstractRestaurantWebParser(restaurant), DailyParser {

    /**
     * REST client for communication with Zomato
     */
    private val zomatoRestClient = ZomatoRestClient()

    override fun parse(): DailyMenu = DailyMenu(
            LocalDate.now(),
            restaurant,
            getListOfDishes()
    )

    /**
     * Transforms the Zomato response to a list menu items
     */
    private fun getListOfDishes(): List<MenuItem> {
        val dailyMenus = zomatoRestClient.getDailyMenu(restaurant.parserUrl).dailyMenus
        return if (dailyMenus.isNotEmpty()) {
            dailyMenus[0].dailyMenu.dishes.map { MenuItem(it.dish.name, parsePrice(it.dish.price)) }
        } else {
            listOf(MenuItem())
        }
    }
}
