package com.arroyo.cine.controller;

import com.arroyo.cine.dto.GeneroDto;
import com.arroyo.cine.service.GeneroService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/genero")
public class GeneroController {

    @Autowired
    private GeneroService generoService;

    @GetMapping
    @Operation(summary = "Trae todo", responses = {
            @ApiResponse(responseCode = "200", description = "Petición exitosa."),
            @ApiResponse(responseCode = "404", description = "No se pudo completar la petición."),
    }, description = "Este método trae todos los genero")
    public List<GeneroDto> getAll() {
        return generoService.getAll();
    }

    @GetMapping("/getById/{idGenero}")
    @Operation(summary = "Buscar con id", responses = {
            @ApiResponse(responseCode = "200", description = "Petición exitosa."),
            @ApiResponse(responseCode = "404", description = "No se pudo completar la petición."),
    }, description = "Este método elimina el genero pasándolo la entidad")
    @Parameter(required = true, description = "Id del genero", example = "1")
    public ResponseEntity<GeneroDto> getById(@PathVariable("idGenero") Integer idGenero) {
        return new ResponseEntity<>(generoService.getById(idGenero), HttpStatus.CREATED);
    }

    @PostMapping
    @Operation(summary = "Guardar genero", responses = {
            @ApiResponse(responseCode = "201", description = "Se creo el genero exitosa."),
            @ApiResponse(responseCode = "404", description = "No se pudo completar la petición de crear genero."),
    }, description = "Este método es para guarda el genero ")
    @Parameter(required = true, description = "Entidad genero", example = "{\"genero\": 1, \"persona\": 1, \"nombre\": Acción, \"imagen\": c:\\imagen\\terror.jpg}")
    public ResponseEntity<GeneroDto> save(@RequestBody GeneroDto generoDto) {
        return new ResponseEntity<>(generoService.save(generoDto), HttpStatus.CREATED);
    }

    @PutMapping
    @Operation(summary = "Borrar genero", responses = {
            @ApiResponse(responseCode = "202", description = "Petición exitosa."),
            @ApiResponse(responseCode = "404", description = "No se pudo completar la petición."),
    }, description = "Este método elimina el genero pasándolo la entidad")
    @Parameter(required = true, description = "Entidad genero", example = "{\"genero\": 1, \"persona\": 1, \"nombre\": Acción, \"imagen\": c:\\imagen\\terror.jpg}")
    public ResponseEntity<GeneroDto> delete(@RequestBody GeneroDto generoDto) {
        return new ResponseEntity<>(generoService.delete(generoDto), HttpStatus.ACCEPTED);
    }

    @PatchMapping("/deleteById/{idGenero}")
    @Operation(summary = "Borrar genero con id", responses = {
            @ApiResponse(responseCode = "202", description = "Petición exitosa."),
            @ApiResponse(responseCode = "404", description = "No se pudo completar la petición."),
    }, description = "Este método elimina el genero de acuerdo al id")
    @Parameter(required = true, description = "Id del genero", example = "1")
    public ResponseEntity<GeneroDto> deleteById(@PathVariable("idGenero") Integer idGenero) {
        return new ResponseEntity<>(generoService.deleteById(idGenero), HttpStatus.ACCEPTED);
    }

}
