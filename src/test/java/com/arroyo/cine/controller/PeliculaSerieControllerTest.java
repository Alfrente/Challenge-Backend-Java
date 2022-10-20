package com.arroyo.cine.controller;

import com.arroyo.cine.model.dto.PeliculaSerieDto;
import com.arroyo.cine.model.dto.PersonajeDto;
import com.arroyo.cine.service.PeliculaSerieService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class PeliculaSerieControllerTest {
    private static PeliculaSerieService service = Mockito.mock(PeliculaSerieService.class);
    private static PeliculaSerieController controller = new PeliculaSerieController(service);

    private static PeliculaSerieDto peliculaSerieDto;
    private static PersonajeDto personajeDto;

    @BeforeAll
    static void beforeAll() {
        personajeDto = new PersonajeDto("1", "Omar", "20", "18.47",
                "prueba.jpg", "Soy productor", null);

        peliculaSerieDto = new PeliculaSerieDto("1", "The boys", "prueba.jpg", LocalDate.now().toString(),
                "5", "1", List.of(personajeDto));
    }

    @Test
    void getAll() {
        Mockito.when(service.getAll(null, null, null)).thenReturn(List.of(peliculaSerieDto));
        assertEquals(new ResponseEntity<>(List.of(peliculaSerieDto), HttpStatus.OK), controller.getAll(null, null, null));
    }

    @Test
    void getById() {
        Mockito.when(service.getById("1")).thenReturn(peliculaSerieDto);
        assertEquals(new ResponseEntity<>(peliculaSerieDto, HttpStatus.OK), controller.getById("1"));
    }

    @Test
    void save() {
        Mockito.when(service.save(peliculaSerieDto)).thenReturn(peliculaSerieDto);
        assertEquals(new ResponseEntity<>(peliculaSerieDto, HttpStatus.CREATED), controller.save(peliculaSerieDto));
    }

    @Test
    void saveReq() {
        Mockito.when(service.savePersonalizado("1", "1")).thenReturn(peliculaSerieDto);
        assertEquals(new ResponseEntity<>(peliculaSerieDto, HttpStatus.CREATED), controller.saveReq("1", "1"));
    }

    @Test
    void delete() {
        Mockito.when(service.delete(peliculaSerieDto)).thenReturn(peliculaSerieDto);
        assertEquals(new ResponseEntity<>(peliculaSerieDto, HttpStatus.ACCEPTED), controller.delete(peliculaSerieDto));
    }

    @Test
    void deletePersonalizado() {
        Mockito.when(service.deletePersonalizado("1", "1")).thenReturn(Map.of("Mensaje", "El personaje " +
                personajeDto.nombre() + " se elimino de la película " + peliculaSerieDto.titulo()));
        assertEquals(new ResponseEntity<>(Map.of("Mensaje", "El personaje " + personajeDto.nombre() + " se elimino de la película " +
                peliculaSerieDto.titulo()), HttpStatus.ACCEPTED), controller.deletePersonalizado("1", "1"));
    }

    @Test
    void deleteById() {
        Mockito.when(service.deleteById("1")).thenReturn(peliculaSerieDto);
        assertEquals(new ResponseEntity<>(peliculaSerieDto, HttpStatus.ACCEPTED), controller.deleteById("1"));
    }

    @Test
    void update() {
        peliculaSerieDto = new PeliculaSerieDto("1", "Los increíbles", "prueba.jpg", LocalDate.now().toString(),
                "4", "2", null);
        Mockito.when(service.update("1", peliculaSerieDto)).thenReturn(peliculaSerieDto);
        assertEquals(new ResponseEntity<>(peliculaSerieDto, HttpStatus.ACCEPTED), controller.update("1", peliculaSerieDto));
    }
}