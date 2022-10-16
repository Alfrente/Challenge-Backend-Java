package com.arroyo.cine.controller;

import com.arroyo.cine.model.dto.GeneroDto;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GeneroControllerTest {

    private static GeneroController controller;
    private static GeneroDto generoDto, generoDtoPrueba;
    private static List<GeneroDto> generoDtoList, generoDtoListPrueba;

    @BeforeAll
    static void beforeAll() {
        controller = Mockito.mock(GeneroController.class);

        generoDto = new GeneroDto("1", "The boys", "boys.jpg", null);
        generoDtoPrueba = new GeneroDto("1", "The boys", "boys.jpg", null);

        generoDtoList = new ArrayList<>();
        generoDtoListPrueba = new ArrayList<>();

        generoDtoList.add(generoDto);
        generoDtoListPrueba.add(generoDtoPrueba);
    }

    @Test
    void getAll() {
        Mockito.when(controller.getAll()).thenReturn(new ResponseEntity<>(generoDtoList, HttpStatus.OK));
        assertEquals(new ResponseEntity<>(generoDtoListPrueba, HttpStatus.OK), controller.getAll());
    }

    @Test
    void getById() {
        Mockito.when(controller.getById("1")).thenReturn(new ResponseEntity<>(generoDto, HttpStatus.OK));
        assertEquals(new ResponseEntity<>(generoDtoPrueba, HttpStatus.OK), controller.getById("1"));
    }

    @Test
    void save() {
        MockMultipartFile filea = new MockMultipartFile(
                "file",
                "saludo.txt",
                MediaType.TEXT_PLAIN_VALUE,
                "HOLA".getBytes()
        );

        Mockito.when(controller.save("Accion pura", filea)).thenReturn(new ResponseEntity<>(generoDto, HttpStatus.CREATED));
        assertEquals(new ResponseEntity<>(generoDtoPrueba, HttpStatus.CREATED), controller.save("Accion pura", filea));
    }

    @Test
    void delete() {
    }

    @Test
    void deleteById() {
    }

    @Test
    void update() {
    }
}