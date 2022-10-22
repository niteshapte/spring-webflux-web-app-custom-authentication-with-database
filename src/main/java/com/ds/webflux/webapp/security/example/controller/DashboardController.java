package com.ds.webflux.webapp.security.example.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.server.ServerWebExchange;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Controller
@RequestMapping("/user")
public class DashboardController {

	@PreAuthorize("hasAuthority('SA')")
	@RequestMapping(value = "/dashboard/", method = RequestMethod.GET)
	public Mono<String> dashboard(Model model, ServerWebExchange exchange, Mono<Authentication> authentication) {
		model.addAttribute("title", "User - Dashboard");
		return Mono.just("dashboard");
	}
}
