package com.ds.webflux.webapp.security.example.config;

import java.util.concurrent.ConcurrentHashMap;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.ReactiveMapSessionRepository;
import org.springframework.session.ReactiveSessionRepository;
import org.springframework.session.config.annotation.web.server.EnableSpringWebSession;
import org.springframework.web.server.session.CookieWebSessionIdResolver;
import org.springframework.web.server.session.WebSessionIdResolver;

@Configuration
@EnableSpringWebSession
public class SessionConfig {

	@Bean
    public ReactiveSessionRepository<?> reactiveSessionRepository() {
        return new ReactiveMapSessionRepository(new ConcurrentHashMap<>());
    }
	
	@Bean
	public WebSessionIdResolver webSessionIdResolver() {
		CookieWebSessionIdResolver resolver = new CookieWebSessionIdResolver();
		resolver.setCookieName("DEMO-SESSIONID"); 
		resolver.addCookieInitializer((builder) -> builder.path("/")); 
		resolver.addCookieInitializer((builder) -> builder.sameSite("Strict")); 
		return resolver;
	}
}