package moe.zhi.scheduled

import io.quarkus.scheduler.Scheduled
import io.quarkus.scheduler.ScheduledExecution
import moe.zhi.interceptor.Logit
import org.slf4j.LoggerFactory
import java.util.concurrent.atomic.AtomicInteger
import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject
import jakarta.ws.rs.client.Client
import jakarta.ws.rs.client.ClientBuilder
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response
import org.jboss.logging.Logger

/**
 * v1.0 fix me
 *
 * @author zhimoe
 * @version 1.0
 * @since 2022/6/22
 */
@ApplicationScoped
class CronTask {

    //    @Inject
    //    private lateinit var log: Logger

    val log: org.slf4j.Logger = LoggerFactory.getLogger(this::class.java)


    private var count: AtomicInteger = AtomicInteger()

    private val rest: Client = ClientBuilder.newClient()

    fun get(): Int {
        return count.get()
    }

    @Scheduled(every = "5m")
    @Logit
    fun getCurrentTime(execution: ScheduledExecution) {
        val resp = rest.target("http://worldclockapi.com")
            .path("/api/json/{timezone}/now")
            .resolveTemplate("timezone", "utc")
            .request(MediaType.APPLICATION_JSON)
            .get(Response::class.java)
        println(resp.readEntity(String::class.java))
        count.incrementAndGet()
        log.info("### cron task get fired" + execution.trigger.nextFireTime)
        log.info("### cron execution counted: " + get())
    }
}