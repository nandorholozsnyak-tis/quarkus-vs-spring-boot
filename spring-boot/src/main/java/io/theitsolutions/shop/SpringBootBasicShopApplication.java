package io.theitsolutions.shop;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@SpringBootApplication
@EnableJpaRepositories
public class SpringBootBasicShopApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootBasicShopApplication.class, args);
    }

}

@Slf4j
@Component
class ConfigPrinter {

    @Value("${server.tomcat.threads.max}")
    Integer threadPoolSize;

    @Value("${spring.datasource.hikari.maximum-pool-size}")
    Integer datasourcePoolSize;

    @PostConstruct
    public void init() {
        log.info("server.tomcat.threads.max:[{}]", threadPoolSize);
        log.info("spring.datasource.hikari.maximum-pool-size:[{}]", datasourcePoolSize);
    }

}
