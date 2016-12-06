package be.ucll.forecast.raspberry.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by tompl on 12/6/2016.
 */


public class Humidity {

    @JsonProperty("humidity")
    private double humidity;

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }
}
