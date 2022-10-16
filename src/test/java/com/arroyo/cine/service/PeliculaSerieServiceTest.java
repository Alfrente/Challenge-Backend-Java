package com.arroyo.cine.service;

import com.arroyo.cine.model.dto.PeliculaSerieDto;
import com.arroyo.cine.model.dto.PersonajeDto;
import com.arroyo.cine.model.entity.PeliculaSerie;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PeliculaSerieServiceTest {

    private static PeliculaSerieService service;
    private static PeliculaSerieDto peliculaSerieDto, peliculaSerieDtoPrueba;
    private static List<PeliculaSerieDto> peliculaSerieDtoList, peliculaSerieDtoListPrueba;

    @BeforeAll
    static void beforeAll() {
        service = Mockito.mock(PeliculaSerieService.class);
        peliculaSerieDto = new PeliculaSerieDto();
        peliculaSerieDtoPrueba = new PeliculaSerieDto();
        peliculaSerieDtoList = new ArrayList<>();
        peliculaSerieDtoListPrueba = new ArrayList<>();

        peliculaSerieDto.setIdPeliculaSerie("1");
        peliculaSerieDto.setPersonajes(null);
        peliculaSerieDto.setIdGenero("2");
        peliculaSerieDto.setCalifiacion("5");
        peliculaSerieDto.setFechaCreacion("2020-09-16");
        peliculaSerieDto.setTitulo("El agua");
        peliculaSerieDto.setCaratula("pelicula.jpg");

        peliculaSerieDtoPrueba.setIdPeliculaSerie("1");
        peliculaSerieDtoPrueba.setPersonajes(null);
        peliculaSerieDtoPrueba.setIdGenero("2");
        peliculaSerieDtoPrueba.setCalifiacion("5");
        peliculaSerieDtoPrueba.setFechaCreacion("2020-09-16");
        peliculaSerieDtoPrueba.setTitulo("El agua");
        peliculaSerieDtoPrueba.setCaratula("pelicula.jpg");


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