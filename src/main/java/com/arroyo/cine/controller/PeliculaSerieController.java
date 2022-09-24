package com.arroyo.cine.controller;

import com.arroyo.cine.dto.pelicula_serie.PeliculaSerieDto;
import com.arroyo.cine.dto.pelicula_serie.PeliculaSeriePersonalizadoPsDto;
import com.arroyo.cine.service.PeliculaSerieService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import java.util.List;

@RestController
public class PeliculaSerieController {

    @Autowired
    private PeliculaSerieService service;

    @GetMapping("/movies")
    @Operation(summary = "Trae las peliculas y series", description = "Este método trae todas las peliculas y series tiene la opción de filtrar el resultado", responses = {
            @ApiResponse(responseCode = "200", description = "Petición exitosa."),
            @ApiResponse(responseCode = "404", description = "No se pudo completar la petición."),
    })
    @Parameter(description = "name", example = "Guerra z")
    @Parameter(description = "genre", example = "1")
    @Parameter(description = "order", example = "ASC")
    public ResponseEntity<List<PeliculaSerieDto>> getAll(@RequestParam(required = false, name = "name") @Null String name, @RequestParam(required = false, name = "genre")
    @Null Integer genre, @RequestParam(required = false, name = "order") @Null String order) {
        return new ResponseEntity<>(service.getAllPersonalizado(name, genre, order), HttpStatus.OK);
    }

    @GetMapping("/peliculaSerie/getAll")
    @Operation(summary = "Trae las peliculas y series", description = "Este método trae todas las peliculas y series tiene la opción de filtrar el resultado", responses = {
            @ApiResponse(responseCode = "200", description = "Petición exitosa."),
            @ApiResponse(responseCode = "404", description = "No se pudo completar la petición."),
    })
    @Parameter(description = "name", example = "Guerra z")
    @Parameter(description = "genre", example = "1")
    @Parameter(description = "order", example = "ASC")
    public ResponseEntity<List<PeliculaSeriePersonalizadoPsDto>> getAllPersonalizado(@RequestParam(required = false, name = "name") @Null String name, @RequestParam(required = false, name = "genre")
    @Null Integer genre, @RequestParam(required = false, name = "order") @Null String order) {
        return new ResponseEntity<>(service.getAll(name, genre, order), HttpStatus.OK);
    }

    @GetMapping("/peliculaSerie/getById/{id}")
    @Operation(summary = "Buscar pelicula o serie", description = "Este método es para buscar una pelicula o serie con el id", responses = {
            @ApiResponse(responseCode = "200", description = "Petición exitosa."),
            @ApiResponse(responseCode = "404", description = "No se pudo completar la petición."),
    })
    @Parameter(required = true, description = "Id", example = "1")
    public ResponseEntity<PeliculaSerieDto> getById(@PathVariable("id") @NotBlank Integer id) {
        return new ResponseEntity<>(service.getById(id), HttpStatus.OK);
    }

    @PostMapping("/peliculaSerie")
    @Operation(summary = "Guardar", description = "Este método es para buscar una pelicula o serie con el id", responses = {
            @ApiResponse(responseCode = "201", description = "Se creo la serie o pelicula."),
            @ApiResponse(responseCode = "404", description = "No se pudo crear la serie o pelicula."),
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(required = true, description = "Entidad pelicula_serie")
    public ResponseEntity<PeliculaSerieDto> save(@RequestBody @NotBlank PeliculaSerieDto peliculaSerieDto) {
        return new ResponseEntity<>(service.save(peliculaSerieDto), HttpStatus.CREATED);
    }

    @PostMapping("/movies/{idMovie}/characters/{idCharacter}")
    @Operation(summary = "Guardar", description = "Este método es para buscar una pelicula o serie con el id", responses = {
            @ApiResponse(responseCode = "201", description = "Se creo la serie o pelicula."),
            @ApiResponse(responseCode = "404", description = "No se pudo crear la serie o pelicula."),
    })
    @Parameter(description = "idMovie", required = true, example = "1")
    @Parameter(description = "idCharacter", required = true, example = "2")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void savePersonalizado(@PathVariable("idMovie") @NotBlank Integer idMovie, @PathVariable("idCharacter") @NotBlank Integer idCharacter) {
        service.savePersonalizado(idMovie, idCharacter);
    }

    @PutMapping("/peliculaSerie/update/{id}")
    @Operation(summary = "Actualizar", description = "Este método es actualizar una pelicula o serie", responses = {
            @ApiResponse(responseCode = "202", description = "Se actualizo la serie o pelicula."),
            @ApiResponse(responseCode = "404", description = "No se pudo actualizo la serie o pelicula.")
    })
    @Parameter(required = true, description = "Id", example = "1")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(required = true, description = "Entidad pelicula_serie")
    public ResponseEntity<PeliculaSerieDto> update(@PathVariable("id") Integer id, @RequestBody @NotBlank PeliculaSerieDto peliculaSerieDto) {
        return new ResponseEntity<>(service.update(id, peliculaSerieDto), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/peliculaSerie")
    @Operation(summary = "Eliminar", description = "Este método es para eliminar una pelicula o serie", responses = {
            @ApiResponse(responseCode = "202", description = "Se eliminar la serie o pelicula."),
            @ApiResponse(responseCode = "404", description = "No se pudo eliminar la serie o pelicula.")
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(required = true, description = "Entidad pelicula_serie")
    public ResponseEntity<PeliculaSerieDto> delete(@RequestBody @NotBlank PeliculaSerieDto peliculaSerieDto) {
        return new ResponseEntity<>(service.delete(peliculaSerieDto), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/movies/{idMovie}/characters/{idCharacter}")
    @Operation(summary = "Eliminar", description = "Este método es para eliminar una pelicula o serie", responses = {
            @ApiResponse(responseCode = "202", description = "Se eliminar la serie o pelicula."),
            @ApiResponse(responseCode = "404", description = "No se pudo eliminar la serie o pelicula.")
    })
    @Parameter(description = "idMovie", required = true, example = "1")
    @Parameter(description = "idCharacter", required = true, example = "2")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void delete(@PathVariable("idMovie") @NotBlank Integer idMovie, @PathVariable("idCharacter") @NotBlank Integer idCharacter) {
        service.deletePersonalizado(idMovie, idCharacter);
    }

    @DeleteMapping("/peliculaSerie/deleteById/{id}")
    @Operation(summary = "Eliminar con id", description = "Este método es para eliminar una pelicula o serie con el id", responses = {
            @ApiResponse(responseCode = "202", description = "Se eliminar la serie o pelicula."),
            @ApiResponse(responseCode = "404", description = "No se pudo eliminar la serie o pelicula.")
    })
    @Parameter(required = true, description = "Id", example = "1")
    public ResponseEntity<PeliculaSerieDto> deleteById(@PathVariable("id") @NotBlank Integer id) {
        return new ResponseEntity<>(service.deleteById(id), HttpStatus.ACCEPTED);
    }
}
