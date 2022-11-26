package moe.zhi.controller

import io.quarkus.cache.CacheResult
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

@Path("/hello")
class GreetingResource {
    @CacheResult(cacheName = "hello-cache")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    fun hello(): String {
        return "Hello from RESTEasy"
    }
}