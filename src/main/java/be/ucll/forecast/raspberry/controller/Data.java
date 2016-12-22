package be.ucll.forecast.raspberry.controller;

import be.ucll.forecast.raspberry.security.JWTTokenNeeded;
import be.ucll.forecast.raspberry.service.RaspberryService;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

/**
 * Created by tompl on 12/6/2016.
 */

@Path("data")
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
public class Data {

    @EJB
    RaspberryService raspberryService;

    @GET
    @Path ("/temperature")
    @JWTTokenNeeded
    @Produces(MediaType.TEXT_PLAIN)
    public Response getTemperature( ) {
        // If there is a connection with the python file we will uncomment this return.
        //return Response.status ( 200 ).entity ( raspberryService.getTemperature () ).build();

        //For now we will use this dummy text
        return Response.status ( 200 ).entity ( "This is a dummy text for the getTemperature method protected with JWT\ntemperature:{tmp: 65}" ).build();
    }

    @GET
    @Path ("/humidity")
    @JWTTokenNeeded
    @Produces(MediaType.TEXT_PLAIN)
    public Response getHumidity(  ) {
        // If there is a connection with the python file we will uncomment this return.
        //return Response.status ( 200 ).entity ( raspberryService.getHumidity () ).build();

        //For now we will use this dummy text
        return Response.status ( 200 ).entity ( "This is a dummy text for the getHumidity method protected with JWT\nhumidity:{hum: 65}" ).build();
    }


}
