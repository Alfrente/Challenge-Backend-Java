package com.arroyo.cine.service;

import com.arroyo.cine.dto.PersonajeDto;
import com.arroyo.cine.entity.Personaje;
import com.arroyo.cine.mapper.PersonajeMapper;
import com.arroyo.cine.repository.PersonajeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonajeService {

    @Autowired
    private PersonajeRepository personajeRepository;

    @Autowired
    private PersonajeMapper personajeMapper;

    public List<PersonajeDto> getAll() {
        return personajeMapper.aListPersonajeDto(personajeRepository.findAll());
    }

    public PersonajeDto getById(Integer idPersonaje) {
        Personaje personaje = personajeRepository.findById(idPersonaje).orElse(new Personaje());
        if (personaje.getIdPersonaje() == null || idPersonaje <= 0)
            return new PersonajeDto();
        return personajeMapper.aPersonajeDto(personaje);
    }

    public PersonajeDto save(PersonajeDto personajeDto) {
        if (personajeDto == null || validarDatosGuardar(personajeDto))
            return new PersonajeDto();
        Personaje personaje = personajeRepository.save(personajeMapper.aPersonaje(personajeDto));
        return personajeMapper.aPersonajeDto(personaje);
    }

    public PersonajeDto update(Integer idPersonaje, PersonajeDto personajeDto) {
        Personaje personaje = personajeRepository.findById(idPersonaje).orElse(new Personaje());
        if (personaje.getIdPersonaje() == null) {
            return new PersonajeDto();
        }
        personajeRepository.save(validarParametros(personaje, personajeDto));
        return personajeRepository.findById(idPersonaje).map(personaje1 -> personajeMapper.aPersonajeDto(personaje1)).orElse(new PersonajeDto());
    }

    public PersonajeDto delete(PersonajeDto personajeDto) {
        Personaje personaje = personajeRepository.findById(personajeDto.getIdePersonaje()).orElse(new Personaje());
        if (personaje.getIdPersonaje() == null || validarTodosLosDatos(personajeDto))
            return new PersonajeDto();
        personajeRepository.delete(personajeMapper.aPersonaje(personajeDto));
        return personajeMapper.aPersonajeDto(personaje);
    }

    public PersonajeDto deleteById(Integer idPersonaje) {
        Personaje personaje = personajeRepository.findById(idPersonaje).orElse(new Personaje());
        if (personaje.getIdPersonaje() == null)
            return new PersonajeDto();
        personajeRepository.deleteById(idPersonaje);
        return personajeMapper.aPersonajeDto(personaje);
    }

    private boolean validarDatosGuardar(PersonajeDto personaje) {
        return personaje.getNombre() != null && personaje.getEdad() != null && personaje.getPeso() != null &&
                (!personaje.getNombre().isBlank()) && personaje.getEdad() > 0 && personaje.getPeso() > 0;
    }

    private boolean validarTodosLosDatos(PersonajeDto dto) {
        return dto.getIdePersonaje() != null && dto.getIdePersonaje() > 0 && dto.getNombre() != null && !dto.getNombre().isBlank() && dto.getEdad() != null && dto.getEdad() > 0 && dto.getPeso() != null && dto.getPeso() > 0
                && dto.getImagen() != null && !dto.getImagen().isBlank() && dto.getHistoria() != null && !dto.getHistoria().isBlank();
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

}
