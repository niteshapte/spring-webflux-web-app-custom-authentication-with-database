package com.ds.webflux.webapp.security.example.config;

import java.net.URI;

import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.server.DefaultServerRedirectStrategy;
import org.springframework.security.web.server.ServerRedirectStrategy;
import org.springframework.security.web.server.WebFilterExchange;
import org.springframework.security.web.server.authentication.logout.RedirectServerLogoutSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.server.WebSession;

import reactor.core.publisher.Mono;

@Component
public class LogoutSuccessHandler extends RedirectServerLogoutSuccessHandler {

	private ServerRedirectStrategy redirectStrategy = new DefaultServerRedirectStrategy();
	
	@Override
    public Mono<Void> onLogoutSuccess(WebFilterExchange exchange, Authentication authentication) {
        ServerHttpResponse response = exchange.getExchange().getResponse();
        authentication.setAuthenticated(false);
        response.getCookies().remove("DEMO-SESSIONID");
        exchange.getExchange().getSession().flatMap(WebSession::invalidate).then();
        return this.redirectStrategy.sendRedirect(exchange.getExchange(), URI.create("/?logout"));
    }
}