package gdlunch.parser

import com.labuda.gdlunch.parser.AbstractRestaurantWebParser
import com.labuda.gdlunch.parser.DailyParser
import com.labuda.gdlunch.repository.entity.DailyMenu
import com.labuda.gdlunch.repository.entity.MenuItem
import com.labuda.gdlunch.repository.entity.Restaurant
import mu.KotlinLogging
import org.jsoup.Jsoup
import java.time.LocalDate

/**
 * Parses daily menu from Zlata Lod restaurant
 */
class ZlataLodParser(restaurant: Restaurant) : AbstractRestaurantWebParser(restaurant), DailyParser {

    val logger = KotlinLogging.logger { }

    override fun parse(): DailyMenu {
        val result = DailyMenu()
        result.restaurant = restaurant
        result.date = LocalDate.now()

        val document = Jsoup.connect(restaurant.parserUrl).get()
        val items = document.selectFirst(".menu-one-day").select("td")
        val names = items.filterIndexed { index, _ -> index % 2 == 0 }.map { it.text() }
        val prices = items.filterIndexed { index, _ -> index % 2 == 1 }.map { parsePrice(it.text()) }

        names.forEachIndexed { index, name -> result.menu.add(MenuItem(name, prices[index])) }

        return result
    }
}
