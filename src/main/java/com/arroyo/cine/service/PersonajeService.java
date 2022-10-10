package com.arroyo.cine.service;

import com.arroyo.cine.dto.PersonajeDto;
import com.arroyo.cine.entity.Personaje;
import com.arroyo.cine.exception.custom.personaje.PersonajeExcepcion;
import com.arroyo.cine.mapper.personaje.PersonajeMapper;
import com.arroyo.cine.repository.PersonajeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.arroyo.cine.service.validacion.ValidacionGenerica.convertirEntero;
import static com.arroyo.cine.service.validacion.ValidacionGenerica.validarId;
import static com.arroyo.cine.service.validacion.personaje.ParametroEntradaPersonaje.*;
import static com.arroyo.cine.util.statico.RespuestaExcepcion.*;

@Service
public class PersonajeService {

    private final PersonajeRepository repository;

    private final PersonajeMapper mapper;


    private Personaje personaje;

    public PersonajeService(PersonajeRepository repository, PersonajeMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<PersonajeDto> getAll(String name, String age, String movie) {
        return mapper.aListPersonajeDto(filtroPersonaje(repository.findAll(), name, age, movie, true));
    }

    public PersonajeDto getById(String idPersonaje) {
        return mapper.aPersonajeDto(buscarConId(idPersonaje));
    }

    @Transactional
    public PersonajeDto save(PersonajeDto personajeDto) {
        validarPersonajeDto(personajeDto);
        verificarParametrosEntradaPersonaje(personajeDto);
        return mapper.aPersonajeDto(repository.save(mapper.aPersonaje(personajeDto)));
    }

    @Transactional
    public PersonajeDto update(String idPersonaje, PersonajeDto personajeDto) {
        this.personaje = null;
        validarPersonajeDto(personajeDto);
        this.personaje = buscarConId(idPersonaje);
        verificarParametrosEntradaPersonaje(personajeDto);
        repository.save(verificarDatoModificar(this.personaje, personajeDto));
        return mapper.aPersonajeDto(buscarConId(idPersonaje));
    }

    @Transactional
    public PersonajeDto delete(PersonajeDto personajeDto) {
        this.personaje = null;
        validarPersonajeDto(personajeDto);
        this.personaje = buscarConId(personajeDto.getIdePersonaje());
        verificarParametrosEntradaPersonaje(personajeDto);
        verificarPersonajeDBConPersonajeDto(this.personaje, personajeDto);
        repository.delete(mapper.aPersonaje(personajeDto));
        return mapper.aPersonajeDto(this.personaje);
    }

    @Transactional
    public PersonajeDto deleteById(String idPersonaje) {
        this.personaje = null;
        this.personaje = buscarConId(idPersonaje);
        repository.deleteById(convertirEntero(idPersonaje));
        return mapper.aPersonajeDto(this.personaje);
    }

    private Personaje buscarConId(String idPersonaje) {
        validarId(idPersonaje, POR_FAVOR_INGRESE + EL_ID+ DE_EL + PERSONAJE + VALIDO);
        return repository.findById(convertirEntero(idPersonaje)).orElseThrow(() ->
                new PersonajeExcepcion(CODIGO_ERROR, ERROR, EL + PERSONAJE + NO_DISPONIBLE, HttpStatus.BAD_REQUEST));
    }
}
