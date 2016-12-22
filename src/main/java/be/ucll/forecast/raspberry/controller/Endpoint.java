package be.ucll.forecast.raspberry.controller;

import be.ucll.forecast.raspberry.security.JWTTokenNeeded;
import be.ucll.forecast.raspberry.service.RaspberryService;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * Created by tompl on 12/6/2016.
 */

public class Endpoint {

    @EJB
    RaspberryService raspberryService;

    @GET
    @Path ("/temperature")
    @JWTTokenNeeded
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTemperature( ) {
        System.out.println("\n\n\nDO WE GET HERE??????\n\n\n");
        return Response.status ( 200 ).entity ( "Looks like you are using authentication" ).build();
    }

    @GET
    @Path ("/humidity")
    @JWTTokenNeeded
    @Produces (MediaType.APPLICATION_JSON)
    public Response getHumidity(  ) {
        return Response.status ( 200 ).entity ( raspberryService.getHumidity () ).build();
    }


}
