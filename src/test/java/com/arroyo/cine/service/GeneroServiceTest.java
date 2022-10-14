package com.arroyo.cine.service;

import com.arroyo.cine.model.dto.GeneroDto;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class GeneroServiceTest {

    private static GeneroService service;
    private static GeneroDto generoDto, generoDtoPrueba;
    private static List<GeneroDto> generoDtoList, generoDtoListPrueba;

    @BeforeAll
    static void beforeAll() {
        service = Mockito.mock(GeneroService.class);

        generoDto = new GeneroDto();
        generoDtoPrueba = new GeneroDto();

        generoDto.setIdGenero("1");
        generoDto.setImagen("boys.jpg");
        generoDto.setNombre("The boys");

        generoDtoPrueba.setIdGenero("1");
        generoDtoPrueba.setImagen("boys.jpg");
        generoDtoPrueba.setNombre("The boys");


        generoDtoList = new ArrayList<>();
        generoDtoListPrueba = new ArrayList<>();

        generoDtoList.add(generoDto);
        generoDtoListPrueba.add(generoDtoPrueba);
    }

    @Test
    void getAll() {
        Mockito.when(service.getAll()).thenReturn(generoDtoList);
        assertEquals(generoDtoListPrueba, service.getAll());
    }

    @Test
    void getById() {
        Mockito.when(service.getById("1")).thenReturn(generoDto);
        assertEquals(generoDtoPrueba, service.getById("1"));
    }

    @Test
    void save() {
        Mockito.when(service.save("La biblia", null)).thenReturn(generoDto);
        assertEquals(generoDtoPrueba, service.save("La biblia", null));
    }

    @Test
    void update() {
        Mockito.when(service.update("1","La biblia", null)).thenReturn(generoDto);
        assertEquals(generoDtoPrueba, service.update("1","La biblia", null));
    }

    @Test
    void delete() {
        Mockito.when(service.delete(generoDto)).thenReturn(generoDto);
        assertEquals(generoDtoPrueba, service.delete(generoDto));
    }

    @Test
    void deleteById() {
    Mockito.when(service.deleteById("1")).thenReturn(generoDto);
    assertEquals(generoDtoPrueba, service.deleteById("1"));
    }

}