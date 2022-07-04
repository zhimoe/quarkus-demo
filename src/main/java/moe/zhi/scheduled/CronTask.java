package moe.zhi.scheduled;

import javax.enterprise.context.ApplicationScoped;
import java.util.concurrent.atomic.AtomicInteger;

import io.quarkus.scheduler.Scheduled;
import io.quarkus.scheduler.ScheduledExecution;
import lombok.extern.slf4j.Slf4j;
import moe.zhi.intercepter.Logit;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * v1.0 fix me
 *
 * @author zhimoe
 * @version 1.0
 * @since 2022/6/22
 */
@ApplicationScoped
@Slf4j
public class CronTask {
    AtomicInteger count = new AtomicInteger();
    private Client rest = ClientBuilder.newClient();

    int get() {
        return count.get();
    }

    @Scheduled(every = "5m")
    @Logit
    void getCurrentTime(ScheduledExecution execution) {

        Response resp = rest.target("http://worldclockapi.com")
                .path("/api/json/{timezone}/now")
                .resolveTemplate("timezone", "utc")
                .request(MediaType.APPLICATION_JSON)
                .get(Response.class);

        System.out.println(resp.readEntity(String.class));


        count.incrementAndGet();
        log.info("### cron task get fired" + execution.getTrigger().getNextFireTime());
        log.info("### cron execution counted: " + get());
    }
}
