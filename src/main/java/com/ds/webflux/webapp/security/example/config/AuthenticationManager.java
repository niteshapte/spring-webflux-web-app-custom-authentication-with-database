package com.ds.webflux.webapp.security.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.ds.webflux.webapp.security.example.service.AuthenticationService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Component
public class AuthenticationManager implements ReactiveAuthenticationManager {
	
	private final AuthenticationService authenticationService;

	@Override
	public Mono<Authentication> authenticate(Authentication authentication) {
		return authenticationService.findByUsername(authentication.getPrincipal().toString())
			.switchIfEmpty(Mono.error(new UsernameNotFoundException("User not found")))
			.flatMap(user -> {
			    final String username           =   authentication.getPrincipal().toString();
			    final CharSequence rawPassword  =   authentication.getCredentials().toString();
			    if(passwordEncoder().matches(rawPassword, user.getPassword())) {
			    	UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username, user.getPassword(), user.getAuthorities());
			    	usernamePasswordAuthenticationToken.setDetails(user);
			    	return Mono.just(usernamePasswordAuthenticationToken);
			    }
			    return Mono.just(new UsernamePasswordAuthenticationToken(username, authentication.getCredentials()));
			});
	}

	@Bean 
	public PasswordEncoder passwordEncoder() { 
		return new BCryptPasswordEncoder(); 
	}
}
