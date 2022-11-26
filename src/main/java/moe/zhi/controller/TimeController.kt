package moe.zhi.controller

import io.quarkus.cache.CacheResult
import moe.zhi.config.TimezoneConfig
import moe.zhi.model.DateTime
import java.util.*
import javax.inject.Inject
import javax.validation.constraints.NotBlank
import javax.ws.rs.*
import javax.ws.rs.client.ClientBuilder
import javax.ws.rs.core.Context
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response
import javax.ws.rs.core.UriInfo

/**
 * v1.0 fix me
 *
 * @author zhimoe
 * @version 1.0
 * @since 2022/7/3
 */
@Path("/now")
class TimeController {
    private val rest = ClientBuilder.newClient()

    @Inject
    lateinit var tzConfig: TimezoneConfig

    @CacheResult(cacheName = "time-cache")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    fun now(
        @Context uriInfo: UriInfo?,
        @QueryParam("tz") tz: String?,
        @HeaderParam("Cache-Control") cacheControl: @NotBlank String?
    ): DateTime {
        var timezone = tzConfig!!.timezone()
        if (Objects.nonNull(tz)) {
            timezone = tz
        }
        val resp = rest.target("http://worldclockapi.com")
            .path("/api/json/{timezone}/now")
            .resolveTemplate("timezone", timezone)
            .request(MediaType.APPLICATION_JSON)
            .get(Response::class.java)
        return resp.readEntity(DateTime::class.java)
    }
}