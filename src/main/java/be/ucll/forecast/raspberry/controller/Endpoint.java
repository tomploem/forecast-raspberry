package be.ucll.forecast.raspberry.controller;

import be.ucll.forecast.raspberry.service.RaspberryService;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by tompl on 12/6/2016.
 */

@Path("/data")
public class Endpoint {

    @EJB
    RaspberryService raspberryService;

    @GET
    @Path ("/temperature")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTemperature( ) {
        return Response.status ( 200 ).entity ( raspberryService.getTemperature () ).build();
    }

    @GET
    @Path ("/humidity")
    @Produces (MediaType.APPLICATION_JSON)
    public Response getHumidity(  ) {
        return Response.status ( 200 ).entity ( raspberryService.getHumidity () ).build();
    }


}
