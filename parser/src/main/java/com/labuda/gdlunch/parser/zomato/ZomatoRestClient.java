package com.labuda.gdlunch.parser.zomato;

import com.labuda.gdlunch.tools.ApiKeys;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Rest client for Zomato
 * This client will be setup to work specifically with Zomato API
 */
public class ZomatoRestClient {

    /**
     * Logger
     */
    private final static Logger log = LoggerFactory.getLogger(ZomatoRestClient.class);

    /**
     * OkHttp client
     */
    private OkHttpClient client = new OkHttpClient();

    /**
     * Gets daily menu from Zomato and returns it in JSON
     *
     * @param url daily menu query
     * @return daily menu as JSONObject
     */
    public JSONObject getDailyMenu(String url) {
        JSONObject result = new JSONObject();

        Request request = prepareRequestWithAuthToken()
                .url(url)
                .build();

        try {
            Response response = client.newCall(request).execute();
            result = new JSONObject(response.body().string());
        } catch (IOException e) {
            log.error("Connection to zomato api has failed, request was=" + request, e);
        }

        return result;
    }

    /**
     * Initializes request with required headers
     *
     * @return pre-initialized request builder
     */
    private Request.Builder prepareRequestWithAuthToken() {
        return new Request.Builder()
                .addHeader("Accept", "application/json")
                .addHeader("user_key", ApiKeys.getInstance().getString("zomato"));
    }
}
