package moe.zhi.controller

import io.quarkus.cache.CacheResult
import jakarta.ws.rs.GET
import jakarta.ws.rs.Path
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.MediaType

@Path("/hello")
class GreetingResource {
    @CacheResult(cacheName = "hello-cache")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    fun hello(): String {
        return "Hello from RESTEasy"
    }
}