package com.labuda.gdlunch.parser;

import com.labuda.gdlunch.repository.entity.DailyMenu;
import com.labuda.gdlunch.repository.entity.MenuItem;
import com.labuda.gdlunch.repository.entity.Restaurant;
import com.labuda.gdlunch.util.DateUtils;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CasaFuegoParser extends AbstractRestaurantWebParser implements WeeklyParser {

    /**
     * Logger
     */
    private final static Logger log = LoggerFactory.getLogger(CasaFuegoParser.class);

    /**
     * Constructor
     *
     * @param restaurant restaurant details
     */
    public CasaFuegoParser(Restaurant restaurant) {
        super(restaurant);
    }

    @Override
    public List<DailyMenu> parse() {
        List<DailyMenu> result = new ArrayList<>();

        try {
            LocalDate mondayOfCurrentWeek = DateUtils.getMondayOfCurrentWeek();

            Document document = Jsoup.connect(restaurant.getParserUrl()).get();
            Elements elements = document.select("div .tab-pane");

            Pattern pattern = Pattern.compile("(\\d*),-");

            for (int i = 0; i < 5; i++) {
                List<MenuItem> dailyMenu = elements.get(i)
                        .select("p")
                        .stream()
                        .map(Element::text)
                        .filter(element -> element.contains(",-"))
                        .map(element -> {
                            Matcher matcher = pattern.matcher(element);
                            matcher.find();
                            return new MenuItem(
                                    element.substring(0, matcher.start(0)),
                                    parsePrice(matcher.group(0))
                            );
                        })
                        .collect(Collectors.toList());
                result.add(new DailyMenu(
                        mondayOfCurrentWeek.plusDays(i),
                        restaurant,
                        dailyMenu
                ));
            }
        } catch (Exception e) {
            log.error("Parsing failed", e);
        }

        return result;
    }
}
