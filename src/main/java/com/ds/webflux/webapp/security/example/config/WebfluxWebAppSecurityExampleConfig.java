package com.ds.webflux.webapp.security.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.RedirectServerAuthenticationFailureHandler;
import org.springframework.security.web.server.csrf.CookieServerCsrfTokenRepository;
import org.springframework.security.web.server.util.matcher.ServerWebExchangeMatchers;

import lombok.RequiredArgsConstructor;

@Order(value = Ordered.HIGHEST_PRECEDENCE)
@RequiredArgsConstructor
@Configuration
@EnableReactiveMethodSecurity
@EnableWebFluxSecurity
public class WebfluxWebAppSecurityExampleConfig {
	
	private final SecurityContextRepository securityContextRepository;
	
	private final AccessDeniedHandler accessDeniedHandler;
	
	private final AuthenticationManager authenticationManager;
	
	private final AuthenticationSuccessHandler authenticationSuccessHandler;
	
	private final LogoutSuccessHandler logoutSuccessHandler;
	
	@Bean
	public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
		return http
			.httpBasic().disable()
			.anonymous().disable()
			.authorizeExchange()
			.pathMatchers("/", "/forgot-password/").permitAll()
			.pathMatchers("/css/**").permitAll()
			.pathMatchers("/js/**").permitAll()
			.pathMatchers("/images/**").permitAll()
			.pathMatchers("/dist/**").permitAll()
			.pathMatchers("/cp/**").hasAuthority("SA")
			.anyExchange()  
			.authenticated()
			.and()
			.securityContextRepository(securityContextRepository.setSpringSecurityContextAttrName("demo-security-context"))
			.exceptionHandling()
			.accessDeniedHandler(accessDeniedHandler)
			.and()
			.formLogin()
				.loginPage("/")
				.requiresAuthenticationMatcher(ServerWebExchangeMatchers.pathMatchers(HttpMethod.POST, "/authenticate/"))
				.authenticationManager(authenticationManager)
				.authenticationSuccessHandler(authenticationSuccessHandler)
				.authenticationFailureHandler(new RedirectServerAuthenticationFailureHandler("/?error"))
			.and()
			.logout()
				.logoutUrl("/logout/")
				.requiresLogout(ServerWebExchangeMatchers.pathMatchers(HttpMethod.POST, "/logout/"))
				.logoutSuccessHandler(logoutSuccessHandler)
			.and().csrf(csrf -> csrf.csrfTokenRepository(CookieServerCsrfTokenRepository.withHttpOnlyFalse()))
			.build();
	}
}