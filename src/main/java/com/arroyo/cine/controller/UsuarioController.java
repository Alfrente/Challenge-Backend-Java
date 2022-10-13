package com.arroyo.cine.controller;

import com.arroyo.cine.model.dto.UsuarioDto;
import com.arroyo.cine.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class UsuarioController {

    private final UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @PostMapping("/register")
    @Operation(summary = "Actualizar personaje", description = "Este método es para actualizar el personaje", responses = {
            @ApiResponse(responseCode = "200", description = "Usuario creado exitosamente."),
            @ApiResponse(responseCode = "400", description = "No se pudo crear el Usuario."),
            @ApiResponse(responseCode = "404", description = "Servicio no disponible.")
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(required = true, description = "Entidad usuario")
    public ResponseEntity<String> registrar(@RequestBody UsuarioDto dto) {
        service.saveRolUser(dto);
        return new ResponseEntity<>("Usuario creado exitosamente", HttpStatus.CREATED);
    }

    @PostMapping("/register/admin")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(summary = "Actualizar personaje", description = "Este método es para actualizar el personaje", responses = {
            @ApiResponse(responseCode = "200", description = "Usuario creado exitosamente."),
            @ApiResponse(responseCode = "400", description = "No se pudo crear el Usuario."),
            @ApiResponse(responseCode = "404", description = "Servicio no disponible.")
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(required = true, description = "Entidad usuario")
    public ResponseEntity<Map<String, String>> registrarAdmin(@RequestBody UsuarioDto dto) {
        service.save(dto);
        return new ResponseEntity<>(Map.of("Mensaje","Usuario creado exitosamente"), HttpStatus.CREATED);
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<UsuarioDto> getAdmin() {
        return new ResponseEntity<>(service.getById(), HttpStatus.OK);
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<UsuarioDto> getUser() {
        return new ResponseEntity<>(service.getById(), HttpStatus.OK);
    }
}
