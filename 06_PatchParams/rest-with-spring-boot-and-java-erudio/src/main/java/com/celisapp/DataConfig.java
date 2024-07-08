package com.celisapp;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan({"com.celisapp"})
@EntityScan(basePackages = "com.celisapp")
@EnableJpaRepositories(basePackages = "com.celisapp")
public class DataConfig {
}
