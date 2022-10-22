package com.ds.webflux.webapp.security.example.config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.ResourceHandlerRegistry;
import org.springframework.web.reactive.config.ViewResolverRegistry;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.thymeleaf.extras.springsecurity5.dialect.SpringSecurityDialect;
import org.thymeleaf.spring5.ISpringWebFluxTemplateEngine;
import org.thymeleaf.spring5.SpringWebFluxTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.reactive.ThymeleafReactiveViewResolver;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ITemplateResolver;

@Configuration
public class ThymeLeafConfig  implements ApplicationContextAware, WebFluxConfigurer {

	ApplicationContext context;
	
	@Override
	public void setApplicationContext(ApplicationContext context) {
		this.context = context;
	}

	@Bean
	public ITemplateResolver thymeleafTemplateResolver() {
		final SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
		resolver.setApplicationContext(this.context);
		resolver.setPrefix("classpath:templates/");
		resolver.setSuffix(".html");
		resolver.setTemplateMode(TemplateMode.HTML);
		resolver.setCacheable(false);
		resolver.setCheckExistence(false);
		return resolver;
	}
	
	@Bean
	public ISpringWebFluxTemplateEngine thymeleafTemplateEngine() {
		SpringWebFluxTemplateEngine templateEngine = new SpringWebFluxTemplateEngine();
		templateEngine.setTemplateResolver(thymeleafTemplateResolver());
	    templateEngine.setEnableSpringELCompiler(true);
	    templateEngine.addDialect(new SpringSecurityDialect());
		return templateEngine;
	}
	
	@Bean
	public ThymeleafReactiveViewResolver thymeleafReactiveViewResolver() {
		ThymeleafReactiveViewResolver viewResolver = new ThymeleafReactiveViewResolver();
		viewResolver.setTemplateEngine(thymeleafTemplateEngine());
		return viewResolver;
	}
	
	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		registry.viewResolver(thymeleafReactiveViewResolver());
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
	    registry.addResourceHandler("/js/**").addResourceLocations("classpath:/templates/static/js/");
	    registry.addResourceHandler("/images/**").addResourceLocations("classpath:/templates/static/images/");
	    registry.addResourceHandler("/css/**").addResourceLocations("classpath:/templates/static/css/");
	    registry.addResourceHandler("/all/**").addResourceLocations("classpath:/templates/static/all/");
	    registry.addResourceHandler("/dist/**").addResourceLocations("classpath:/templates/static/dist/");
	}
}