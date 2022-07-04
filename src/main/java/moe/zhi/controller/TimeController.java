package moe.zhi.controller;

import io.quarkus.cache.CacheResult;
import moe.zhi.config.TimezoneConfig;
import moe.zhi.model.DateTime;

import javax.inject.Inject;
import javax.validation.constraints.NotBlank;
import javax.ws.rs.*;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.sql.Time;
import java.util.Objects;

/**
 * v1.0 fix me
 *
 * @author zhimoe
 * @version 1.0
 * @since 2022/7/3
 */
@Path("/now")
public class TimeController {
    private Client rest = ClientBuilder.newClient();

    @Inject
    TimezoneConfig tzConfig;

    @CacheResult(cacheName = "time-cache")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public DateTime now(@Context UriInfo uriInfo,
                        @QueryParam("tz") String tz,
                        @NotBlank @HeaderParam("Cache-Control") String cacheControl
    ) {
        String timezone = tzConfig.timezone();
        if (Objects.nonNull(tz)) {
            timezone = tz;
        }
        Response resp = rest.target("http://worldclockapi.com")
                .path("/api/json/{timezone}/now")
                .resolveTemplate("timezone", timezone)
                .request(MediaType.APPLICATION_JSON)
                .get(Response.class);

        return resp.readEntity(DateTime.class);
    }

}
