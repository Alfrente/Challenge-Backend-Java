package com.arroyo.cine.controller;

import com.arroyo.cine.dto.PersonajeDto;
import com.arroyo.cine.service.PersonajeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/personaje")
public class PersonajeController {

    @Autowired
    private PersonajeService personajeService;

    @GetMapping
    @Operation(summary = "Trae los personajes", description = "Este método trae todos los personajes", responses = {
            @ApiResponse(responseCode = "200", description = "Petición exitosa."),
            @ApiResponse(responseCode = "404", description = "No se pudo completar la petición.")
    })
    public List<PersonajeDto> getAll() {
        return personajeService.getAll();
    }

    @GetMapping("/getById/{idPersona}")
    @Operation(summary = "Buscar con id", description = "Este método busca el personaje con el id", responses = {
            @ApiResponse(responseCode = "200", description = "Petición exitosa."),
            @ApiResponse(responseCode = "404", description = "No se pudo completar la petición.")
    })
    @Parameter(required = true, description = "Id persona", example = "1")
    public PersonajeDto getById(@PathVariable("idPersona") Integer idPersona) {
        return personajeService.getById(idPersona);
    }

    @PostMapping
    @Operation(summary = "Guardar personaje", description = "Este método es para guarda un personaje", responses = {
            @ApiResponse(responseCode = "201", description = "Se creo el personaje."),
            @ApiResponse(responseCode = "404", description = "No se pudo crear el personaje.")
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(required = true, description = "Entidad personaje")
    public PersonajeDto save(@RequestBody PersonajeDto personajeDto) {
        return personajeService.save(personajeDto);
    }

    @DeleteMapping
    @Operation(summary = "Eliminar personaje", description = "Este método es para eliminar el personaje", responses = {
            @ApiResponse(responseCode = "202", description = "Se elimino el personaje."),
            @ApiResponse(responseCode = "404", description = "No se pudo eliminar el personaje.")
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(required = true, description = "Entidad personaje")
    public PersonajeDto delete(PersonajeDto personajeDto) {
        return personajeService.delete(personajeDto);
    }

    @DeleteMapping("/deleteById/{idPersona}")
    @Operation(summary = "Eliminar personaje con id", description = "Este método es para eliminar el personaje con el id", responses = {
            @ApiResponse(responseCode = "202", description = "Se elimino el personaje."),
            @ApiResponse(responseCode = "404", description = "No se pudo eliminar el personaje.")
    })
    @Parameter(required = true, description = "Id persona", example = "1")
    public PersonajeDto deleteById(@PathVariable("idPersona") Integer idPersona) {
        return personajeService.deleteById(idPersona);
    }

    @PutMapping("/update/{idPersonaje}")
    @Operation(summary = "Actualizar personaje", description = "Este método es para actualizar el personaje", responses = {
            @ApiResponse(responseCode = "200", description = "Se actualizo el personaje."),
            @ApiResponse(responseCode = "404", description = "No se pudo actualizar el personaje.")
    })
    @Parameter(required = true, description = "Id personaje", example = "1")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(required = true, description = "Entidad personaje")
    public PersonajeDto update(@PathVariable("idPersonaje") Integer idPersonaje, @RequestBody PersonajeDto personajeDto) {
        return personajeService.update(idPersonaje, personajeDto);
    }
}
