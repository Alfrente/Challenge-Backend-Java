package com.arroyo.cine;

import com.arroyo.cine.model.entity.Usuario;
import com.arroyo.cine.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class CineApplication {

	public static void main(String[] args) {
		SpringApplication.run(CineApplication.class, args);
	}

	/*@Bean
	CommandLineRunner lineRunner(UsuarioRepository repository, PasswordEncoder encoder){
		return args -> {
			repository.save(new Usuario("user","user@gmail.com",encoder.encode("user"),"ROLE_USER"));
			repository.save(new Usuario("admin","admin@gmail.com",encoder.encode("admin"),"ROLE_ADMIN"));
		};
	}*/

}
