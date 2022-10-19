package com.arroyo.cine.controller;

import com.arroyo.cine.model.dto.GeneroDto;
import com.arroyo.cine.service.GeneroService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GeneroControllerTest {
    private static final GeneroService service = Mockito.mock(GeneroService.class);
    private static final GeneroController controller = new GeneroController(service);
    private static GeneroDto generoDto;
    private static List<GeneroDto> generoDtoList;
    private static MockMultipartFile imagen;

    @BeforeAll
    static void beforeAll() {
        generoDto = new GeneroDto("1", "Terror", "prueba.jpg", null);
        generoDtoList = new ArrayList<>();
        generoDtoList.add(generoDto);
        imagen = new MockMultipartFile("prueba", "prueba.jpg", MediaType.TEXT_PLAIN_VALUE, "prueba".getBytes());
    }

    @Test
    void getAll() {
        Mockito.when(service.getAll()).thenReturn(generoDtoList);
        assertEquals(new ResponseEntity<>(generoDtoList, HttpStatus.OK), controller.getAll());
    }

    @Test
    void getById() {
        Mockito.when(service.getById("1")).thenReturn(generoDto);
        assertEquals(new ResponseEntity<>(generoDto, HttpStatus.OK), controller.getById("1"));
    }

    @Test
    void save() {
        Mockito.when(service.save("Terror", imagen)).thenReturn(generoDto);
        assertEquals(new ResponseEntity<>(generoDto, HttpStatus.CREATED), controller.save("Terror", imagen));
    }

    @Test
    void delete() {
        Mockito.when(service.delete(generoDto)).thenReturn(generoDto);
        assertEquals(new ResponseEntity<>(generoDto, HttpStatus.ACCEPTED), controller.delete(generoDto));
    }

    @Test
    void deleteById() {
        Mockito.when(service.deleteById("1")).thenReturn(generoDto);
        assertEquals(new ResponseEntity<>(generoDto, HttpStatus.ACCEPTED), controller.deleteById("1"));
    }

    @Test
    void update() {
        GeneroDto generoDtoActualizar = new GeneroDto("1","Terror", "prueba.jpg", null);
        Mockito.when(service.update("1","Terror", imagen)).thenReturn(generoDtoActualizar);
        assertEquals(new ResponseEntity<>(generoDtoActualizar, HttpStatus.OK), controller.update("1","Terror", imagen));
    }
}