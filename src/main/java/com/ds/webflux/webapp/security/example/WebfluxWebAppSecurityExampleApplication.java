package com.ds.webflux.webapp.security.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.reactive.config.EnableWebFlux;

@EnableWebFlux
@EnableAutoConfiguration
@ComponentScan("com.ds.webflux.webapp.security.example*")
@EnableCaching
@SpringBootApplication
public class WebfluxWebAppSecurityExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebfluxWebAppSecurityExampleApplication.class, args);
	}
}