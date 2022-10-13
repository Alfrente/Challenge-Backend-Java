package com.arroyo.cine.service;

import com.arroyo.cine.exception.Excepcion;
import com.arroyo.cine.mapper.personaje.PersonajeMapper;
import com.arroyo.cine.model.dto.PersonajeDto;
import com.arroyo.cine.model.entity.Personaje;
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
import static com.arroyo.cine.util.statico.RespuestaExcepcion.*;

@Service
public class PersonajeService {
    private final PersonajeRepository repository;
    private final PersonajeMapper mapper;
    private String nombreImagenVieja;
    private static final int directorio = 2;

    public PersonajeService(PersonajeRepository repository, PersonajeMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<PersonajeDto> getAll(String name, String age, String movie) {
        List<Personaje> personajes = repository.findAll();
        personajes.forEach(personaje1 -> personaje1.setImagen(devolverDirectorioImagen(personaje1.getImagen(), 2).toString()));
        return mapper.aListPersonajeDto(filtroPersonaje(personajes, name, age, movie, true));
    }

    public PersonajeDto getById(String idPersonaje) {
        Personaje personaje = buscarConId(idPersonaje);
        personaje.setImagen(devolverDirectorioImagen(personaje.getImagen(), directorio).toString());
        return mapper.aPersonajeDto(personaje);
    }

    @Transactional
    public PersonajeDto save(String nombre, String edad, String peso, MultipartFile imagen, String historia) {
        validarPersonajeDto(nombre, edad, peso);
        PersonajeDto dto = crearPersonajeDto(nombre, edad, peso, historia);
        validarDatoRecibido(dto);
        dto.setImagen(guardarImagen(imagen, directorio));
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
        Personaje personaje = buscarConId(personajeDto.getIdPersonaje());
        validarDatoRecibido(personajeDto);
        compararPersonajeConPersonajeDto(personaje, personajeDto);
        repository.delete(mapper.aPersonaje(personajeDto));
        borrarImagen(personaje.getImagen(), directorio);
        return mapper.aPersonajeDto(personaje);
    }

    @Transactional
    public PersonajeDto deleteById(String idPersonaje) {
        Personaje personaje = buscarConId(idPersonaje);
        repository.deleteById(convertirEntero(idPersonaje));
        borrarImagen(personaje.getImagen(), directorio);
        return mapper.aPersonajeDto(personaje);
    }

    private Personaje buscarConId(String idPersonaje) {
        validarId(idPersonaje, POR_FAVOR_INGRESE + EL_ID + DE_EL + PERSONAJE + VALIDO);
        return repository.findById(convertirEntero(idPersonaje)).orElseThrow(() ->
                new Excepcion(MENSAJE_CODIGO_ERROR, ERROR, EL + PERSONAJE + NO_DISPONIBLE, HttpStatus.BAD_REQUEST));
    }

    private PersonajeDto crearPersonajeDto(String nombre, String edad, String peso, String historia) {
        PersonajeDto dto = new PersonajeDto();
        dto.setNombre(nombre);
        dto.setEdad(edad);
        dto.setPeso(peso);
        dto.setHistoria(historia);
        return dto;
    }

    public PersonajeDto verificarDatoModificar(String nombre, String edad, String peso, MultipartFile imagen, String historia, Personaje personaje) {
        validarNombreError(nombre);
        validarEdadError(edad);
        validarPesoError(peso);
        validarHistoriaError(historia);

        if (nombre != null && !validarNombre(nombre)) personaje.setNombre(nombre);
        if (edad != null && Byte.parseByte(edad) > 0) personaje.setEdad(Byte.parseByte(edad));
        if (peso != null && Float.parseFloat(peso) > 0) personaje.setPeso(Float.parseFloat(peso));
        if (historia != null && !historia.isBlank()) personaje.setHistoria(historia);

        if (imagen != null) {
            nombreImagenVieja = personaje.getImagen();
            personaje.setImagen(guardarImagen(imagen, directorio));
            borrarImagenActualizar(nombreImagenVieja, Objects.requireNonNull(imagen.getOriginalFilename()), 2);
        }

        return mapper.aPersonajeDto(personaje);
    }
}
