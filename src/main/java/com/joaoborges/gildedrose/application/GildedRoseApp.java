package com.joaoborges.gildedrose.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

/**
 * Main application class.
 *
 * @author joaoborges
 */
@SpringBootApplication
@ComponentScan("com.joaoborges.gildedrose")
public class GildedRoseApp {

    public static void main(String[] args) {
        SpringApplication.run(GildedRoseApp.class, args);
    }

    @Bean
    public WebServerFactoryCustomizer<ConfigurableWebServerFactory> serverPortCustomizer() {
        return factory -> factory.setPort(8081);
    }
}
