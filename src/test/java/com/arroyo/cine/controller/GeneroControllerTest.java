package com.arroyo.cine.controller;

import com.arroyo.cine.model.dto.GeneroDto;
import com.arroyo.cine.service.GeneroService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GeneroControllerTest {

    private static GeneroController controller;
    private static GeneroDto generoDto, generoDtoPrueba;
    private static List<GeneroDto> generoDtoList, generoDtoListPrueba;

    @BeforeAll
    static void beforeAll() {
        controller = Mockito.mock(GeneroController.class);

        generoDto = new GeneroDto();
        generoDtoPrueba = new GeneroDto();

        generoDto.setIdGenero("1");
        generoDto.setImagen("boys.jpg");
        generoDto.setNombre("The boys");

        generoDtoPrueba.setIdGenero("");
        generoDtoPrueba.setImagen("boys.jpg");
        generoDtoPrueba.setNombre("The boys");


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
    }

    @Test
    void save() {
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