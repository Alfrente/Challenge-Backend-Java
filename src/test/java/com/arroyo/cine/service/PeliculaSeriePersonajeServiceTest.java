package com.arroyo.cine.service;

import com.arroyo.cine.model.entity.FkPeliculaSeriePersonaje;
import com.arroyo.cine.model.entity.PeliculaSerie;
import com.arroyo.cine.model.entity.PeliculaSeriePersonaje;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class PeliculaSeriePersonajeServiceTest {

    private static PeliculaSeriePersonajeService service;
    private static PeliculaSeriePersonaje psp, pspPrueba;
    private static FkPeliculaSeriePersonaje fkPsp, fkPspPrueba;
    @BeforeAll
    static void beforeAll() {
        service = Mockito.mock(PeliculaSeriePersonajeService.class);
        fkPsp = new FkPeliculaSeriePersonaje(1,2);
        fkPspPrueba = new FkPeliculaSeriePersonaje(1,2);
        psp = new PeliculaSeriePersonaje(fkPsp);
        pspPrueba = new PeliculaSeriePersonaje(fkPspPrueba);
    }

    @Test
    void getByIdPeliculaSerieAndIdPersonaje() {
        Mockito.when(service.getByIdPeliculaSerieAndIdPersonaje(1,2)).thenReturn(psp);
        assertEquals(pspPrueba, service.getByIdPeliculaSerieAndIdPersonaje(1,2));
    }

}