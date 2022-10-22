package com.ds.webflux.webapp.security.example.controller;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.support.WebExchangeBindException;
import lombok.extern.log4j.Log4j2;
import reactor.core.publisher.Mono;

@Log4j2
@ControllerAdvice
public class WebfluxWebAppSecurityExampleControllerAdvice {

	@ExceptionHandler(WebExchangeBindException.class)
	public Mono<String> serverExceptionHandler(WebExchangeBindException e) {
		log.error("WebExchangeBindException occured. Exception {}", e);
		if(e.toString().contains("Validation failed")) {
			return Mono.just("error/400");
		} else {
			return Mono.just("error/500");
		}
	}
	
	@ExceptionHandler(AccessDeniedException.class)
	public Mono<String> serverExceptionHandler(AccessDeniedException e) {
		log.error("AccessDeniedException occured. Exception {}", e);
		return Mono.just("error/403");
	}
	
	@ExceptionHandler(Exception.class)
	public Mono<String> serverExceptionHandler(Exception e) {
		log.debug("Exception occured. Exception {}", e);
		
		if(e.getMessage().contains("Denied")) {
			return Mono.just("error/403");
		}
		return Mono.just("error/500");
	}
}