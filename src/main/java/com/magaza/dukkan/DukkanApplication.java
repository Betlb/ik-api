package com.magaza.dukkan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.magaza.dukkan")
public class DukkanApplication {

	public static void main(String[] args) {
		SpringApplication.run(DukkanApplication.class, args);
	}

}
