package be.ucll.forecast.raspberry.service;

import be.ucll.forecast.raspberry.domain.Humidity;
import be.ucll.forecast.raspberry.domain.Temperature;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.ejb.Stateless;

/**
 * Created by tompl on 12/6/2016.
 */

@Stateless
public class RaspberryServiceImplementation implements RaspberryService {

    @Override
    public Temperature getTemperature() {
        try {
            Runtime rt = Runtime.getRuntime();
            Process pr = rt.exec("python /projects/readDHT22.py 2302 22");
            return new ObjectMapper().readValue( pr.getOutputStream().toString(), Temperature.class );
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Humidity getHumidity() {
        try {
            Runtime rt = Runtime.getRuntime();
            Process pr = rt.exec("python /projects/readDHT22.py 2302 22");
            return new ObjectMapper().readValue( pr.getOutputStream().toString(), Humidity.class );
        } catch (Exception e) {
            return null;
        }
    }
}
