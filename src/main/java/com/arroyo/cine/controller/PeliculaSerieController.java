package com.arroyo.cine.controller;

import com.arroyo.cine.dto.PeliculaSerieDto;
import com.arroyo.cine.service.PeliculaSerieService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/peliculaSerie")
public class PeliculaSerieController {

    @Autowired
    private PeliculaSerieService service;

    @GetMapping
    @Operation(summary = "Trae las peliculas y series", description = "Este método trae todas las peliculas y series", responses = {
            @ApiResponse(responseCode = "200", description = "Petición exitosa."),
            @ApiResponse(responseCode = "404", description = "No se pudo completar la petición."),
    })
    public List<PeliculaSerieDto> getAll() {
        return service.getAll();
    }

    @GetMapping("/getById/{id}")
    @Operation(summary = "Buscar pelicula o serie", description = "Este método es para buscar una pelicula o serie con el id", responses = {
            @ApiResponse(responseCode = "200", description = "Petición exitosa."),
            @ApiResponse(responseCode = "404", description = "No se pudo completar la petición."),
    })
    @Parameter(required = true, description = "Id", example = "1")
    public PeliculaSerieDto getById(@PathVariable("id") Integer id) {
        return service.getById(id);
    }

    @PostMapping
    @Operation(summary = "Guardar", description = "Este método es para buscar una pelicula o serie con el id", responses = {
            @ApiResponse(responseCode = "201", description = "Se creo la serie o pelicula."),
            @ApiResponse(responseCode = "404", description = "No se pudo crear la serie o pelicula."),
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(required = true, description = "Entidad pelicula_serie")
    public PeliculaSerieDto save(@RequestBody PeliculaSerieDto peliculaSerieDto) {
        return service.save(peliculaSerieDto);
    }

    @PutMapping("/update/{id}")
    @Operation(summary = "Actualizar", description = "Este método es actualizar una pelicula o serie", responses = {
            @ApiResponse(responseCode = "202", description = "Se actualizo la serie o pelicula."),
            @ApiResponse(responseCode = "404", description = "No se pudo actualizo la serie o pelicula.")
    })
    @Parameter(required = true, description = "Id", example = "1")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(required = true, description = "Entidad pelicula_serie")
    public PeliculaSerieDto update(@PathVariable("id") Integer id, @RequestBody PeliculaSerieDto peliculaSerieDto) {
        return service.update(id, peliculaSerieDto);
    }

    @DeleteMapping
    @Operation(summary = "Eliminar", description = "Este método es para eliminar una pelicula o serie", responses = {
            @ApiResponse(responseCode = "202", description = "Se eliminar la serie o pelicula."),
            @ApiResponse(responseCode = "404", description = "No se pudo eliminar la serie o pelicula.")
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(required = true, description = "Entidad pelicula_serie")
    public PeliculaSerieDto delete(@RequestBody PeliculaSerieDto peliculaSerieDto) {
        return service.delete(peliculaSerieDto);
    }

    @DeleteMapping("/deleteById/{id}")
    @Operation(summary = "Eliminar con id", description = "Este método es para eliminar una pelicula o serie con el id", responses = {
            @ApiResponse(responseCode = "202", description = "Se eliminar la serie o pelicula."),
            @ApiResponse(responseCode = "404", description = "No se pudo eliminar la serie o pelicula.")
    })
    @Parameter(required = true, description = "Id", example = "1")
    public PeliculaSerieDto deleteById(@PathVariable("id") Integer id) {
        return service.deleteById(id);
    }
}
