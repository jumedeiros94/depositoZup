package com.example.depositoZup;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Swagger OpenApi", version = "1", description = "Api desenvolvida para controle de produtos"))
public class DepositoZupApplication {

	public static void main(String[] args) {
		SpringApplication.run(DepositoZupApplication.class, args);
	}

}
