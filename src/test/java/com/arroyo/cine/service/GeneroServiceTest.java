package com.arroyo.cine.service;

import com.arroyo.cine.model.dto.GeneroDto;
import com.arroyo.cine.model.entity.Genero;
import com.arroyo.cine.model.entity.PeliculaSerie;
import com.arroyo.cine.model.mapper.genero.GeneroMapperImpl;
import com.arroyo.cine.model.mapper.personaje.PeliculaSerieComplementoMapper;
import com.arroyo.cine.model.mapper.personaje.PeliculaSerieComplementoMapperImpl;
import com.arroyo.cine.repository.GeneroRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.arroyo.cine.service.validacion.imagen.GuardarImagen.borrarImagen;
import static org.junit.jupiter.api.Assertions.assertEquals;

class GeneroServiceTest {
    @Autowired
    private static final PeliculaSerieComplementoMapper peliculaSerieComplementoMapper = new PeliculaSerieComplementoMapperImpl();
    @Autowired
    private static final GeneroMapperImpl mapper = new GeneroMapperImpl(peliculaSerieComplementoMapper);
    @Autowired
    private static final GeneroRepository repository = Mockito.mock(GeneroRepository.class);
    @Autowired
    private static final GeneroService service = new GeneroService(repository, mapper);
    @Autowired
    private static final GeneroService serviceMock = Mockito.mock(GeneroService.class);

    private static GeneroDto generoDto;
    private static List<GeneroDto> generoDtoList;
    private static List<PeliculaSerie> peliculaSerieList;
    private static Genero genero;
    private static List<Genero> generoList;
    private static MockMultipartFile imagen;

    @BeforeAll
    static void beforeAll() {
        genero = new Genero();
        PeliculaSerie peliculaSerie = new PeliculaSerie();
        peliculaSerieList = new ArrayList<>();
        generoList = new ArrayList<>();
        generoDtoList = new ArrayList<>();

        peliculaSerie.setIdPeliculaSerie(1);
        peliculaSerie.setIdGenero(2);
        peliculaSerie.setImagen("Foto.jpg");
        peliculaSerie.setCalifiacion((byte) 5);
        peliculaSerie.setFechaCreacion(LocalDate.now());
        peliculaSerie.setTitulo("El agua");

        genero.setIdGenero(1);
        genero.setNombre("The boys");
        genero.setImagen("boys.jpg");

        peliculaSerieList.add(peliculaSerie);

        imagen = new MockMultipartFile("prueba",
                "prueba.jpg",
                MediaType.TEXT_PLAIN_VALUE,
                "prueba".getBytes()
        );
    }

    @AfterEach
    void tearDown() {
        borrarImagen(imagen.getOriginalFilename(), 1);
    }

    @Test
    void getAll() {
        genero.setPeliculaSerieList(peliculaSerieList);
        generoList.add(genero);
        generoDtoList = mapper.aListGeneroDto(generoList);
        Mockito.when(repository.findAll()).thenReturn(generoList);
        assertEquals(generoDtoList, service.getAll());
    }

    @Test
    void getById() {
        genero.setPeliculaSerieList(peliculaSerieList);
        Mockito.when(repository.findById(1)).thenReturn(Optional.of(genero));
        generoDto = mapper.aGeneroDto(genero);
        assertEquals(generoDto, service.getById("1"));
    }

    @Test
    void save() {
        generoDto = mapper.aGeneroDto(genero);
        Mockito.when(repository.save(genero)).thenReturn(genero);
        Mockito.when(serviceMock.save("La biblia", imagen)).thenReturn(generoDto);
        Mockito.when(service.save("La biblia", imagen)).thenReturn(generoDto);
        assertEquals(generoDto, serviceMock.save("La biblia", imagen));
    }

    @Test
    void update() {
        Genero genero = new Genero();
        genero.setIdGenero(1);
        genero.setNombre("The boys");
        genero.setImagen("boys.jpg");
        GeneroDto generoModificado = new GeneroDto("1", "The boys modificado", imagen.getOriginalFilename(), null);
        Mockito.when(repository.findById(1)).thenReturn(Optional.of(genero));
        Mockito.when(serviceMock.update("1", "The boys modificado", imagen)).thenReturn(generoModificado);
        assertEquals(generoModificado, service.update("1", "The boys modificado", imagen));
    }

    @Test
    void delete() {
        GeneroDto generoDto = new GeneroDto("1", "The boys","boys.jpg", null);
        Mockito.when(repository.findById(1)).thenReturn(Optional.of(genero));
        Mockito.when(serviceMock.delete(generoDto)).thenReturn(generoDto);
        assertEquals(generoDto, service.delete(generoDto));
    }

    @Test
    void deleteById() {
        Genero genero = new Genero();
        genero.setIdGenero(1);
        genero.setNombre("The boys");
        genero.setImagen("boys.jpg");
        GeneroDto generoDto = new GeneroDto("1", "The boys","boys.jpg", null);
        Mockito.when(repository.findById(1)).thenReturn(Optional.of(genero));
        Mockito.when(serviceMock.deleteById("1")).thenReturn(generoDto);
        assertEquals(generoDto, service.deleteById("1"));
    }

}