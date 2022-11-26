package moe.zhi.listener


import io.quarkus.runtime.ShutdownEvent
import io.quarkus.runtime.StartupEvent
import org.eclipse.microprofile.config.inject.ConfigProperty
import org.slf4j.LoggerFactory
import javax.enterprise.context.ApplicationScoped
import javax.enterprise.event.Observes

/**
 * v1.0 fix me
 *
 * @author zhimoe
 * @version 1.0
 * @since 2022/6/23
 */
@ApplicationScoped
private class AppEventListener {
    val log = LoggerFactory.getLogger(this::class.java)

    @ConfigProperty(name = "app.city")
    lateinit var cities: List<String>

    fun onStart(@Observes event: StartupEvent?) {
        log.info("#### app started...")
        log.info("properties in file app.city= $cities")
    }

    fun onShutdown(@Observes event: ShutdownEvent?) {
        log.info("### app shutdown...")
    }
}