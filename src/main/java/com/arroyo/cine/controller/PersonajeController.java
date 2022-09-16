package com.arroyo.cine.controller;

import com.arroyo.cine.dto.PersonajeDto;
import com.arroyo.cine.service.PersonajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/personaje")
public class PersonajeController {

    @Autowired
    private PersonajeService personajeService;

    @GetMapping
    public List<PersonajeDto> getAll(){
        return personajeService.getAll();
    }

    @GetMapping("/getById/{idPersona}")
    public PersonajeDto getById(@PathVariable("idPersona") Integer idPersona){
        return personajeService.getById(idPersona);
    }

    @PostMapping
    public PersonajeDto save(@RequestBody PersonajeDto personajeDto){
        return personajeService.save(personajeDto);
    }

    @DeleteMapping
    public PersonajeDto delete(PersonajeDto personajeDto){
        return personajeService.delete(personajeDto);
    }

    @DeleteMapping("/deleteById/{idPersona}")
    public PersonajeDto deleteById(@PathVariable("idPersona") Integer idPersona){
        return personajeService.deleteById(idPersona);
    }

    @PutMapping
    public PersonajeDto update(PersonajeDto personajeDto){
        return personajeService.update(personajeDto);
    }
}
