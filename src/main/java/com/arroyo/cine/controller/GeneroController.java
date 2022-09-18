package com.arroyo.cine.controller;

import com.arroyo.cine.dto.GeneroDto;
import com.arroyo.cine.service.GeneroService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
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
    @Operation(summary = "Trae los genero", responses = {
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
    }, description = "Este método sirve para buscar un genero con el id")
    @Parameter(required = true, description = "Id genero", example = "1")
    public ResponseEntity<GeneroDto> getById(@PathVariable("idGenero") Integer idGenero) {
        return new ResponseEntity<>(generoService.getById(idGenero), HttpStatus.CREATED);
    }

    @PostMapping
    @Operation(summary = "Guardar genero", responses = {
            @ApiResponse(responseCode = "201", description = "Se creo el genero."),
            @ApiResponse(responseCode = "404", description = "No se pudo crear el genero."),
    }, description = "Este método es para guarda el genero ")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(required = true, description = "Entidad genero")
    public ResponseEntity<GeneroDto> save(@RequestBody GeneroDto generoDto) {
        return new ResponseEntity<>(generoService.save(generoDto), HttpStatus.CREATED);
    }

    @DeleteMapping
    @Operation(summary = "Eliminar genero", responses = {
            @ApiResponse(responseCode = "202", description = "Se elimino el genero."),
            @ApiResponse(responseCode = "404", description = "No se pudo eliminar el genero."),
    }, description = "Este método sirve para eliminar el genero")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(required = true, description = "Entidad genero")
    public ResponseEntity<GeneroDto> delete(@RequestBody GeneroDto generoDto) {
        return new ResponseEntity<>(generoService.delete(generoDto), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/deleteById/{idGenero}")
    @Operation(summary = "Eliminar genero con id", responses = {
            @ApiResponse(responseCode = "202", description = "Se elimino el genero."),
            @ApiResponse(responseCode = "404", description = "No se pudo eliminar el genero."),
    }, description = "Este método sirve para eliminar el genero con el id")
    @Parameter(required = true, description = "Id genero", example = "1")
    public ResponseEntity<GeneroDto> deleteById(@PathVariable("idGenero") Integer idGenero) {
        return new ResponseEntity<>(generoService.deleteById(idGenero), HttpStatus.ACCEPTED);
    }

    @PutMapping("/update/{idGenero}/{nuevoGenero}")
    @Operation(summary = "Actualizar genero", responses = {
            @ApiResponse(responseCode = "202", description = "Se modifico el genero."),
            @ApiResponse(responseCode = "404", description = "No se pudo completar la petición."),
    }, description = "Este método sirve para actualiza el genero")
    @Parameters({
            @Parameter(required = true, description = "Id genero", example = "1"),
            @Parameter(required = true, description = "Nuevo genero", example = "Acción")
    })
    public ResponseEntity<GeneroDto> update(@PathVariable("idGenero") Integer idGenero, @PathVariable("nuevoGenero") String nuevoGenero) {
        return new ResponseEntity<>(generoService.update(idGenero, nuevoGenero), HttpStatus.OK);
    }

}
