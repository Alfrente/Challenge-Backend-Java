package com.arroyo.cine.service;

import com.arroyo.cine.model.dto.PeliculaSerieDto;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PeliculaSerieServiceTest {

    private static PeliculaSerieService service;
    private static PeliculaSerieDto peliculaSerieDto, peliculaSerieDtoPrueba;
    private static List<PeliculaSerieDto> peliculaSerieDtoList, peliculaSerieDtoListPrueba;

    @BeforeAll
    static void beforeAll() {
        service = Mockito.mock(PeliculaSerieService.class);
        peliculaSerieDto = new PeliculaSerieDto("1","El agua","pelicula.jpg","2020-09-16","2", "2", null);
        peliculaSerieDtoPrueba = new PeliculaSerieDto("1","El agua","pelicula.jpg","2020-09-16","2", "2", null);
        peliculaSerieDtoList = new ArrayList<>();
        peliculaSerieDtoListPrueba = new ArrayList<>();

        peliculaSerieDtoList.add(peliculaSerieDto);
        peliculaSerieDtoListPrueba.add(peliculaSerieDtoPrueba);
    }

    @Test
    void getAll() {
        Mockito.when(service.getAll(null, null,null)).thenReturn(peliculaSerieDtoList);
        assertEquals(peliculaSerieDtoListPrueba, service.getAll(null, null,null));
    }

    @Test
    void getById() {
        Mockito.when(service.getById("1")).thenReturn(peliculaSerieDto);
        assertEquals(peliculaSerieDtoPrueba, service.getById("1"));
    }

    @Test
    void save() {
        Mockito.when(service.save(peliculaSerieDto)).thenReturn(peliculaSerieDto);
        assertEquals(peliculaSerieDtoPrueba, service.save(peliculaSerieDto));
    }

    @Test
    void savePersonalizado() {
        Mockito.when(service.savePersonalizado("1","1")).thenReturn(peliculaSerieDto);
        assertEquals(peliculaSerieDtoPrueba, service.savePersonalizado("1","1"));
    }

    @Test
    void update() {
        Mockito.when(service.update("1", peliculaSerieDto)).thenReturn(peliculaSerieDto);
        assertEquals(peliculaSerieDtoPrueba, service.update("1", peliculaSerieDto));
    }

    @Test
    void delete() {
        Mockito.when(service.delete(peliculaSerieDto)).thenReturn(peliculaSerieDto);
        assertEquals(peliculaSerieDtoPrueba, service.delete(peliculaSerieDto));
    }

    @Test
    void deleteById() {
        Mockito.when(service.deleteById("1")).thenReturn(peliculaSerieDto);
        assertEquals(peliculaSerieDtoPrueba, service.deleteById("1"));
    }
}