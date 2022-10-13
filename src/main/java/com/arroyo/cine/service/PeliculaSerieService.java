package com.arroyo.cine.service;

import com.arroyo.cine.exception.Excepcion;
import com.arroyo.cine.model.dto.PeliculaSerieDto;
import com.arroyo.cine.model.entity.FkPeliculaSeriePersonaje;
import com.arroyo.cine.model.entity.PeliculaSerie;
import com.arroyo.cine.model.entity.PeliculaSeriePersonaje;
import com.arroyo.cine.model.entity.Personaje;
import com.arroyo.cine.model.mapper.pelicula_serie.PeliculaSerieMapper;
import com.arroyo.cine.repository.GeneroRepository;
import com.arroyo.cine.repository.PeliculaSerieRepository;
import com.arroyo.cine.repository.PersonajeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.arroyo.cine.service.validacion.ValidacionGenerica.convertirEntero;
import static com.arroyo.cine.service.validacion.ValidacionGenerica.validarId;
import static com.arroyo.cine.service.validacion.pelicula.serie.ParametroEntrada.*;
import static com.arroyo.cine.util.statico.MensajeError.*;

@Service
public class PeliculaSerieService {
    private final PeliculaSerieRepository repository;
    private final GeneroRepository generoRepository;
    private final PersonajeRepository personajeRepository;
    private final PeliculaSeriePersonajeService peliculaSeriePersonajeService;
    private final PeliculaSerieMapper mapper;
    private static final int directorio = 3;

    public PeliculaSerieService(PeliculaSerieRepository repository, GeneroRepository generoRepository, PersonajeRepository personajeRepository, PeliculaSeriePersonajeService peliculaSeriePersonajeService, PeliculaSerieMapper mapper) {
        this.repository = repository;
        this.generoRepository = generoRepository;
        this.personajeRepository = personajeRepository;
        this.peliculaSeriePersonajeService = peliculaSeriePersonajeService;
        this.mapper = mapper;
    }

    public List<PeliculaSerieDto> getAll(String name, Integer genre, String order) {
        return mapper.aListPeliculaSerieDto(filtroPeliculaSerie(repository.findAll(), name, genre, order));
    }

    public PeliculaSerieDto getById(String idPeliculaSerie) {
        return mapper.aPeliculaSerieDto(buscarPeliculaSerieConId(idPeliculaSerie));
    }

    @Transactional
    public PeliculaSerieDto save(PeliculaSerieDto peliculaSerie) {
        validarPeliculaSerieDto(peliculaSerie);
        buscarGeneroConId(peliculaSerie.getIdGenero());
        verificarParametrosEntradaPeliculaSerie(peliculaSerie);
        verificarParametrosEntradaPersonajes(peliculaSerie.getPersonajes());
        return mapper.aPeliculaSerieDto(repository.save(mapper.aPeliculaSerie(peliculaSerie)));
    }

    @Transactional
    public PeliculaSerieDto savePersonalizado(String idPeliculaSerie, String idPersonaje) {
        PeliculaSerie peliculaSerie = buscarPeliculaSerieConId(idPeliculaSerie);
        Personaje personaje = buscarPersonajeConId(idPersonaje);
        peliculaSeriePersonajeService.save(new PeliculaSeriePersonaje(new FkPeliculaSeriePersonaje(peliculaSerie.getIdPeliculaSerie(), personaje.getIdPersonaje())));
        return mapper.aPeliculaSerieDto(buscarPeliculaSerieConId(idPeliculaSerie));
    }

    @Transactional
    public PeliculaSerieDto update(String idPeliculaSerie, PeliculaSerieDto peliculaSerieDto) {
        validarPeliculaSerieDto(peliculaSerieDto);
        PeliculaSerie peliculaSerie = buscarPeliculaSerieConId(idPeliculaSerie);
        buscarGeneroConId(peliculaSerieDto.getIdGenero());
        return mapper.aPeliculaSerieDto(repository.save(identificarParametroActualizar(peliculaSerie, peliculaSerieDto)));
    }

    @Transactional
    public PeliculaSerieDto delete(PeliculaSerieDto peliculaSerieDto) {
        validarPeliculaSerieDto(peliculaSerieDto);
        PeliculaSerie peliculaSerie = buscarPeliculaSerieConId(peliculaSerieDto.getIdPeliculaSerie());
        verificarParametrosEntradaPeliculaSerie(peliculaSerieDto);
        validarDatosSonIgual(peliculaSerie, peliculaSerieDto);
        repository.delete(mapper.aPeliculaSerie(setearPersonajesNull(peliculaSerieDto)));
        return mapper.aPeliculaSerieDto(peliculaSerie);
    }

    @Transactional
    public void deletePersonalizado(String idPeliculaSerie, String idPersonaje) {
        PeliculaSerie peliculaSerie = buscarPeliculaSerieConId(idPeliculaSerie);
        Personaje personaje = buscarPersonajeConId(idPersonaje);
        validarTablaIntermedia(peliculaSerie.getIdPeliculaSerie(), personaje.getIdPersonaje());
        peliculaSeriePersonajeService.delete(new PeliculaSeriePersonaje(new FkPeliculaSeriePersonaje(peliculaSerie.getIdPeliculaSerie(), personaje.getIdPersonaje())));
    }

    @Transactional
    public PeliculaSerieDto deleteById(String idPeliculaSerie) {
        Optional<PeliculaSerie> peliculaSerie = Optional.of(buscarPeliculaSerieConId(idPeliculaSerie));
        peliculaSerie.ifPresent(peliculaSerie1 -> repository.deleteById(peliculaSerie1.getIdPeliculaSerie()));
        return mapper.aPeliculaSerieDto(peliculaSerie.get());
    }

    private PeliculaSerieDto setearPersonajesNull(PeliculaSerieDto peliculaSerieDto) {
        peliculaSerieDto.setPersonajes(null);
        return peliculaSerieDto;
    }

    private PeliculaSerie buscarPeliculaSerieConId(String idPeliculaSerie) {
        validarId(idPeliculaSerie, POR_FAVOR_INGRESE + EL_ID + "de la " + PELICULA_SERIE + VALIDO);
        return repository.findById(convertirEntero(idPeliculaSerie)).orElseThrow(() ->
                new Excepcion(MENSAJE_CODIGO_ERROR, ERROR, LA + PELICULA_SERIE + NO_DISPONIBLE, HttpStatus.BAD_REQUEST));
    }

    private void buscarGeneroConId(String idGenero) {
        validarId(idGenero, POR_FAVOR_INGRESE + EL_ID + DE_EL + GENERO + VALIDO);
        if (generoRepository.findById(convertirEntero(idGenero)).isEmpty())
            throw new Excepcion(MENSAJE_CODIGO_ERROR, ERROR, POR_FAVOR_VERIFIQUE + "el id " + GENERO + ".", HttpStatus.BAD_REQUEST);
    }

    private Personaje buscarPersonajeConId(String idPersonaje) {
        validarId(idPersonaje, POR_FAVOR_INGRESE + EL_ID + DE_EL + PERSONAJE + VALIDO);
        return personajeRepository.findById(convertirEntero(idPersonaje)).orElseThrow(() ->
                new Excepcion(MENSAJE_CODIGO_ERROR, ERROR, EL + PERSONAJE + NO_DISPONIBLE, HttpStatus.BAD_REQUEST));
    }

    private void validarTablaIntermedia(Integer idPeli, Integer idPersonaje) {
        if (peliculaSeriePersonajeService.getByIdPeliculaSerieAndIdPersonaje(idPeli, idPersonaje) == null)
            throw new Excepcion(MENSAJE_CODIGO_ERROR, ERROR, POR_FAVOR_VERIFIQUE + "la información ingresada.", HttpStatus.BAD_REQUEST);
    }
}
