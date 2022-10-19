package com.arroyo.cine.service;

import com.arroyo.cine.model.dto.UsuarioDto;
import com.arroyo.cine.model.entity.Usuario;
import com.arroyo.cine.model.mapper.usuario.UsuarioMapper;
import com.arroyo.cine.model.mapper.usuario.UsuarioMapperImpl;
import com.arroyo.cine.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UsuarioServiceTest {
    private static final UsuarioRepository repository = Mockito.mock(UsuarioRepository.class);
    private static final UsuarioMapper mapper = new UsuarioMapperImpl();
    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private static final UsuarioService service = new UsuarioService(repository, mapper, passwordEncoder);
    private static UsuarioDto usuarioDto;

    @BeforeAll
    static void beforeAll() {
        usuarioDto = new UsuarioDto(1, "admin", "admin@gmail.com", passwordEncoder.encode("admin"), "ROLE_ADMIN");
    }

    @Test
    void save() {
        Mockito.when(service.save(usuarioDto)).thenReturn(Map.of("Mensaje", "el usuario " + usuarioDto.usuario() + " se creo exitosamente"));
        assertEquals(Map.of("Mensaje", "el usuario " + usuarioDto.usuario() + " se creo exitosamente"), service.save(usuarioDto));
    }

    @Test
    void saveRolUser() {
        Mockito.when(service.saveRolUser(usuarioDto)).thenReturn(Map.of("Mensaje", "el usuario " + usuarioDto.usuario() + " se creo exitosamente"));
        assertEquals(Map.of("Mensaje", "el usuario " + usuarioDto.usuario() + " se creo exitosamente"), service.save(usuarioDto));
    }
}