package com.ds.webflux.webapp.security.example.config;

import java.util.TimeZone;
import javax.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Configuration
public class PostConstructConfig {

	@PostConstruct
	public void init() {
		// Setting Spring Boot SetTimeZone
		log.info("Setting timezone for South Africa: " + TimeZone.getTimeZone("Africa/Johannesburg"));
		TimeZone.setDefault(TimeZone.getTimeZone("Africa/Johannesburg"));
	}
}