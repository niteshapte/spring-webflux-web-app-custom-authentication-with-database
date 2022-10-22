package com.ds.webflux.webapp.security.example.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.server.ServerWebExchange;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Controller
@RequiredArgsConstructor
public class HomeController {

	@RequestMapping(value = "/", method = RequestMethod.GET, produces = "text/html; charset=utf-8")
	public Mono<String> home(Mono<Authentication> authentication) {
		return authentication.map(Authentication::isAuthenticated).map(redirect -> String.format("%s", "redirect:/cp/dashboard/highlights/")).defaultIfEmpty("login");
	}

	@RequestMapping(value = "/forgot-password/", method = RequestMethod.GET)
	public Mono<String> showForgotPasswordPage(ServerWebExchange exchange, Mono<Authentication> authentication) {
		return authentication.map(Authentication::isAuthenticated).map(redirect -> String.format("%s", "redirect:/cp/dashboard/highlights/")).defaultIfEmpty("forgot-password");
	}
}