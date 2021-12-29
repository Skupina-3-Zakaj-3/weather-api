package si.fri.rso.skupina3.weather.services.beans;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;
import si.fri.rso.skupina3.weather.config.AppProperties;
import si.fri.rso.skupina3.weather.lib.ForecastDay;
import si.fri.rso.skupina3.weather.lib.Weather;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.ProcessingException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

@RequestScoped
public class WeatherBean {

    private Logger log = Logger.getLogger(WeatherBean.class.getName());

    private Client httpClient;
    private String baseUrl;

    @Inject
    private AppProperties appProperties;

    @PostConstruct
    private void init() {
        httpClient = ClientBuilder.newClient();
        baseUrl = "https://weatherapi-com.p.rapidapi.com"; // only for demonstration
    }


    public Weather getWeatherForecast(String city, Integer days) {

        log.info("Calling Weather API: getting weather info: city=" + city);

        try {
            String response = httpClient
                            .target(baseUrl + "/forecast.json")
                            .queryParam("q", city)
                            .queryParam("days", days)
                            .request()
                            .header("x-rapidapi-host", "weatherapi-com.p.rapidapi.com")
                            .header("x-rapidapi-key", appProperties.getWeatherApiAccessKey())
                            .get(new GenericType<String>() {});

            JSONObject json = new JSONObject(response);
            JSONArray forecastdays = json.getJSONObject("forecast").getJSONArray("forecastday");

            Weather weather = new Weather();
            weather.setLocationName(json.getJSONObject("location").getString("name"));
            List<ForecastDay> forecastDayList = new ArrayList<ForecastDay>();

            for (int i = 0; i < forecastdays.length(); i++) {
                JSONObject jsonDay = forecastdays.getJSONObject(i);

                ForecastDay forecastDay = new ForecastDay();
//                forecastDay.setDate(new SimpleDateFormat("yyyy-MM-dd").parse(jsonDay.getString("date")));
                forecastDay.setDate(jsonDay.getString("date"));
                forecastDay.setMaxTemp(jsonDay.getJSONObject("day").getDouble("maxtemp_c"));
                forecastDay.setMinTemp(jsonDay.getJSONObject("day").getDouble("mintemp_c"));
                forecastDay.setTextForecast(jsonDay.getJSONObject("day").getJSONObject("condition").getString("text"));
                forecastDay.setIconUrl(jsonDay.getJSONObject("day").getJSONObject("condition").getString("icon"));

                forecastDayList.add(forecastDay);
            }
            weather.setForecastDaysList(forecastDayList);

            return weather;
        }
        catch (WebApplicationException | ProcessingException e) {
            log.severe(e.getMessage());
            throw new InternalServerErrorException(e);
        }
    }
}
