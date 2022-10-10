package com.arroyo.cine.controller;

import com.arroyo.cine.model.dto.PersonajeDto;
import com.arroyo.cine.service.PersonajeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import java.util.List;

@RestController
public class PersonajeController {
    private final PersonajeService service;

    public PersonajeController(PersonajeService service) {
        this.service = service;
    }

    @GetMapping("characters")
    @Operation(summary = "Trae los personajes", description = "Este método trae todos los personajes tiene la opción de filtrar el resultado", responses = {
            @ApiResponse(responseCode = "200", description = "Petición exitosa."),
            @ApiResponse(responseCode = "400", description = "No se pudo completar la petición.")
    })
    @Parameter(description = "name", example = "Guerra z")
    @Parameter(description = "age", example = "15")
    @Parameter(description = "movies", example = "1")
    public ResponseEntity<List<PersonajeDto>> getAll(@RequestParam(required = false, name = "name") String name, @RequestParam(required = false, name = "age")
    String age, @RequestParam(required = false, name = "movies") String movies) {
        return new ResponseEntity<>(service.getAll(name, age, movies), HttpStatus.OK);
    }

    @GetMapping("/personaje/getById/{idPersona}")
    @Operation(summary = "Buscar con id", description = "Este método busca el personaje con el id", responses = {
            @ApiResponse(responseCode = "200", description = "Petición exitosa."),
            @ApiResponse(responseCode = "400", description = "No se pudo completar la petición.")
    })
    @Parameter(required = true, description = "Id persona", example = "1")
    public ResponseEntity<PersonajeDto> getById(@PathVariable("idPersona") String idPersona) {
        return new ResponseEntity<>(service.getById(idPersona), HttpStatus.OK);
    }

    @PostMapping("/personaje")
    @Operation(summary = "Guardar personaje", description = "Este método es para guarda un personaje", responses = {
            @ApiResponse(responseCode = "201", description = "Se creo el personaje."),
            @ApiResponse(responseCode = "400", description = "No se pudo crear el personaje.")
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(required = true, description = "Entidad personaje")
    public ResponseEntity<PersonajeDto> save(@RequestBody @NotBlank PersonajeDto personajeDto) {
        return new ResponseEntity<>(service.save(personajeDto), HttpStatus.CREATED);
    }

    @DeleteMapping("/personaje")
    @Operation(summary = "Eliminar personaje", description = "Este método es para eliminar el personaje", responses = {
            @ApiResponse(responseCode = "202", description = "Se elimino el personaje."),
            @ApiResponse(responseCode = "400", description = "No se pudo eliminar el personaje.")
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(required = true, description = "Entidad personaje")
    public ResponseEntity<PersonajeDto> delete(@RequestBody @NotBlank PersonajeDto personajeDto) {
        return new ResponseEntity<>(service.delete(personajeDto), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/personaje/deleteById/{idPersona}")
    @Operation(summary = "Eliminar personaje con id", description = "Este método es para eliminar el personaje con el id", responses = {
            @ApiResponse(responseCode = "202", description = "Se elimino el personaje."),
            @ApiResponse(responseCode = "400", description = "No se pudo eliminar el personaje.")
    })
    @Parameter(required = true, description = "Id persona", example = "1")
    public ResponseEntity<PersonajeDto> deleteById(@PathVariable("idPersona") String idPersona) {
        return new ResponseEntity<>(service.deleteById(idPersona), HttpStatus.ACCEPTED);
    }

    @PutMapping("/personaje/update/{idPersonaje}")
    @Operation(summary = "Actualizar personaje", description = "Este método es para actualizar el personaje", responses = {
            @ApiResponse(responseCode = "202", description = "Se actualizo el personaje."),
            @ApiResponse(responseCode = "400", description = "No se pudo actualizar el personaje.")
    })
    @Parameter(required = true, description = "Id personaje", example = "1")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(required = true, description = "Entidad personaje")
    public ResponseEntity<PersonajeDto> update(@PathVariable("idPersonaje") String idPersonaje, @RequestBody @NotBlank PersonajeDto personajeDto) {
        return new ResponseEntity<>(service.update(idPersonaje, personajeDto), HttpStatus.ACCEPTED);
    }
}
