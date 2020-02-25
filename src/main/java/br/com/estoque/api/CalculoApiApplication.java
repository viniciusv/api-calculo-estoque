package br.com.estoque.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class CalculoApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(CalculoApiApplication.class, args);
	}

}
