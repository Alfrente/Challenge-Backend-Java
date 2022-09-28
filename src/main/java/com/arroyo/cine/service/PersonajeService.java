package com.arroyo.cine.service;

import com.arroyo.cine.dto.personaje.PersonajeDto;
import com.arroyo.cine.dto.personaje.PersonajePersonalizadoPDto;
import com.arroyo.cine.entity.Personaje;
import com.arroyo.cine.mapper.personaje.PersonajeMapper;
import com.arroyo.cine.mapper.personaje.PersonajePersonalizadoMapper;
import com.arroyo.cine.repository.PersonajeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.Null;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

@Service
public class PersonajeService {
    @Autowired
    private PersonajeRepository repository;
    @Autowired
    private PersonajeMapper mapper;

    @Autowired
    private PersonajePersonalizadoMapper mapperP;

    public List<PersonajeDto> getAll(String name, Byte age, Integer movie) {
        return mapper.aListPersonajeDto(filtro(repository.findAll(), name, age, movie, true));
    }

    public List<PersonajePersonalizadoPDto> getAllPersonalizado(String name, Byte age, Integer movie) {
        return mapperP.aListPersonajePersonalizadoDto(filtro(repository.findAll(), name, age, movie, false));
    }

    public PersonajeDto getById(Integer idPersonaje) {
        Personaje personaje = repository.findById(idPersonaje).orElse(new Personaje());
        if (personaje.getIdPersonaje() == null || idPersonaje <= 0)
            return new PersonajeDto();
        return mapper.aPersonajeDto(personaje);
    }

    @Transactional
    public PersonajeDto save(PersonajeDto personajeDto) {
        if (personajeDto == null || !validarDatosGuardar(personajeDto))
            return new PersonajeDto();
        Personaje personaje = repository.save(mapper.aPersonaje(personajeDto));
        return mapper.aPersonajeDto(personaje);
    }

    @Transactional
    public PersonajeDto update(Integer idPersonaje, PersonajeDto personajeDto) {
        Personaje personaje = repository.findById(idPersonaje).orElse(new Personaje());
        if (personaje.getIdPersonaje() == null) {
            return new PersonajeDto();
        }
        repository.save(validarParametros(personaje, personajeDto));
        return repository.findById(idPersonaje).map(personaje1 -> mapper.aPersonajeDto(personaje1)).orElse(new PersonajeDto());
    }

    @Transactional
    public PersonajeDto delete(PersonajeDto personajeDto) {
        if (personajeDto.getIdePersonaje() == null)
            return new PersonajeDto();
        Personaje personaje = repository.findById(personajeDto.getIdePersonaje()).orElse(new Personaje());
        if (personaje.getIdPersonaje() == null || !validarTodosLosDatos(personajeDto))
            return new PersonajeDto();
        repository.delete(mapper.aPersonaje(personajeDto));
        return mapper.aPersonajeDto(personaje);
    }

    @Transactional
    public PersonajeDto deleteById(Integer idPersonaje) {
        Personaje personaje = repository.findById(idPersonaje).orElse(new Personaje());
        if (personaje.getIdPersonaje() == null)
            return new PersonajeDto();
        repository.deleteById(idPersonaje);
        return mapper.aPersonajeDto(personaje);
    }

    private boolean validarDatosGuardar(PersonajeDto personaje) {
        return personaje.getNombre() != null && personaje.getEdad() != null &&
                personaje.getPeso() != null && !personaje.getNombre().isBlank()
                && personaje.getEdad() > 0 && personaje.getPeso() > 0;
    }

    private boolean validarTodosLosDatos(PersonajeDto dto) {
        return dto.getIdePersonaje() != null && dto.getIdePersonaje() > 0
                && dto.getNombre() != null && !dto.getNombre().isBlank()
                && dto.getEdad() != null && dto.getEdad() > 0
                && dto.getPeso() != null && dto.getPeso() > 0
                && dto.getImagen() != null && !dto.getImagen().isBlank()
                && dto.getHistoria() != null && !dto.getHistoria().isBlank();
    }

    private Personaje validarParametros(Personaje personaje, PersonajeDto dto) {
        if (dto.getNombre() != null && !dto.getNombre().isBlank()) {
            personaje.setNombre(dto.getNombre());
        }
        if (dto.getEdad() != null && dto.getEdad() > 0) {
            personaje.setEdad(dto.getEdad());
        }
        if (dto.getPeso() != null && dto.getPeso() > 0) {
            personaje.setPeso(dto.getPeso());
        }
        if (dto.getImagen() != null && !dto.getImagen().isBlank()) {
            personaje.setImagen(dto.getImagen());
        }
        if (dto.getHistoria() != null && !dto.getHistoria().isBlank()) {
            personaje.setHistoria(dto.getHistoria());
        }
        return personaje;
    }

    private List<Personaje> filtro(List<Personaje> personajes, @Null String name, @Null Byte age, @Null Integer movie, Boolean setear) {
        if (name != null && !name.isBlank() && age != null && age > 0 && movie != null && movie > 0)
            return personajes.stream().filter(personaje -> personaje.getNombre().equals(name) && Objects.equals(personaje.getEdad(), age) && Objects.equals(personaje.getIdPersonaje(), movie)).map(personaje -> setearNull.apply(personaje, setear)).collect(Collectors.toList());
        else if (name != null && !name.isBlank())
            return personajes.stream().filter(personaje -> personaje.getNombre().equals(name)).map(personaje -> setearNull.apply(personaje, setear)).collect(Collectors.toList());
        else if (age != null && age > 0)
            return personajes.stream().filter(personaje -> Objects.equals(personaje.getEdad(), age)).map(personaje -> setearNull.apply(personaje, setear)).collect(Collectors.toList());
        else if (movie != null && movie > 0)
            return personajes.stream().filter(personaje -> Objects.equals(personaje.getIdPersonaje(), movie)).map(personaje -> setearNull.apply(personaje, setear)).collect(Collectors.toList());
        else if (name == null && age == null && movie == null)
            return personajes;
        else
            return new ArrayList<>();
    }

    private final BiFunction<Personaje, Boolean, Personaje> setearNull = (personaje, setear) -> {
        if (Boolean.TRUE.equals(setear)) {
            personaje.setIdPersonaje(null);
            personaje.setHistoria(null);
            personaje.setPeso(null);
            personaje.setPeliculaSeries(null);
            personaje.setEdad(null);
        }
        return personaje;
    };
}
