package com.ds.webflux.webapp.security.example.config;

import org.springframework.security.web.server.context.WebSessionServerSecurityContextRepository;
import org.springframework.stereotype.Component;

@Component
public class SecurityContextRepository {

	public WebSessionServerSecurityContextRepository setSpringSecurityContextAttrName(String springSecurityContextAttrName) {
		final WebSessionServerSecurityContextRepository securityContextRepository = new WebSessionServerSecurityContextRepository();
        securityContextRepository.setSpringSecurityContextAttrName(springSecurityContextAttrName);
        return securityContextRepository;
    }
}
