package com.celisapp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	/* MEDIATYPE WITH QUERY PARANS
	 * @Override public void
	 * configureContentNegotiation(ContentNegotiationConfigurer configurer) {
	 * configurer.favorParameter(true)
	 * .parameterName("mediaType").ignoreAcceptHeader(true)
	 * .useRegisteredExtensionsOnly(false)
	 * .defaultContentType(MediaType.APPLICATION_JSON) .mediaType("json",
	 * MediaType.APPLICATION_JSON) .mediaType("xml", MediaType.APPLICATION_XML); }
	 */
	
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
			.allowedOrigins("*")
			.allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS", "HEAD", "TRACE", "CONNECT");
	}
	
	@Override
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
		configurer.favorParameter(false)
			.ignoreAcceptHeader(false)
			.useRegisteredExtensionsOnly(false)
			.defaultContentType(MediaType.APPLICATION_JSON)
				.mediaType("json", MediaType.APPLICATION_JSON)
				.mediaType("xml", MediaType.APPLICATION_XML);
	}
		
}
