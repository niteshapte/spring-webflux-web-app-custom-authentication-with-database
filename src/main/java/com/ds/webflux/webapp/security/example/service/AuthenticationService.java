package com.ds.webflux.webapp.security.example.service;

import org.springframework.stereotype.Service;

import com.ds.webflux.webapp.security.example.dto.db.UserDbDTO;

import reactor.core.publisher.Mono;

@Service
public interface AuthenticationService {

	public  Mono<UserDbDTO> findByUsername(String username);
}