package com.arroyo.cine.controller;

import com.arroyo.cine.model.dto.PersonajeDto;
import com.arroyo.cine.service.PersonajeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import java.util.List;

@RestController
public class PersonajeController {
    private final PersonajeService service;

    public PersonajeController(PersonajeService service) {
        this.service = service;
    }

    @GetMapping("characters")
    @Operation(summary = "Lista personajes", description = "Devuelve la lista de personajes", responses = {
            @ApiResponse(responseCode = "200", description = "Petición exitosa."),
            @ApiResponse(responseCode = "400", description = "No se completo la petición."),
            @ApiResponse(responseCode = "404", description = "Servicio no disponible.")
    })
    @Parameter(description = "name", example = "Guerra z")
    @Parameter(description = "age", example = "15")
    @Parameter(description = "movies", example = "1")
    public ResponseEntity<List<PersonajeDto>> getAll(@RequestParam(required = false, name = "name") String name, @RequestParam(required = false, name = "age")
    String age, @RequestParam(required = false, name = "movies") String movies) {
        return new ResponseEntity<>(service.getAll(name, age, movies), HttpStatus.OK);
    }

    @GetMapping("/personaje/getById/{idPersona}")
    @Operation(summary = "Personaje específico", description = "Devuelve un personaje específico", responses = {
            @ApiResponse(responseCode = "200", description = "Petición exitosa."),
            @ApiResponse(responseCode = "400", description = "No se completo la petición."),
            @ApiResponse(responseCode = "404", description = "Servicio no disponible.")
    })
    @Parameter(required = true, description = "Id persona", example = "1")
    public ResponseEntity<PersonajeDto> getById(@PathVariable("idPersona") String idPersona) {
        return new ResponseEntity<>(service.getById(idPersona), HttpStatus.OK);
    }

    @PostMapping("/personaje")
    @Operation(summary = "Guardar personaje", description = "Guarda un nuevo personaje", responses = {
            @ApiResponse(responseCode = "201", description = "Se creo el personaje."),
            @ApiResponse(responseCode = "400", description = "No se creo el personaje."),
            @ApiResponse(responseCode = "404", description = "Servicio no disponible.")
    })
    @Parameter(description = "nombre", example = "Andres Restrepo", required = true)
    @Parameter(description = "edad", example = "20", required = true)
    @Parameter(description = "peso", example = "18.72", required = true)
    @Parameter(description = "imagen")
    @Parameter(description = "historia")
    public ResponseEntity<PersonajeDto> save(
            @RequestParam(value = "nombre", required = false) String nombre,
            @RequestParam(value = "edad", required = false) String edad,
            @RequestParam(value = "peso", required = false) String peso,
            @RequestParam(value = "imagen", required = false) MultipartFile imagen,
            @RequestParam(value = "historia", required = false) String historia
    ) {
        return new ResponseEntity<>(service.save(nombre, edad, peso, imagen, historia), HttpStatus.CREATED);
    }

    @DeleteMapping("/personaje")
    @Operation(summary = "Eliminar personaje", description = "Elimina un personaje específico", responses = {
            @ApiResponse(responseCode = "202", description = "Se elimino el personaje."),
            @ApiResponse(responseCode = "400", description = "No se elimino el personaje."),
            @ApiResponse(responseCode = "404", description = "Servicio no disponible.")
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(required = true, description = "Entidad personaje")
    public ResponseEntity<PersonajeDto> delete(@RequestBody @NotBlank PersonajeDto personajeDto) {
        return new ResponseEntity<>(service.delete(personajeDto), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/personaje/deleteById/{idPersona}")
    @Operation(summary = "Eliminar personaje", description = "Elimina un personaje específico", responses = {
            @ApiResponse(responseCode = "202", description = "Se elimino el personaje."),
            @ApiResponse(responseCode = "400", description = "No se elimino el personaje."),
            @ApiResponse(responseCode = "404", description = "Servicio no disponible.")
    })
    @Parameter(required = true, description = "Id persona", example = "1")
    public ResponseEntity<PersonajeDto> deleteById(@PathVariable("idPersona") String idPersona) {
        return new ResponseEntity<>(service.deleteById(idPersona), HttpStatus.ACCEPTED);
    }

    @PutMapping("/personaje/update/{idPersonaje}")
    @Operation(summary = "Actualizar personaje", description = "Actualiza un personaje específico", responses = {
            @ApiResponse(responseCode = "202", description = "Se actualizo el personaje."),
            @ApiResponse(responseCode = "400", description = "No se actualizo el personaje."),
            @ApiResponse(responseCode = "404", description = "Servicio no disponible.")
    })
    @Parameter(description = "Id personaje", example = "1", required = true)
    @Parameter(description = "nombre", example = "Andres Restrepo", required = true)
    @Parameter(description = "edad", example = "20", required = true)
    @Parameter(description = "peso", example = "18.72", required = true)
    @Parameter(description = "imagen")
    @Parameter(description = "historia")
    public ResponseEntity<PersonajeDto> update(
            @PathVariable(value = "idPersonaje") String idPersonaje,
            @RequestParam(value = "nombre", required = false) String nombre,
            @RequestParam(value = "edad", required = false) String edad,
            @RequestParam(value = "peso", required = false) String peso,
            @RequestParam(value = "imagen", required = false) MultipartFile imagen,
            @RequestParam(value = "historia", required = false) String historia
    ) {
        return new ResponseEntity<>(service.update(idPersonaje, nombre, edad, peso, imagen, historia), HttpStatus.ACCEPTED);
    }
}
