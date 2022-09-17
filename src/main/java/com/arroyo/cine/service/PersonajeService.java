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
        if (personaje.getIdPersonaje() == null)
            return new PersonajeDto();
        return personajeMapper.aPersonajeDto(personaje);
    }

    public PersonajeDto save(PersonajeDto personajeDto) {
        Personaje personaje = personajeRepository.save(personajeMapper.aPersonaje(personajeDto));
        return personajeMapper.aPersonajeDto(personaje);
    }

    public PersonajeDto update(PersonajeDto personajeDto) {
        Personaje personaje = personajeRepository.findById(personajeDto.getIdePersonaje()).orElse(new Personaje());
        if (personaje.getIdPersonaje() == null)
            return new PersonajeDto();
        return personajeMapper.aPersonajeDto(personajeRepository.save(personajeMapper.aPersonaje(personajeDto)));
    }

    public PersonajeDto delete(PersonajeDto personajeDto) {
        Personaje personaje = personajeRepository.findById(personajeDto.getIdePersonaje()).orElse(new Personaje());
        if (personaje.getIdPersonaje() == null)
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

}
