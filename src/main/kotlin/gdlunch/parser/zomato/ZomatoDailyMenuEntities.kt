package gdlunch.parser.zomato

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class ZomatoDailyMenus(
        @JsonProperty("daily_menus")
        val dailyMenus: List<ZomatoDailyMenu> = listOf()
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class ZomatoDailyMenu(
        @JsonProperty("daily_menu")
        val dailyMenu: ZomatoDailyMenuContent = ZomatoDailyMenuContent()
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class ZomatoDailyMenuContent(
        val dishes: List<ZomatoDish> = listOf()
)

data class ZomatoDish(
        val dish: ZomatoDishContent = ZomatoDishContent()
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class ZomatoDishContent(
        val name: String = "",
        val price: String = ""
)
