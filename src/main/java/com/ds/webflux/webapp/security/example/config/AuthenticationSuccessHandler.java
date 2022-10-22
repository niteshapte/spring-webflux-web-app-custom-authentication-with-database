package com.ds.webflux.webapp.security.example.config;

import java.net.URI;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.server.DefaultServerRedirectStrategy;
import org.springframework.security.web.server.ServerRedirectStrategy;
import org.springframework.security.web.server.WebFilterExchange;
import org.springframework.security.web.server.authentication.RedirectServerAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

@Component
public class AuthenticationSuccessHandler extends RedirectServerAuthenticationSuccessHandler {

	private URI location = URI.create("/user/dashboard/");

	private ServerRedirectStrategy redirectStrategy = new DefaultServerRedirectStrategy();

	@Override
	public Mono<Void> onAuthenticationSuccess(WebFilterExchange webFilterExchange, Authentication authentication) {
		ServerWebExchange exchange = webFilterExchange.getExchange();
		if(!authentication.isAuthenticated()) {
			return this.redirectStrategy.sendRedirect(exchange, URI.create("/?error"));
		} 
		webFilterExchange.getChain().filter(exchange);
		return this.redirectStrategy.sendRedirect(exchange, location);
	}
}
