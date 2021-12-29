package si.fri.rso.skupina3.weather.lib;

import java.util.Date;

public class ForecastDay {
    private String date;
    private Double maxTemp;
    private Double minTemp;
    private String textForecast;
    private String iconUrl;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Double getMaxTemp() {
        return maxTemp;
    }

    public void setMaxTemp(Double maxTemp) {
        this.maxTemp = maxTemp;
    }

    public Double getMinTemp() {
        return minTemp;
    }

    public void setMinTemp(Double minTemp) {
        this.minTemp = minTemp;
    }

    public String getTextForecast() {
        return textForecast;
    }

    public void setTextForecast(String textForecast) {
        this.textForecast = textForecast;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }
}
