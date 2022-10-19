package com.arroyo.cine.service;

import com.arroyo.cine.model.dto.PeliculaSerieDto;
import com.arroyo.cine.model.dto.PersonajeDto;
import com.arroyo.cine.model.entity.PeliculaSerie;
import com.arroyo.cine.model.entity.Personaje;
import com.arroyo.cine.model.mapper.personaje.PeliculaSerieComplementoMapper;
import com.arroyo.cine.model.mapper.personaje.PeliculaSerieComplementoMapperImpl;
import com.arroyo.cine.model.mapper.personaje.PersonajeMapper;
import com.arroyo.cine.model.mapper.personaje.PersonajeMapperImpl;
import com.arroyo.cine.repository.PersonajeRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.arroyo.cine.service.validacion.imagen.GuardarImagen.borrarImagen;
import static com.arroyo.cine.service.validacion.imagen.GuardarImagen.crearDirectorio;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class PersonajeServiceTest {
    private static final PeliculaSerieComplementoMapper peliculaSerieMapper = new PeliculaSerieComplementoMapperImpl();
    private static final PersonajeRepository repository = Mockito.mock(PersonajeRepository.class);
    private static final PersonajeMapper mapper = new PersonajeMapperImpl(peliculaSerieMapper);
    private static final PersonajeService service = new PersonajeService(repository, mapper);
    private static final Personaje personaje = new Personaje();
    private static final PeliculaSerie peliculaSerie = new PeliculaSerie();
    private static final List<Personaje> personajeList = new ArrayList<>();
    private static final MockMultipartFile imagen = new MockMultipartFile("prueba", "prueba.jpg", MediaType.TEXT_PLAIN_VALUE, "prueba".getBytes());

    @BeforeAll
    static void beforeAll() {
        crearDirectorio();
        peliculaSerie.setIdPeliculaSerie(1);
        peliculaSerie.setTitulo("Los simson");
        peliculaSerie.setImagen("prueba.jpg");
        peliculaSerie.setIdGenero(1);
        peliculaSerie.setCalifiacion((byte) 5);
        peliculaSerie.setFechaCreacion(LocalDate.now());

        personaje.setIdPersonaje(1);
        personaje.setImagen(imagen.getOriginalFilename());
        personaje.setPeso(18.47F);
        personaje.setEdad((byte) 20);
        personaje.setNombre("Omar");
        personaje.setHistoria("Soy productor");
        personaje.setPeliculaSeries(List.of(peliculaSerie));
    }

    @Test
    void getAll() {
        personaje.setPeliculaSeries(List.of(peliculaSerie));
        personajeList.add(personaje);
        List<PersonajeDto> personajeDtoList = mapper.aListPersonajeDto(personajeList);
        Mockito.when(repository.findAll()).thenReturn(personajeList);
        assertEquals(personajeDtoList, service.getAll(null, null, null));
    }

    @Test
    void getById() {
        PersonajeDto personajeDto = mapper.aPersonajeDto(personaje);
        Mockito.when(repository.findById(1)).thenReturn(Optional.of(personaje));
        assertEquals(personajeDto, service.getById("1"));
    }

    @Test
    void save() {
        assertNull(service.save("Omar", "20", "18.47", imagen, "Soy productor"));
        borrarImagen(imagen.getOriginalFilename(), 2);
    }

    @Test
    void update() {
        PersonajeDto personajeDto = mapper.aPersonajeDto(personaje);
        Mockito.when(repository.findById(personaje.getIdPersonaje())).thenReturn(Optional.of(personaje));
        Mockito.when(repository.save(personaje)).thenReturn(personaje);
        assertEquals(personajeDto, service.update("1", "Omar", "20", "18.47", imagen, "Soy productor"));
        borrarImagen(imagen.getOriginalFilename(), 2);
    }

    @Test
    void delete() {
        PersonajeDto personajeDto = mapper.aPersonajeDto(personaje);
        Mockito.when(repository.findById(personaje.getIdPersonaje())).thenReturn(Optional.of(personaje));
        assertEquals(personajeDto, service.delete(personajeDto));
    }

    @Test
    void deleteById() {
        PersonajeDto personajeDto = mapper.aPersonajeDto(personaje);
        Mockito.when(repository.findById(personaje.getIdPersonaje())).thenReturn(Optional.of(personaje));
        assertEquals(personajeDto, service.deleteById("1"));
    }
}