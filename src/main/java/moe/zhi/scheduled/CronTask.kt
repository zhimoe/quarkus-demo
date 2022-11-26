package moe.zhi.scheduled

import io.quarkus.scheduler.Scheduled
import io.quarkus.scheduler.ScheduledExecution
import moe.zhi.interceptor.Logit
import org.slf4j.LoggerFactory
import java.util.concurrent.atomic.AtomicInteger
import javax.enterprise.context.ApplicationScoped
import javax.ws.rs.client.Client
import javax.ws.rs.client.ClientBuilder
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

/**
 * v1.0 fix me
 *
 * @author zhimoe
 * @version 1.0
 * @since 2022/6/22
 */
@ApplicationScoped
class CronTask {
    private val log = LoggerFactory.getLogger(this::class.java)
    var count: AtomicInteger = AtomicInteger()
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
        log.info("### cron task get fired" + execution.getTrigger().getNextFireTime())
        log.info("### cron execution counted: " + get())
    }
}