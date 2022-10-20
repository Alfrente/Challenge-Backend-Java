package com.arroyo.cine.service;

import com.arroyo.cine.model.dto.PeliculaSerieDto;
import com.arroyo.cine.model.dto.PersonajeDto;
import com.arroyo.cine.model.entity.*;
import com.arroyo.cine.model.mapper.pelicula_serie.PeliculaSerieMapper;
import com.arroyo.cine.model.mapper.pelicula_serie.PeliculaSerieMapperImpl;
import com.arroyo.cine.model.mapper.pelicula_serie.PersonajeComplementoPeliculaSerieMapper;
import com.arroyo.cine.model.mapper.pelicula_serie.PersonajeComplementoPeliculaSerieMapperImpl;
import com.arroyo.cine.repository.GeneroRepository;
import com.arroyo.cine.repository.PeliculaSeriePersonajeRepository;
import com.arroyo.cine.repository.PeliculaSerieRepository;
import com.arroyo.cine.repository.PersonajeRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PeliculaSerieServiceTest {

    private static final PeliculaSeriePersonajeRepository peliculaSeriePersonajeRepository = Mockito.mock(PeliculaSeriePersonajeRepository.class);
    private static final PersonajeComplementoPeliculaSerieMapper peliculaSerieMapper = new PersonajeComplementoPeliculaSerieMapperImpl();
    private static final PeliculaSerieMapper mapper = new PeliculaSerieMapperImpl(peliculaSerieMapper);
    private static final PeliculaSeriePersonajeService peliculaSeriePersonajeService = new PeliculaSeriePersonajeService(peliculaSeriePersonajeRepository);
    private static final PeliculaSerieRepository repository = Mockito.mock(PeliculaSerieRepository.class);
    private static final PersonajeRepository personajeRepository = Mockito.mock(PersonajeRepository.class);
    private static final GeneroRepository generoRepository = Mockito.mock(GeneroRepository.class);
    private static final PeliculaSerieService service = new PeliculaSerieService(repository, generoRepository, personajeRepository, peliculaSeriePersonajeService, mapper);
    private static final PeliculaSerie peliculaSerie= new PeliculaSerie();
    private static final List<PeliculaSerieDto> peliculaSerieDtoList= new ArrayList<>();
    private static final Personaje personaje= new Personaje();
    private static final Genero genero= new Genero();


    @BeforeAll
    static void beforeAll() {
         genero.setIdGenero(1);
        genero.setImagen("accion.jpg");
        genero.setNombre("Accion");

        personaje.setIdPersonaje(1);
        personaje.setNombre("Omar");
        personaje.setHistoria("Soy productor");
        personaje.setImagen("omar.jpg");
        personaje.setEdad((byte) 20);
        personaje.setPeso(18.50F);

        peliculaSerie.setIdPeliculaSerie(1);
        peliculaSerie.setTitulo("Los simson");
        peliculaSerie.setImagen("simson.jpg");
        peliculaSerie.setIdGenero(1);
        peliculaSerie.setCalifiacion((byte) 5);
        peliculaSerie.setFechaCreacion(LocalDate.now());
        peliculaSerie.setGeneroPelicula(genero);
        peliculaSerie.setPersonajes(List.of(personaje));
    }

    @Test
    void getAll() {
        PeliculaSerieDto peliculaSerieDto = mapper.aPeliculaSerieDto(peliculaSerie);
        peliculaSerieDtoList.add(peliculaSerieDto);
        Mockito.when(repository.findAll()).thenReturn(List.of(peliculaSerie));
        assertEquals(peliculaSerieDtoList, service.getAll(null, null, null));
    }

    @Test
    void getById() {
        PeliculaSerieDto peliculaSerieDto = mapper.aPeliculaSerieDto(peliculaSerie);
        Mockito.when(repository.findById(1)).thenReturn(Optional.of(peliculaSerie));
        assertEquals(peliculaSerieDto, service.getById("1"));
    }

    @Test
    void save() {
        PeliculaSerieDto peliculaSerieDto = mapper.aPeliculaSerieDto(peliculaSerie);
        Mockito.when(generoRepository.findById(1)).thenReturn(Optional.of(genero));
        Mockito.when(repository.findById(1)).thenReturn(Optional.of(peliculaSerie));
        Mockito.when(repository.save(peliculaSerie)).thenReturn(peliculaSerie);
        assertEquals(peliculaSerieDto, service.save(peliculaSerieDto));
    }

    @Test
    void savePersonalizado() {
        PeliculaSerieDto peliculaSerieDto = new PeliculaSerieDto("1", "Los simson", "simson.jpg", LocalDate.now().toString(), "5"
                , "1", List.of(new PersonajeDto("1", "Omar", "20", "18.5", "omar.jpg", "Soy productor", null)));
        Mockito.when(repository.findById(1)).thenReturn(Optional.of(peliculaSerie));
        Mockito.when(personajeRepository.findById(1)).thenReturn(Optional.of(personaje));
        assertEquals(peliculaSerieDto, service.savePersonalizado("1", "1"));
    }

    @Test
    void update() {
        PeliculaSerieDto peliculaSerieDto = new PeliculaSerieDto("1", "Los simson modificado", "simson.jpg", LocalDate.now().toString(), "5"
                , "1", List.of(new PersonajeDto("1", "Omar", "20", "18.5", "omar.jpg", "Soy productor", null)));
        Mockito.when(repository.findById(1)).thenReturn(Optional.of(peliculaSerie));
        Mockito.when(generoRepository.findById(1)).thenReturn(Optional.of(genero));
        assertEquals(peliculaSerieDto, service.update("1", peliculaSerieDto));
    }

    @Test
    void delete() {
        PeliculaSerieDto peliculaSerieDto = mapper.aPeliculaSerieDto(peliculaSerie);
        Mockito.when(repository.findById(1)).thenReturn(Optional.of(peliculaSerie));
        assertEquals(peliculaSerieDto, service.delete(peliculaSerieDto));
    }

    @Test
    void deleteById() {
        PeliculaSerieDto peliculaSerieDto = mapper.aPeliculaSerieDto(peliculaSerie);
        Mockito.when(repository.findById(1)).thenReturn(Optional.of(peliculaSerie));
        assertEquals(peliculaSerieDto, service.deleteById("1"));
    }

    @Test
    void deletePersonalizado() {
        Mockito.when(repository.findById(1)).thenReturn(Optional.of(peliculaSerie));
        Mockito.when(personajeRepository.findById(1)).thenReturn(Optional.of(personaje));
        Mockito.when(peliculaSeriePersonajeRepository.buscarConIdPeliculaSerieAndIdPersonaje(1, 1)).thenReturn(new PeliculaSeriePersonaje(new FkPeliculaSeriePersonaje(1, 1)));
        assertEquals(Map.of("Mensaje", "El personaje " + personaje.getNombre() + " se elimino de la película " + peliculaSerie.getTitulo()), service.deletePersonalizado("1", "1"));
    }
}