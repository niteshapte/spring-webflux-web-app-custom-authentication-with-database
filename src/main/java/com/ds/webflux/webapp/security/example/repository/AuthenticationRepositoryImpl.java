package com.ds.webflux.webapp.security.example.repository;

import org.springframework.data.r2dbc.convert.R2dbcConverter;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Repository;

import com.ds.webflux.webapp.security.example.dto.db.UserDbDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Repository
public class AuthenticationRepositoryImpl implements AuthenticationRepository {

	private final DatabaseClient databaseClient;
	
	private final R2dbcConverter converter;
	
	public Mono<UserDbDTO> findByUsername(String username) {
		String sql = "SELECT a.id, a.username, a.password, a.user_uid, a.first_name, a.last_name, a.is_active, a.is_deleted, b.access_level, b.access_name, a.access_level_id FROM demo.users a, demo.access_level b WHERE a.username = :username AND a.access_level_id = b.id";
		return databaseClient.sql(sql)
				.bind("username", username)
				.map((k,v) -> converter.read(UserDbDTO.class, k, v)).one();
		
		/** OR **/
		/*return databaseClient
			.sql(sql.trim())
			.bind("username", username)
			.fetch()
			.first()
			.flatMap(um -> Mono.just(new ObjectMapper().convertValue(um, UserDbDTO.class)));*/
	}
}
