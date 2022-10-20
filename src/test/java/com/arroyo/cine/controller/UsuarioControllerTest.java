package com.arroyo.cine.controller;

import com.arroyo.cine.model.dto.UsuarioDto;
import com.arroyo.cine.service.UsuarioService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class UsuarioControllerTest {
    private static final UsuarioService service = Mockito.mock(UsuarioService.class);
    private static final UsuarioController controller = new UsuarioController(service);
    private static UsuarioDto usuarioDto;

    @BeforeAll
    static void beforeAll() {
        usuarioDto = new UsuarioDto(1, "admin", "admin@gmail.com", "admin", "ROLE_ADMIN");
    }

    @Test
    void registrar() {
        Mockito.when(service.save(usuarioDto)).thenReturn(Map.of("Mensaje", "el usuario " + usuarioDto.usuario() + " se creo exitosamente"));
        assertEquals(new ResponseEntity<>(Map.of("Mensaje", "el usuario " + usuarioDto.usuario() + " se creo exitosamente"),HttpStatus.CREATED), controller.registrarAdmin(usuarioDto));
    }

    @Test
    void registrarAdmin() {
        Mockito.when(service.saveRolUser(usuarioDto)).thenReturn(Map.of("Mensaje", "el usuario " + usuarioDto.usuario() + " se creo exitosamente"));
        assertEquals(new ResponseEntity<>(Map.of("Mensaje", "el usuario " + usuarioDto.usuario() + " se creo exitosamente"),HttpStatus.CREATED), controller.registrar(usuarioDto));
    }
}