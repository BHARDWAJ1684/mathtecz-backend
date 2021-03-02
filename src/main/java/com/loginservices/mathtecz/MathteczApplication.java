package com.loginservices.mathtecz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@ComponentScans({ @ComponentScan("com.loginservices.configuration"), @ComponentScan("com.loginservices.controller") })
@EnableJpaRepositories("com.loginservices.repository")
@EntityScan("com.loginservices.model")
@EnableWebSecurity(debug = true)
public class MathteczApplication {

	public static void main(String[] args) {
		SpringApplication.run(MathteczApplication.class, args);
	}

}
