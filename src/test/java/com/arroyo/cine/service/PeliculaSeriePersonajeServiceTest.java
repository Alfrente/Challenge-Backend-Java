package com.arroyo.cine.service;

import com.arroyo.cine.model.entity.FkPeliculaSeriePersonaje;
import com.arroyo.cine.model.entity.PeliculaSeriePersonaje;
import com.arroyo.cine.repository.PeliculaSeriePersonajeRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PeliculaSeriePersonajeServiceTest {
    private static PeliculaSeriePersonajeService service;
    private static PeliculaSeriePersonaje psp, pspPrueba;

    @BeforeAll
    static void beforeAll() {
        PeliculaSeriePersonajeRepository repository = Mockito.mock(PeliculaSeriePersonajeRepository.class);
        service = new PeliculaSeriePersonajeService(repository);
        psp = new PeliculaSeriePersonaje(new FkPeliculaSeriePersonaje(1, 2));
        pspPrueba = new PeliculaSeriePersonaje(new FkPeliculaSeriePersonaje(1, 2));
    }

    @Test
    void getByIdPeliculaSerieAndIdPersonaje() {
        Mockito.when(service.getByIdPeliculaSerieAndIdPersonaje(1,2)).thenReturn(psp);
        assertEquals(pspPrueba, service.getByIdPeliculaSerieAndIdPersonaje(1,2));
    }

}