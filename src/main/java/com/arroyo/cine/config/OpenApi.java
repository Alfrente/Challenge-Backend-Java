package com.arroyo.cine.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Backend Java Spring Boot",
                version = "1.0",
                description = "Es un challenge backend desarrollado en Java con Spring Boot, En el que hay que desarrollar una api en la que se guarda información " +
                        "de película.",
                contact = @Contact(
                        name = "Idelfonso Arroyo Hernandez",
                        email = "arroyolokura@gmail.com"
                )
        ),
        servers = @Server(
                url = "http://localhost:8090",
                description = "Servidor local"
        )
)
public class OpenApi {


}
