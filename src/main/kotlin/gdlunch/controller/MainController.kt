package gdlunch.controller

import com.labuda.gdlunch.dto.facade.DailyMenuFacade
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import kotlin.random.Random
import kotlin.streams.toList

@Controller
class MainController(
    private val dailyMenuFacade: DailyMenuFacade
) {
    private val dateFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("EEE dd.MM.YYYY")

    @GetMapping("/")
    fun indexWithDate(
            @RequestParam(name = "date", required = false) dateIndex: Int?,
            model: Model
    ): String {
        val now = LocalDate.now()
        val availableDates = now.with(DayOfWeek.MONDAY).datesUntil(now.with(DayOfWeek.SUNDAY).plusDays(1)).toList()

        val desiredIndex = dateIndex ?: availableDates.indexOf(now)
        val dailyMenus = dailyMenuFacade.getAllMenusForDate(availableDates[desiredIndex])
                .sortedBy { it.restaurant.name }

        if (dateIndex != null && dateIndex != availableDates.indexOf(now)) {
            model.addAttribute("luckyDisabled", true)
        }
        model.addAttribute("dates", availableDates.map { it.format(dateFormatter) })
        model.addAttribute("currentDateIndex", desiredIndex)
        model.addAttribute("dailyMenus", dailyMenus)
        return "index"
    }

    @GetMapping("/lucky")
    fun lucky(model: Model): String {
        val now = LocalDate.now()
        val availableDates = now.with(DayOfWeek.MONDAY).datesUntil(now.with(DayOfWeek.SUNDAY).plusDays(1)).toList()

        val menus = dailyMenuFacade.getAllMenusForDate(now)
        model.addAttribute("dates", availableDates.map { it.format(dateFormatter) })
        model.addAttribute("currentDateIndex", availableDates.indexOf(now))
        model.addAttribute("dailyMenus", menus[Random.nextInt(menus.size)])
        return "index"
    }

}
