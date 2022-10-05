package com.arroyo.cine.service;

import com.arroyo.cine.dto.personaje.PersonajeDto;
import com.arroyo.cine.dto.personaje.PersonajePersonalizadoPDto;
import com.arroyo.cine.entity.Personaje;
import com.arroyo.cine.exception.custom.pelicula.serie.PersonajeExcepciones;
import com.arroyo.cine.exception.custom.personaje.PersonajeExcepcionGenerico;
import com.arroyo.cine.mapper.personaje.PersonajeMapper;
import com.arroyo.cine.mapper.personaje.PersonajePersonalizadoMapper;
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

    private final PersonajePersonalizadoMapper mapperP;

    private Personaje personaje;

    public PersonajeService(PersonajeRepository repository, PersonajeMapper mapper, PersonajePersonalizadoMapper mapperP) {
        this.repository = repository;
        this.mapper = mapper;
        this.mapperP = mapperP;
    }

    public List<PersonajeDto> getAll(String name, Byte age, Integer movie) {
        return mapper.aListPersonajeDto(filtro(repository.findAll(), name, age, movie, true));
    }

    public List<PersonajePersonalizadoPDto> getAllPersonalizado(String name, Byte age, Integer movie) {
        return mapperP.aListPersonajePersonalizadoDto(filtro(repository.findAll(), name, age, movie, false));
    }

    public PersonajeDto getById(@NotNull Integer idPersonaje) {
        return mapper.aPersonajeDto(buscarConId(idPersonaje));
    }

    @Transactional
    public PersonajeDto save(@NotNull PersonajeDto personajeDto) {
        verificarParametrosEntrada(personajeDto);
        return mapper.aPersonajeDto(repository.save(mapper.aPersonaje(personajeDto)));
    }

    @Transactional
    public PersonajeDto update(@NotNull Integer idPersonaje, @NotNull PersonajeDto personajeDto) {
        this.personaje = null;
        this.personaje = buscarConId(idPersonaje);
        verificarParametrosEntrada(personajeDto);
        repository.save(verificarDatoModificar(this.personaje, personajeDto));
        return mapper.aPersonajeDto(buscarConId(idPersonaje));
    }

    @Transactional
    public PersonajeDto delete(@NotNull PersonajeDto personajeDto) {
        this.personaje = null;
        verificarParametrosEntrada(personajeDto);
        this.personaje = buscarConId(personajeDto.getIdePersonaje());
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
                new PersonajeExcepcionGenerico(ID_PERSONAJE_NO_DISPONIBLE, HttpStatus.BAD_REQUEST));
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
        if (personajes.isEmpty()) throw new PersonajeExcepcionGenerico(SIN_PERSONAJE, HttpStatus.BAD_REQUEST);
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
            errores.add(ID_PERSONAJE_DIFERENTE);
        if (!db.getNombre().equals(dto.getNombre()))
            errores.add(NOMBRE_DIFERENTE);
        if (!db.getEdad().equals(convertirByte(dto.getEdad())))
            errores.add(EDAD_DIFERENTE);
        if (!db.getPeso().equals(convertirFloat(dto.getPeso())))
            errores.add(PESO_DIFERENTE);
        if (!db.getImagen().equals(dto.getImagen()))
            errores.add(IMAGEN_DIFERENTE);
        if (!db.getHistoria().equals(dto.getHistoria()))
            errores.add(HISTORIA_DIFERENTE);
        if (!errores.isEmpty())
            throw new PersonajeExcepciones(errores, HttpStatus.BAD_REQUEST);
    }
}
