package com.arroyo.cine.controller;

import com.arroyo.cine.model.dto.GeneroDto;
import com.arroyo.cine.service.GeneroService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    @Operation(summary = "Lista de genero", description = "Devuelve la lista de genero", responses = {
            @ApiResponse(responseCode = "200", description = "Petición exitosa."),
            @ApiResponse(responseCode = "400", description = "No se pudo completar la petición."),
            @ApiResponse(responseCode = "404", description = "Servicio no disponible.")
    })
    public ResponseEntity<List<GeneroDto>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(service.getAll());
    }

    @GetMapping("/getById/{idGenero}")
    @Operation(summary = "Devuelve un genero", description = "Devuelve un genero específico", responses = {
            @ApiResponse(responseCode = "200", description = "Petición exitosa."),
            @ApiResponse(responseCode = "400", description = "No se completo la petición."),
            @ApiResponse(responseCode = "404", description = "Servicio no disponible.")
    })
    @Parameter(required = true, description = "Id genero", example = "1")
    public ResponseEntity<GeneroDto> getById(@PathVariable("idGenero") String idGenero) {
        return ResponseEntity.status(HttpStatus.OK).body(service.getById(idGenero));
    }

    @PostMapping
    @Operation(summary = "Guardar genero", description = "Guarda un genero específico", responses = {
            @ApiResponse(responseCode = "201", description = "Se guardo un genero."),
            @ApiResponse(responseCode = "400", description = "No se guardo el genero."),
            @ApiResponse(responseCode = "404", description = "Servicio no disponible.")
    })
    @Parameter(description = "nombre", required = true)
    @Parameter(description = "imagen")
    public ResponseEntity<GeneroDto> save(
            @RequestParam(value = "nombre", required = false) String nombre,
            @RequestParam(value = "imagen", required = false) MultipartFile imagen
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(nombre, imagen));
    }

    @DeleteMapping
    @Operation(summary = "Eliminar genero", description = "Elimina un genero específico", responses = {
            @ApiResponse(responseCode = "202", description = "Se elimino el genero."),
            @ApiResponse(responseCode = "400", description = "No se elimino el genero."),
            @ApiResponse(responseCode = "404", description = "Servicio no disponible.")
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(required = true, description = "Entidad genero")
    public ResponseEntity<GeneroDto> delete(@RequestBody @NotNull GeneroDto generoDto) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.delete(generoDto));
    }

    @DeleteMapping("/deleteById/{idGenero}")
    @Operation(summary = "Eliminar genero", description = "Elimina un genero específico", responses = {
            @ApiResponse(responseCode = "202", description = "Se elimino el genero."),
            @ApiResponse(responseCode = "400", description = "No se elimino el genero."),
            @ApiResponse(responseCode = "404", description = "Servicio no disponible.")
    })
    @Parameter(required = true, description = "Id genero", example = "1")
    public ResponseEntity<GeneroDto> deleteById(@PathVariable("idGenero") String idGenero) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.deleteById(idGenero));
    }

    @PutMapping("/update/{idGenero}")
    @Operation(summary = "Actualizar genero", description = "Actualiza un genero específico", responses = {
            @ApiResponse(responseCode = "202", description = "Se actualizo el genero."),
            @ApiResponse(responseCode = "400", description = "No se completo la petición."),
            @ApiResponse(responseCode = "404", description = "Servicio no disponible.")
    })
    @Parameter(description = "Id genero", example = "1", required = true)
    @Parameter(description = "nombre", example = "Acción", required = true)
    @Parameter(description = "nombre", example = "Acción")
    public ResponseEntity<GeneroDto> update(
            @PathVariable("idGenero") String idGenero,
            @RequestParam(value = "nombre", required = false) String nombre,
            @RequestParam(value = "imagen", required = false) MultipartFile imagen
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(service.update(idGenero, nombre, imagen));
    }
}
