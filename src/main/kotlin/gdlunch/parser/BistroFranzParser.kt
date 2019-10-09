package gdlunch.parser

import com.labuda.gdlunch.parser.AbstractRestaurantWebParser
import com.labuda.gdlunch.parser.DailyParser
import com.labuda.gdlunch.repository.entity.DailyMenu
import com.labuda.gdlunch.repository.entity.MenuItem
import com.labuda.gdlunch.repository.entity.Restaurant
import org.jsoup.Jsoup
import org.slf4j.LoggerFactory
import java.time.LocalDate

/**
 * Parses daily menu from Bistro Franz restaurant
 */
class BistroFranzParser(restaurant: Restaurant) : AbstractRestaurantWebParser(restaurant), DailyParser {

    val logger = LoggerFactory.getLogger(BistroFranzParser::class.java)

    override fun parse(): DailyMenu {
        val result = DailyMenu()
        result.restaurant = restaurant
        result.date = LocalDate.now()

        val document = Jsoup.connect(restaurant.parserUrl).get()
        document.select(".obedmenu .tabnab.polevka .radj .mnam").forEach {
            result.menu.add(MenuItem(it.ownText(), 0f))
        }
        document.select(".obedmenu .tabnab.jidlo .radj").forEach {
            result.menu.add(
                    MenuItem(
                            it.selectFirst(".mnam").ownText(),
                            parsePrice(it.selectFirst(".cena").ownText()
                            )
                    )
            )
        }

        return result
    }
}
