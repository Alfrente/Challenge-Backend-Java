package com.arroyo.cine.service;

import com.arroyo.cine.exception.Excepcion;
import com.arroyo.cine.model.dto.PersonajeDto;
import com.arroyo.cine.model.entity.Personaje;
import com.arroyo.cine.model.mapper.personaje.PersonajeMapper;
import com.arroyo.cine.repository.PersonajeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Objects;

import static com.arroyo.cine.service.validacion.ValidacionGenerica.convertirEntero;
import static com.arroyo.cine.service.validacion.ValidacionGenerica.validarId;
import static com.arroyo.cine.service.validacion.imagen.GuardarImagen.*;
import static com.arroyo.cine.service.validacion.imagen.ValidarImagenEntrada.devolverDirectorioImagen;
import static com.arroyo.cine.service.validacion.personaje.ParametroEntradaPersonaje.*;
import static com.arroyo.cine.service.validacion.personaje.ValidarCampoIndividual.*;
import static com.arroyo.cine.util.statico.Directorio.DIRECTORIO_PERSONAJE;
import static com.arroyo.cine.util.statico.MensajeError.*;

@Service
public class PersonajeService {
    private final PersonajeRepository repository;
    private final PersonajeMapper mapper;

    public PersonajeService(PersonajeRepository repository, PersonajeMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<PersonajeDto> getAll(String name, String age, String movie) {
        List<Personaje> personajes = repository.findAll();
        personajes.forEach(personaje1 -> personaje1.setImagen(devolverDirectorioImagen(personaje1.getImagen(), DIRECTORIO_PERSONAJE).toString()));
        return mapper.aListPersonajeDto(filtroPersonaje(personajes, name, age, movie, true));
    }

    public PersonajeDto getById(String idPersonaje) {
        Personaje personaje = buscarConId(idPersonaje);
        personaje.setImagen(devolverDirectorioImagen(personaje.getImagen(), DIRECTORIO_PERSONAJE).toString());
        return mapper.aPersonajeDto(personaje);
    }

    @Transactional
    public PersonajeDto save(String nombre, String edad, String peso, MultipartFile imagen, String historia) {
        validarPersonajeDto(nombre, edad, peso);
        PersonajeDto dto = crearPersonajeDto(nombre, edad, peso, historia, imagen);
        validarDatoRecibido(dto);
        guardarImagen(imagen, DIRECTORIO_PERSONAJE);
        return mapper.aPersonajeDto(repository.save(mapper.aPersonaje(dto)));
    }

    @Transactional
    public PersonajeDto update(String idPersonaje, String nombre, String edad, String peso, MultipartFile imagen, String historia) {
        Personaje personaje = buscarConId(idPersonaje);
        repository.save(mapper.aPersonaje(verificarDatoModificar(nombre, edad, peso, imagen, historia, personaje)));
        return mapper.aPersonajeDto(buscarConId(idPersonaje));
    }

    @Transactional
    public PersonajeDto delete(PersonajeDto personajeDto) {
        validarPersonajeDto(personajeDto);
        Personaje personaje = buscarConId(personajeDto.idPersonaje());
        validarDatoRecibido(personajeDto);
        compararPersonajeConPersonajeDto(personaje, personajeDto);
        repository.delete(mapper.aPersonaje(personajeDto));
        borrarImagen(personaje.getImagen(), DIRECTORIO_PERSONAJE);
        return mapper.aPersonajeDto(personaje);
    }

    @Transactional
    public PersonajeDto deleteById(String idPersonaje) {
        Personaje personaje = buscarConId(idPersonaje);
        repository.deleteById(convertirEntero(idPersonaje));
        borrarImagen(personaje.getImagen(), DIRECTORIO_PERSONAJE);
        return mapper.aPersonajeDto(personaje);
    }

    private Personaje buscarConId(String idPersonaje) {
        validarId(idPersonaje, POR_FAVOR_INGRESE + EL_ID + DE_EL + PERSONAJE + VALIDO);
        return repository.findById(convertirEntero(idPersonaje)).orElseThrow(() ->
                new Excepcion(MENSAJE_CODIGO_ERROR, ERROR, EL + PERSONAJE + NO_DISPONIBLE, HttpStatus.BAD_REQUEST));
    }

    private PersonajeDto crearPersonajeDto(String nombre, String edad, String peso, String historia, MultipartFile imagen) {
        return new PersonajeDto(null,nombre, edad, peso, historia, imagen.getOriginalFilename(), null);
    }

    private PersonajeDto verificarDatoModificar(String nombre, String edad, String peso, MultipartFile imagen, String historia, Personaje personaje) {
        validarNombreError(nombre);
        validarEdadError(edad);
        validarPesoError(peso);
        validarHistoriaError(historia);

        if (nombre != null && !validarNombre(nombre)) personaje.setNombre(nombre);
        if (edad != null && Byte.parseByte(edad) > 0) personaje.setEdad(Byte.parseByte(edad));
        if (peso != null && Float.parseFloat(peso) > 0) personaje.setPeso(Float.parseFloat(peso));
        if (historia != null && !historia.isBlank()) personaje.setHistoria(historia);

        if (imagen != null && personaje.getImagen() != null) {
            borrarImagenActualizar(personaje.getImagen(), Objects.requireNonNull(imagen.getOriginalFilename()), DIRECTORIO_PERSONAJE);
            personaje.setImagen(guardarImagen(imagen, DIRECTORIO_PERSONAJE));
        }

        return mapper.aPersonajeDto(personaje);
    }
}
