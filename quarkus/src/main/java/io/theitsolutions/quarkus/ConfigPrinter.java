package io.theitsolutions.quarkus;

import io.quarkus.runtime.Startup;
import io.quarkus.runtime.StartupEvent;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;

@Slf4j
@ApplicationScoped
public class ConfigPrinter {

    @ConfigProperty(name = "quarkus.thread-pool.max-threads")
    Integer threadPoolSize;

    @ConfigProperty(name = "quarkus.datasource.jdbc.max-size")
    Integer datasourcePoolSize;

    void onStart(@Observes StartupEvent event) {
        log.info("quarkus.thread-pool.max-threads:[{}]", threadPoolSize);
        log.info("quarkus.datasource.jdbc.max-size:[{}]", datasourcePoolSize);
    }

}
