package com.arroyo.cine.controller;

import com.arroyo.cine.service.PeliculaSerieService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class PeliculaSerieControllerTest {
    private static PeliculaSerieService service = Mockito.mock(PeliculaSerieService.class);
    private static PeliculaSerieController controller = new PeliculaSerieController(service);

    @Test
    void getAll() {

    }

    @Test
    void getById() {
    }

    @Test
    void save() {
    }

    @Test
    void saveReq() {
    }

    @Test
    void delete() {
    }

    @Test
    void deletePersonalizado() {
    }

    @Test
    void deleteById() {
    }

    @Test
    void update() {
    }
}