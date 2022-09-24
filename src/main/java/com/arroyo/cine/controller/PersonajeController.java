package com.arroyo.cine.controller;

import com.arroyo.cine.dto.personaje.PersonajeDto;
import com.arroyo.cine.dto.personaje.PersonajePersonalizadoPDto;
import com.arroyo.cine.service.PersonajeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import java.util.List;

@RestController
public class PersonajeController {

    @Autowired
    private PersonajeService personajeService;

    @GetMapping("characters")
    @Operation(summary = "Trae los personajes", description = "Este método trae todos los personajes tiene la opción de filtrar el resultado", responses = {
            @ApiResponse(responseCode = "200", description = "Petición exitosa."),
            @ApiResponse(responseCode = "404", description = "No se pudo completar la petición.")
    })
    @Parameter(description = "name", example = "Guerra z")
    @Parameter(description = "age", example = "15")
    @Parameter(description = "movies", example = "1")
    public ResponseEntity<List<PersonajeDto>> getAll(@RequestParam(required = false, name = "name") String name, @RequestParam(required = false, name = "age")
    Byte age, @RequestParam(required = false, name = "movies") Integer movies) {
        return new ResponseEntity<>(personajeService.getAll(name, age, movies), HttpStatus.OK);
    }

    @GetMapping("/personaje/getAll")
    @Operation(summary = "Trae los personajes", description = "Este método trae todos los personajes tiene la opción de filtrar el resultado", responses = {
            @ApiResponse(responseCode = "200", description = "Petición exitosa."),
            @ApiResponse(responseCode = "404", description = "No se pudo completar la petición.")
    })
    @Parameter(description = "name", example = "Guerra z")
    @Parameter(description = "age", example = "15")
    @Parameter(description = "movies", example = "1")
    public ResponseEntity<List<PersonajePersonalizadoPDto>> getAllPersonalizado(@RequestParam(required = false, name = "name") String name, @RequestParam(required = false, name = "age")
    Byte age, @RequestParam(required = false, name = "movies") Integer movies) {
        return new ResponseEntity<>(personajeService.getAllPersonalizado(name, age, movies), HttpStatus.OK);
    }

    @GetMapping("/personaje/getById/{idPersona}")
    @Operation(summary = "Buscar con id", description = "Este método busca el personaje con el id", responses = {
            @ApiResponse(responseCode = "200", description = "Petición exitosa."),
            @ApiResponse(responseCode = "404", description = "No se pudo completar la petición.")
    })
    @Parameter(required = true, description = "Id persona", example = "1")
    public ResponseEntity<PersonajeDto> getById(@PathVariable("idPersona") @NotBlank Integer idPersona) {
        return new ResponseEntity<>(personajeService.getById(idPersona), HttpStatus.OK);
    }

    @PostMapping("/personaje")
    @Operation(summary = "Guardar personaje", description = "Este método es para guarda un personaje", responses = {
            @ApiResponse(responseCode = "201", description = "Se creo el personaje."),
            @ApiResponse(responseCode = "404", description = "No se pudo crear el personaje.")
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(required = true, description = "Entidad personaje")
    public ResponseEntity<PersonajeDto> save(@RequestBody @NotBlank PersonajeDto personajeDto) {
        return new ResponseEntity<>(personajeService.save(personajeDto), HttpStatus.CREATED);
    }

    @DeleteMapping("/personaje")
    @Operation(summary = "Eliminar personaje", description = "Este método es para eliminar el personaje", responses = {
            @ApiResponse(responseCode = "202", description = "Se elimino el personaje."),
            @ApiResponse(responseCode = "404", description = "No se pudo eliminar el personaje.")
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(required = true, description = "Entidad personaje")
    public ResponseEntity<PersonajeDto> delete(@RequestBody @NotBlank PersonajeDto personajeDto) {
        return new ResponseEntity<>(personajeService.delete(personajeDto), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/personaje/deleteById/{idPersona}")
    @Operation(summary = "Eliminar personaje con id", description = "Este método es para eliminar el personaje con el id", responses = {
            @ApiResponse(responseCode = "202", description = "Se elimino el personaje."),
            @ApiResponse(responseCode = "404", description = "No se pudo eliminar el personaje.")
    })
    @Parameter(required = true, description = "Id persona", example = "1")
    public ResponseEntity<PersonajeDto> deleteById(@PathVariable("idPersona") @NotBlank Integer idPersona) {
        return new ResponseEntity<>(personajeService.deleteById(idPersona), HttpStatus.ACCEPTED);
    }

    @PutMapping("/personaje/update/{idPersonaje}")
    @Operation(summary = "Actualizar personaje", description = "Este método es para actualizar el personaje", responses = {
            @ApiResponse(responseCode = "202", description = "Se actualizo el personaje."),
            @ApiResponse(responseCode = "404", description = "No se pudo actualizar el personaje.")
    })
    @Parameter(required = true, description = "Id personaje", example = "1")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(required = true, description = "Entidad personaje")
    public ResponseEntity<PersonajeDto> update(@PathVariable("idPersonaje") @NotBlank Integer idPersonaje, @RequestBody @NotBlank PersonajeDto personajeDto) {
        return new ResponseEntity<>(personajeService.update(idPersonaje, personajeDto), HttpStatus.ACCEPTED);
    }
}
