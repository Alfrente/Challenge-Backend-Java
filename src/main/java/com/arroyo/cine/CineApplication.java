package com.arroyo.cine;

import com.arroyo.cine.model.dto.UsuarioDto;
import com.arroyo.cine.service.UsuarioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import static com.arroyo.cine.service.validacion.imagen.GuardarImagen.crearDirectorio;

@SpringBootApplication
public class CineApplication {
    Logger logger = LoggerFactory.getLogger(CineApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(CineApplication.class, args);
    }

	@Bean
    CommandLineRunner lineRunner(UsuarioService service, PasswordEncoder encoder){
        logger.info("Se creo el usuario: user, contraseña: user");
        logger.info("Se creo el usuario: admin, contraseña: admin");
        crearDirectorio();
        return args -> {
            service.saveLineaComando(new UsuarioDto("user","user@gmail.com",encoder.encode("user"),"ROLE_USER"));
            service.saveLineaComando(new UsuarioDto("admin","admin@gmail.com",encoder.encode("admin"),"ROLE_ADMIN"));
		};
    }


}
