package com.ds.webflux.webapp.security.example.repository;

import org.springframework.stereotype.Repository;

import com.ds.webflux.webapp.security.example.dto.db.UserDbDTO;

import reactor.core.publisher.Mono;

@Repository
public interface AuthenticationRepository {

	public  Mono<UserDbDTO> findByUsername(String username);
}