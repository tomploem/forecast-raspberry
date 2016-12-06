package be.ucll.forecast.raspberry.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by tompl on 12/6/2016.
 */
public class Temperature {

    @JsonProperty("temperature")
    private double temperature;

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }
}
