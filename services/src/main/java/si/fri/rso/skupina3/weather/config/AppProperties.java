package si.fri.rso.skupina3.weather.config;

import com.kumuluz.ee.configuration.cdi.ConfigBundle;
import com.kumuluz.ee.configuration.cdi.ConfigValue;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
@ConfigBundle("app-properties")
public class AppProperties {

    @ConfigValue(watch = true)
    private String weatherApiAccessKey;

    public String getWeatherApiAccessKey() {
        return weatherApiAccessKey;
    }

    public void setWeatherApiAccessKey(String weatherApiAccessKey) {
        this.weatherApiAccessKey = weatherApiAccessKey;
    }
}