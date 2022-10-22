package com.ds.webflux.webapp.security.example.service;

import org.springframework.stereotype.Service;

import com.ds.webflux.webapp.security.example.dto.db.UserDbDTO;
import com.ds.webflux.webapp.security.example.repository.AuthenticationRepository;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class AuthenticationServiceImpl implements AuthenticationService {

	private final AuthenticationRepository repository;
	
	@Override
	public Mono<UserDbDTO> findByUsername(String username) {
		return repository.findByUsername(username);
	}
}