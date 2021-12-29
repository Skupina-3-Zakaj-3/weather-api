package si.fri.rso.skupina3.weather.lib;

import java.util.List;

public class Weather {

    private String locationName;
    private List<ForecastDay> forecastDaysList;

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public List<ForecastDay> getForecastDaysList() {
        return forecastDaysList;
    }

    public void setForecastDaysList(List<ForecastDay> forecastDaysList) {
        this.forecastDaysList = forecastDaysList;
    }
}
