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
    @Operation(summary = "Crear usuario", description = "Crea un nuevo usuario", responses = {
            @ApiResponse(responseCode = "200", description = "Se creo el usuario."),
            @ApiResponse(responseCode = "400", description = "No se creo el Usuario."),
            @ApiResponse(responseCode = "404", description = "Servicio no disponible.")
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(required = true, description = "Entidad usuario")
    public ResponseEntity<Map<String, String>> registrar(@RequestBody UsuarioDto dto) {
        return new ResponseEntity<>(service.saveRolUser(dto), HttpStatus.CREATED);
    }

    @PostMapping("/register/admin")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(summary = "Crear usuario", description = "Crea un nuevo usuario con el rol", responses = {
            @ApiResponse(responseCode = "200", description = "Se creo el usuario."),
            @ApiResponse(responseCode = "400", description = "No se creo el Usuario."),
            @ApiResponse(responseCode = "404", description = "Servicio no disponible.")
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(required = true, description = "Entidad usuario")
    public ResponseEntity<Map<String, String>> registrarAdmin(@RequestBody UsuarioDto dto) {
        return new ResponseEntity<>(service.save(dto), HttpStatus.CREATED);
    }
}
