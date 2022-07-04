package moe.zhi.listener;

import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import java.util.List;

/**
 * v1.0 fix me
 *
 * @author zhimoe
 * @version 1.0
 * @since 2022/6/23
 */
@ApplicationScoped
@Slf4j
public class AppEventListener {

    @ConfigProperty(name = "app.city")
    List<String> cities;

    void onStart(@Observes StartupEvent event) {
        log.info("#### app started...");
        log.info("properties in file app.city= " + cities);
    }

    void onShutdown(@Observes ShutdownEvent event) {
        log.info("### app shutdown...");
    }
}

