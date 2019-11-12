package gdlunch.parser.zomato

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.labuda.gdlunch.util.ApiKeys
import okhttp3.OkHttpClient
import okhttp3.Request

/**
 * Rest client for Zomato. This client is setup to work specifically with Zomato REST API.
 */
class ZomatoRestClient {

    /**
     * Simple HTTP client
     */
    private val client = OkHttpClient()

    /**
     * Gets daily menu from the Zomato [url] in a parsed data class.
     */
    fun getDailyMenu(url: String): ZomatoDailyMenus {
        val request = prepareRequestWithAuthToken()
                .url(url)
                .build()

        val response = client.newCall(request).execute()
        return jacksonObjectMapper().readValue(response.body!!.string())
    }

    /**
     * Initializes the request with required headers
     */
    private fun prepareRequestWithAuthToken(): Request.Builder =
            Request.Builder()
                    .addHeader("Accept", "application/json")
                    .addHeader("user_key", ApiKeys.getInstance().getString("zomato"))

}
