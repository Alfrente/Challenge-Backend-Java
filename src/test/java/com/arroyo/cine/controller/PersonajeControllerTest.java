package com.arroyo.cine.controller;

import com.arroyo.cine.model.dto.PeliculaSerieDto;
import com.arroyo.cine.model.dto.PersonajeDto;
import com.arroyo.cine.service.PersonajeService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PersonajeControllerTest {
    private static final PersonajeService service = Mockito.mock(PersonajeService.class);
    private static final PersonajeController controller = new PersonajeController(service);
    private static PersonajeDto personajeDto;
    private static PeliculaSerieDto peliculaSerieDto;
    private static MockMultipartFile imagen;

    @BeforeAll
    static void beforeAll() {
        peliculaSerieDto = new PeliculaSerieDto("1", "The boys", "prueba.jpg", LocalDate.now().toString(),
                "5", "1", null);
        personajeDto = new PersonajeDto("1", "Omar", "20", "18.47", "prueba.jpg",
                "Soy productor", List.of(peliculaSerieDto));
        imagen = new MockMultipartFile("prueba", "prueba.jpg", MediaType.TEXT_PLAIN_VALUE, "prueba".getBytes());
    }

    @Test
    void getAll() {
        Mockito.when(service.getAll(null, null, null)).thenReturn(List.of(personajeDto));
        assertEquals(new ResponseEntity<>(List.of(personajeDto), HttpStatus.OK), controller.getAll(null, null, null));
    }

    @Test
    void getById() {
        Mockito.when(service.getById("1")).thenReturn(personajeDto);
        assertEquals(new ResponseEntity<>(personajeDto, HttpStatus.OK), controller.getById("1"));
    }

    @Test
    void save() {
        Mockito.when(service.save("Omar", "20", "18.74", imagen, "Soy productor")).thenReturn(personajeDto);
        assertEquals(new ResponseEntity<>(personajeDto, HttpStatus.CREATED), controller.save("Omar", "20", "18.74", imagen,
                "Soy productor"));
    }

    @Test
    void delete() {
        Mockito.when(service.delete(personajeDto)).thenReturn(personajeDto);
        assertEquals(new ResponseEntity<>(personajeDto, HttpStatus.ACCEPTED), controller.delete(personajeDto));
    }

    @Test
    void deleteById() {
        Mockito.when(service.deleteById("1")).thenReturn(personajeDto);
        assertEquals(new ResponseEntity<>(personajeDto, HttpStatus.ACCEPTED), controller.deleteById("1"));
    }

    @Test
    void update() {
        Mockito.when(service.update("1", "Omar", "20", "18.74", imagen, "Soy productor")).thenReturn(personajeDto);
        assertEquals(new ResponseEntity<>(personajeDto, HttpStatus.ACCEPTED), controller.update("1", "Omar", "20", "18.74", imagen,
                "Soy productor"));
    }
}