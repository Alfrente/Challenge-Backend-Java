package com.arroyo.cine.controller;

import com.arroyo.cine.dto.GeneroDto;
import com.arroyo.cine.service.GeneroService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/genero")
public class GeneroController {

    private final GeneroService service;

    public GeneroController(GeneroService service) {
        this.service = service;
    }

    @GetMapping
    @Operation(summary = "Trae los genero", responses = {
            @ApiResponse(responseCode = "200", description = "Petición exitosa."),
            @ApiResponse(responseCode = "400", description = "No se pudo completar la petición."),
            @ApiResponse(responseCode = "404", description = "Servicio no disponible.")
    }, description = "Este método trae todos los genero")
    public ResponseEntity<List<GeneroDto>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(service.getAll());
    }

    @GetMapping("/getById/{idGenero}")
    @Operation(summary = "Buscar con id", responses = {
            @ApiResponse(responseCode = "200", description = "Petición exitosa."),
            @ApiResponse(responseCode = "400", description = "No se pudo completar la petición."),
            @ApiResponse(responseCode = "404", description = "Servicio no disponible.")
    }, description = "Este método sirve para buscar un genero con el id")
    @Parameter(required = true, description = "Id genero", example = "1")
    public ResponseEntity<GeneroDto> getById(@PathVariable("idGenero") String idGenero) {
        return ResponseEntity.status(HttpStatus.OK).body(service.getById(idGenero));
    }

    @PostMapping
    @Operation(summary = "Guardar genero", responses = {
            @ApiResponse(responseCode = "201", description = "Se creo el genero."),
            @ApiResponse(responseCode = "400", description = "No se pudo crear el genero."),
            @ApiResponse(responseCode = "404", description = "Servicio no disponible.")
    }, description = "Este método es para guarda el genero ")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(required = true, description = "Entidad genero")
    public ResponseEntity<GeneroDto> save(@RequestBody @NotNull GeneroDto generoDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(generoDto));
    }

    @DeleteMapping
    @Operation(summary = "Eliminar genero", responses = {
            @ApiResponse(responseCode = "202", description = "Se elimino el genero."),
            @ApiResponse(responseCode = "400", description = "No se pudo eliminar el genero."),
            @ApiResponse(responseCode = "404", description = "Servicio no disponible.")
    }, description = "Este método sirve para eliminar el genero")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(required = true, description = "Entidad genero")
    public ResponseEntity<GeneroDto> delete(@RequestBody @NotNull GeneroDto generoDto) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.delete(generoDto));
    }

    @DeleteMapping("/deleteById/{idGenero}")
    @Operation(summary = "Eliminar genero con id", responses = {
            @ApiResponse(responseCode = "202", description = "Se elimino el genero."),
            @ApiResponse(responseCode = "400", description = "No se pudo eliminar el genero."),
            @ApiResponse(responseCode = "404", description = "Servicio no disponible.")
    }, description = "Este método sirve para eliminar el genero con el id")
    @Parameter(required = true, description = "Id genero", example = "1")
    public ResponseEntity<GeneroDto> deleteById(@PathVariable("idGenero") String idGenero) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.deleteById(idGenero));
    }

    @PutMapping("/update/{idGenero}/{nuevoGenero}")
    @Operation(summary = "Actualizar genero", responses = {
            @ApiResponse(responseCode = "202", description = "Se modifico el genero."),
            @ApiResponse(responseCode = "400", description = "No se pudo completar la petición."),
            @ApiResponse(responseCode = "404", description = "Servicio no disponible.")
    }, description = "Este método sirve para actualiza el genero")
    @Parameter(required = true, description = "Id genero", example = "1")
    @Parameter(required = true, description = "Nuevo genero", example = "Acción")
    public ResponseEntity<GeneroDto> update(@PathVariable("idGenero") String idGenero, @PathVariable("nuevoGenero") String nuevoGenero) {
        return ResponseEntity.status(HttpStatus.OK).body(service.update(idGenero, nuevoGenero));
    }
}
