package com.arroyo.cine.service;

import com.arroyo.cine.dto.PersonajeDto;
import com.arroyo.cine.entity.Personaje;
import com.arroyo.cine.exception.custom.personaje.PersonajeExcepciones;
import com.arroyo.cine.exception.custom.personaje.PersonajeExcepcion;
import com.arroyo.cine.mapper.personaje.PersonajeMapper;
import com.arroyo.cine.repository.PersonajeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

import static com.arroyo.cine.service.ValidacionCompartida.*;
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

    public List<PersonajeDto> getAll(String name, Byte age, Integer movie) {
        return mapper.aListPersonajeDto(filtro(repository.findAll(), name, age, movie, true));
    }

    public PersonajeDto getById(@NotNull Integer idPersonaje) {
        return mapper.aPersonajeDto(buscarConId(idPersonaje));
    }

    @Transactional
    public PersonajeDto save(@NotNull PersonajeDto personajeDto) {
        verificarParametrosEntradaPersonaje(personajeDto);
        return mapper.aPersonajeDto(repository.save(mapper.aPersonaje(personajeDto)));
    }

    @Transactional
    public PersonajeDto update(@NotNull Integer idPersonaje, @NotNull PersonajeDto personajeDto) {
        this.personaje = null;
        this.personaje = buscarConId(idPersonaje);
        verificarParametrosEntradaPersonaje(personajeDto);
        repository.save(verificarDatoModificar(this.personaje, personajeDto));
        return mapper.aPersonajeDto(buscarConId(idPersonaje));
    }

    @Transactional
    public PersonajeDto delete(@NotNull PersonajeDto personajeDto) {
        this.personaje = null;
        this.personaje = buscarConId(personajeDto.getIdePersonaje());
        verificarParametrosEntradaPersonaje(personajeDto);
        verificarPersonajeDBConPersonajeDto(this.personaje, personajeDto);
        repository.delete(mapper.aPersonaje(personajeDto));
        return mapper.aPersonajeDto(this.personaje);
    }

    @Transactional
    public PersonajeDto deleteById(@NotNull Integer idPersonaje) {
        this.personaje = null;
        this.personaje = buscarConId(idPersonaje);
        repository.deleteById(idPersonaje);
        return mapper.aPersonajeDto(this.personaje);
    }

    private Personaje buscarConId(Integer idPersonaje) {
        return repository.findById(idPersonaje).orElseThrow(() ->
                new PersonajeExcepcion(CODIGO,MENSAJE, EL + PERSONAJE + NO_DISPONIBLE, HttpStatus.OK));
    }

    private final BiFunction<Personaje, Boolean, Personaje> setearNull = (personajeFuncion, setear) -> {
        if (Boolean.TRUE.equals(setear)) {
            personajeFuncion.setIdPersonaje(null);
            personajeFuncion.setHistoria(null);
            personajeFuncion.setPeso(null);
            personajeFuncion.setPeliculaSeries(null);
            personajeFuncion.setEdad(null);
        }
        return personajeFuncion;
    };

    private Personaje verificarDatoModificar(Personaje personaje, PersonajeDto dto) {
        if (dto.getNombre() != null && !dto.getNombre().isBlank()) personaje.setNombre(dto.getNombre());
        if (dto.getEdad() != null && Byte.parseByte(dto.getEdad()) > 0)
            personaje.setEdad(Byte.parseByte(dto.getEdad()));
        if (dto.getPeso() != null && Float.parseFloat(dto.getPeso()) > 0)
            personaje.setPeso(Float.parseFloat(dto.getPeso()));
        if (dto.getImagen() != null && !dto.getImagen().isBlank()) personaje.setImagen(dto.getImagen());
        if (dto.getHistoria() != null && !dto.getHistoria().isBlank()) personaje.setHistoria(dto.getHistoria());
        return personaje;
    }

    private List<Personaje> filtro(List<Personaje> personajes, String name, Byte age, Integer movie, Boolean setear) {
        if (personajes.isEmpty())
            throw new PersonajeExcepcion(CODIGO,MENSAJE, NO_HAY + PERSONAJE + DISPONIBLE, HttpStatus.OK);
        if (name != null && !name.isBlank() && age != null && age > 0 && movie != null && movie > 0)
            return personajes.stream().filter(personajeStream -> personajeStream.getNombre().equals(name) && Objects.equals(personajeStream.getEdad(), age) && Objects.equals(personajeStream.getIdPersonaje(), movie)).map(personajeStream -> setearNull.apply(personaje, setear)).collect(Collectors.toList());
        else if (name != null && !name.isBlank())
            return personajes.stream().filter(personajeStream -> personajeStream.getNombre().equals(name)).map(personajeStream -> setearNull.apply(personajeStream, setear)).collect(Collectors.toList());
        else if (age != null && age > 0)
            return personajes.stream().filter(personajeStream -> Objects.equals(personajeStream.getEdad(), age)).map(personajeStream -> setearNull.apply(personajeStream, setear)).collect(Collectors.toList());
        else if (movie != null && movie > 0)
            return personajes.stream().filter(personajeStream -> Objects.equals(personajeStream.getIdPersonaje(), movie)).map(personajeStream -> setearNull.apply(personajeStream, setear)).collect(Collectors.toList());
        else if (name == null && age == null && movie == null) return personajes;
        else return new ArrayList<>();
    }

    public void verificarPersonajeDBConPersonajeDto(Personaje db, PersonajeDto dto) {
        List<String> errores = new ArrayList<>();
        if (!db.getIdPersonaje().equals(dto.getIdePersonaje()))
            errores.add(POR_FAVOR_VERIFIQUE + "el id " + DE_EL + PERSONAJE + ".");
        if (!db.getNombre().equals(dto.getNombre()))
            errores.add(POR_FAVOR_VERIFIQUE + "el nombre " + DE_EL + PERSONAJE + ".");
        if (!db.getEdad().equals(convertirByte(dto.getEdad())))
            errores.add(POR_FAVOR_VERIFIQUE + "la edad " + DE_EL + PERSONAJE + ".");
        if (!db.getPeso().equals(convertirFloat(dto.getPeso())))
            errores.add(POR_FAVOR_VERIFIQUE + "el peso " + DE_EL + PERSONAJE + ".");
        if (!db.getImagen().equals(dto.getImagen()))
            errores.add(POR_FAVOR_VERIFIQUE + "la dirección de la imagen " + DE_EL + PERSONAJE + ".");
        if (!db.getHistoria().equals(dto.getHistoria()))
            errores.add(POR_FAVOR_VERIFIQUE + "la historia " + DE_EL + PERSONAJE + ".");
        if (!errores.isEmpty())
            throw new PersonajeExcepciones(CODIGO_ERROR,ERROR, errores, HttpStatus.BAD_REQUEST);
    }
}
