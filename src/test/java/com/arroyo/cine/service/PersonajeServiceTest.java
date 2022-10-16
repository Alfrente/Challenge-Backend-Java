package com.arroyo.cine.service;

import com.arroyo.cine.model.dto.PersonajeDto;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class PersonajeServiceTest {

    private static PersonajeService service;
    private static PersonajeDto personajeDto, personajeDtoPrueba;
    private static List<PersonajeDto> personajeDtoList, personajeDtoListPrueba;

    @BeforeAll
    static void beforeAll() {
        service = Mockito.mock(PersonajeService.class);
        personajeDto = new PersonajeDto("1","Arroyo", "20", "15.50", "FotoPersonaje.jpg", "Soy productor", null);
        personajeDtoPrueba = new PersonajeDto("1","Arroyo", "20", "15.50", "FotoPersonaje.jpg", "Soy productor", null);
        personajeDtoList = new ArrayList<>();
        personajeDtoListPrueba = new ArrayList<>();

        personajeDtoList.add(personajeDto);
        personajeDtoListPrueba.add(personajeDtoPrueba);
    }

    @Test
    void getAll() {
        Mockito.when(service.getAll(null, null, null)).thenReturn(personajeDtoList);
        assertEquals(personajeDtoListPrueba, service.getAll(null, null, null));
    }
    @Test
    void getById() {
        Mockito.when(service.getById("1")).thenReturn(personajeDto);
        assertEquals(personajeDtoPrueba, service.getById("1"));
    }

    @Test
    void save() {
        Mockito.when(service.save("Arroyo","20","15.50", null,"Soy productor")).thenReturn(personajeDto);
        assertEquals(personajeDtoPrueba,service.save("Arroyo","20","15.50", null,"Soy productor"));
    }

    @Test
    void update() {
        Mockito.when(service.update("1","Arroyo","20","15.50", null,"Soy productor")).thenReturn(personajeDto);
        assertEquals(personajeDtoPrueba, service.update("1","Arroyo","20","15.50", null,"Soy productor"));
    }

    @Test
    void delete() {
        Mockito.when(service.delete(personajeDto)).thenReturn(personajeDto);
        assertEquals(personajeDtoPrueba, personajeDto);
    }

    @Test
    void deleteById() {
        Mockito.when(service.deleteById("1")).thenReturn(personajeDto);
        assertEquals(personajeDtoPrueba, service.deleteById("1"));
    }
}