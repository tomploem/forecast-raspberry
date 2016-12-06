package be.ucll.forecast.raspberry.service;

import be.ucll.forecast.raspberry.domain.Humidity;
import be.ucll.forecast.raspberry.domain.Temperature;

/**
 * Created by tompl on 12/6/2016.
 */
public interface RaspberryService {
    Temperature getTemperature();
    Humidity getHumidity();
}
