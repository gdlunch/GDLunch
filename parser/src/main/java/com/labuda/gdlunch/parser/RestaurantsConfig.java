package com.labuda.gdlunch.parser;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.labuda.gdlunch.parser.entity.ParserConfig;
import java.io.FileNotFoundException;
import java.io.FileReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Simple reader and parser of the "restaurants.json"
 */
public class RestaurantsConfig {

    /**
     * Logger
     */
    private final static Logger log = LoggerFactory.getLogger(RestaurantsConfig.class);

    /**
     * Parser configuration
     */
    private static ParserConfig parserConfig;

    /**
     * Obtains the parser configuration from the json
     *
     * @return parsed config
     */
    public static ParserConfig obtain() {
        if (parserConfig != null) {
            return parserConfig;
        } else {
            Gson gson = new Gson();
            try {
                parserConfig = gson.fromJson(new JsonReader(new FileReader("restaurants.json")), ParserConfig.class);
                return parserConfig;
            } catch (FileNotFoundException e) {
                log.error("Couldn't find restaurants.json in pwd", e);
            }
        }
        return null;
    }

}
