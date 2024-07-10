package com.celisapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenAPIConfig {
	
	@Bean
	public OpenAPI cunstomOpenAPI() {
		return new OpenAPI()
			.info(new Info()
					.title("Rest API Java Spring")
					.version("v1")
					.description("API Restfull with Java and Spring Boot udemy course")
					.termsOfService("com.br.celisaapp")
					.license(new License()
							.name("Apache 2.0")
							.url("celisapp.com.br")));
	}

}
