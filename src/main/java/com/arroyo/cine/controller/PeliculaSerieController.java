package com.arroyo.cine.controller;

import com.arroyo.cine.model.dto.PeliculaSerieDto;
import com.arroyo.cine.service.PeliculaSerieService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import java.util.List;
import java.util.Map;

@RestController
public class PeliculaSerieController {

    private final PeliculaSerieService service;

    public PeliculaSerieController(PeliculaSerieService service) {
        this.service = service;
    }

    @GetMapping("/movies")
    @Operation(summary = "Lista de películas", description = "Devuelve la lista de películas", responses = {
            @ApiResponse(responseCode = "200", description = "Petición exitosa."),
            @ApiResponse(responseCode = "400", description = "No se completo la petición."),
            @ApiResponse(responseCode = "404", description = "Servicio no disponible.")
    })
    @Parameter(description = "name", example = "Guerra z")
    @Parameter(description = "genre", example = "1")
    @Parameter(description = "order", example = "ASC")
    public ResponseEntity<List<PeliculaSerieDto>> getAll(
            @RequestParam(required = false, name = "name") @Null String name,
            @RequestParam(required = false, name = "genre")
            @Null String genre, @RequestParam(required = false, name = "order") @Null String order
    ) {
        return new ResponseEntity<>(service.getAll(name, genre, order), HttpStatus.OK);
    }

    @GetMapping("/peliculaSerie/getById/{id}")
    @Operation(summary = "película específica", description = "Devuelve una película específica", responses = {
            @ApiResponse(responseCode = "200", description = "Petición exitosa."),
            @ApiResponse(responseCode = "400", description = "No se completo la petición."),
            @ApiResponse(responseCode = "404", description = "Servicio no disponible.")
    })
    @Parameter(required = true, description = "Id", example = "1")
    public ResponseEntity<PeliculaSerieDto> getById(@PathVariable("id") String id) {
        return new ResponseEntity<>(service.getById(id), HttpStatus.OK);
    }

    @PostMapping("/peliculaSerie")
    @Operation(summary = "Guardar película", description = "Guarda una nueva película", responses = {
            @ApiResponse(responseCode = "201", description = "Guardo la película."),
            @ApiResponse(responseCode = "400", description = "No se guardo película."),
            @ApiResponse(responseCode = "404", description = "Servicio no disponible.")
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(required = true, description = "Entidad pelicula_serie")
    public ResponseEntity<PeliculaSerieDto> save(@RequestBody @NotBlank PeliculaSerieDto peliculaSerieDto) {
        return new ResponseEntity<>(service.save(peliculaSerieDto), HttpStatus.CREATED);
    }

    @PostMapping("/movies/{idMovie}/characters/{idCharacter}")
    @Operation(summary = "Guardar película", description = "Guarda una nueva película", responses = {
            @ApiResponse(responseCode = "201", description = "Guarda la película."),
            @ApiResponse(responseCode = "400", description = "No se guardo película."),
            @ApiResponse(responseCode = "404", description = "Servicio no disponible.")
    })
    @Parameter(description = "idMovie", example = "1", required = true)
    @Parameter(description = "idCharacter", example = "2", required = true)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<PeliculaSerieDto> saveReq
            (@PathVariable("idMovie") String idMovie, @PathVariable("idCharacter") String idCharacter
            ) {
        return new ResponseEntity<>(service.savePersonalizado(idMovie, idCharacter), HttpStatus.CREATED);
    }

    @DeleteMapping("/peliculaSerie")
    @Operation(summary = "Eliminar película", description = "Elimina una película específica", responses = {
            @ApiResponse(responseCode = "202", description = "Se elimino la película."),
            @ApiResponse(responseCode = "400", description = "No se elimino la película."),
            @ApiResponse(responseCode = "404", description = "Servicio no disponible.")
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Entidad pelicula_serie", required = true)
    public ResponseEntity<PeliculaSerieDto> delete(@RequestBody @NotBlank PeliculaSerieDto peliculaSerieDto) {
        return new ResponseEntity<>(service.delete(peliculaSerieDto), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/movies/{idMovie}/characters/{idCharacter}")
    @Operation(summary = "Eliminar película", description = "Elimina una película específica", responses = {
            @ApiResponse(responseCode = "202", description = "Se elimino la película."),
            @ApiResponse(responseCode = "400", description = "No se elimino la película."),
            @ApiResponse(responseCode = "404", description = "Servicio no disponible.")
    })
    @Parameter(description = "idMovie", required = true, example = "1")
    @Parameter(description = "idCharacter", required = true, example = "2")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<Map<String, String>> deletePersonalizado(@PathVariable("idMovie") String idMovie, @PathVariable("idCharacter") String idCharacter) {
        return new ResponseEntity<>(service.deletePersonalizado(idMovie, idCharacter), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/peliculaSerie/deleteById/{id}")
    @Operation(summary = "Eliminar película", description = "Elimina una película específica", responses = {
            @ApiResponse(responseCode = "202", description = "Se elimino la película."),
            @ApiResponse(responseCode = "400", description = "No se elimino la película."),
            @ApiResponse(responseCode = "404", description = "Servicio no disponible.")
    })
    @Parameter(description = "Id", example = "1", required = true)
    public ResponseEntity<PeliculaSerieDto> deleteById(@PathVariable("id") String id) {
        return new ResponseEntity<>(service.deleteById(id), HttpStatus.ACCEPTED);
    }

    @PutMapping("/peliculaSerie/update/{id}")
    @Operation(summary = "Actualizar película", description = "Actualiza una película específica", responses = {
            @ApiResponse(responseCode = "202", description = "Se actualizo la película."),
            @ApiResponse(responseCode = "400", description = "No se pudo actualizo la serie o película."),
            @ApiResponse(responseCode = "404", description = "Servicio no disponible.")
    })
    @Parameter(required = true, description = "Id", example = "1")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Entidad pelicula_serie", required = true)
    public ResponseEntity<PeliculaSerieDto> update(@PathVariable("id") String id, @RequestBody @NotBlank PeliculaSerieDto peliculaSerieDto) {
        return new ResponseEntity<>(service.update(id, peliculaSerieDto), HttpStatus.ACCEPTED);
    }
}
