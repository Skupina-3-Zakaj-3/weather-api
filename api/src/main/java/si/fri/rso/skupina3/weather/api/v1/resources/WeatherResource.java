package si.fri.rso.skupina3.weather.api.v1.resources;

import com.kumuluz.ee.cors.annotations.CrossOrigin;
//import com.kumuluz.ee.discovery.annotations.DiscoverService;
import com.kumuluz.ee.logs.cdi.Log;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.headers.Header;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.rest.client.RestClientBuilder;
import si.fri.rso.skupina3.weather.lib.Weather;
import si.fri.rso.skupina3.weather.services.beans.WeatherBean;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.logging.Logger;


@ApplicationScoped
@Path("/weather")
@Produces(MediaType.APPLICATION_JSON)
@Consumes({MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN})
@CrossOrigin(supportedMethods = "GET, POST, HEAD, DELETE, OPTIONS")
public class WeatherResource {

    private Logger log = Logger.getLogger(WeatherResource.class.getName());

    @Inject
    private WeatherBean weatherBean;

    @GET
    public Response getWeatherForecast(
            @DefaultValue("Ljubljana") @QueryParam("q") String city,
            @DefaultValue("3") @QueryParam("days") Integer days
    ) {
        log.info("getWeatherForecast() called: city=" + city);

        Weather response = weatherBean.getWeatherForecast(city, days);

        return Response.status(Response.Status.OK).entity(response).build();
    }

}
