package com.arroyo.cine.service;

import com.arroyo.cine.exception.Excepcion;
import com.arroyo.cine.model.mapper.genero.GeneroMapper;
import com.arroyo.cine.model.dto.GeneroDto;
import com.arroyo.cine.model.entity.Genero;
import com.arroyo.cine.repository.GeneroRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Objects;

import static com.arroyo.cine.service.validacion.ValidacionGenerica.convertirEntero;
import static com.arroyo.cine.service.validacion.ValidacionGenerica.validarId;
import static com.arroyo.cine.service.validacion.genero.validarGeneroEntrada.*;
import static com.arroyo.cine.service.validacion.imagen.GuardarImagen.*;
import static com.arroyo.cine.service.validacion.imagen.ValidarImagenEntrada.devolverDirectorioImagen;
import static com.arroyo.cine.util.statico.MensajeError.*;

@Service
public class GeneroService {
    private final GeneroRepository repository;
    private final GeneroMapper mapper;

    private static final int directorio = 1;

    public GeneroService(GeneroRepository repository, GeneroMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<GeneroDto> getAll() {
        List<Genero> generoList = repository.findAll();
        verificarHayGenero(generoList);
        generoList.forEach(genero -> devolverDirectorioImagen(genero.getImagen(), directorio));
        return mapper.aListGeneroDto(generoList);
    }

    public GeneroDto getById(String idGenero) {
        Genero genero = buscarGeneroConId(idGenero);
        genero.setImagen(devolverDirectorioImagen(genero.getImagen(), directorio).toString());
        return mapper.aGeneroDto(genero);
    }

    @Transactional
    public GeneroDto save(String nombre, MultipartFile imagen) {
        validarParametro(nombre, imagen);
        validarNombreGeneroDto(nombre);
        Genero genero = new Genero();
        genero.setNombre(nombre);
        genero.setImagen(guardarImagen(imagen, directorio));
        return mapper.aGeneroDto(repository.save(genero));
    }

    @Transactional
    public GeneroDto update(String idGenero, String nuevoGenero, MultipartFile nuevaImagen) {
        Genero genero = buscarGeneroConId(idGenero);
        validarParametro(nuevoGenero, nuevaImagen);
        validarNombreGeneroDto(nuevoGenero);
        return mapper.aGeneroDto(repository.save(mapper.aGenero(verificarDatoModificar(nuevoGenero, nuevaImagen, genero))));
    }

    @Transactional
    public GeneroDto delete(GeneroDto dto) {
        validarGeneroDto(dto);
        Genero genero = buscarGeneroConId(dto.getIdGenero());
        validarGeneroDtoEliminar(dto, genero);
        repository.delete(mapper.aGenero(dto));
        return mapper.aGeneroDto(genero);
    }

    @Transactional
    public GeneroDto deleteById(String idGenero) {
        Genero genero = buscarGeneroConId(idGenero);
        repository.deleteById(genero.getIdGenero());
        borrarImagen(genero.getImagen(), directorio);
        return mapper.aGeneroDto(genero);
    }

    private Genero buscarGeneroConId(String idGenero) {
        validarId(idGenero, POR_FAVOR_INGRESE + EL_ID + DE_EL + GENERO + VALIDO);
        return repository.findById(convertirEntero(idGenero)).
                orElseThrow(() -> new Excepcion(MENSAJE_CODIGO_ERROR, ERROR, EL + GENERO + NO_DISPONIBLE, HttpStatus.BAD_REQUEST));
    }

    public GeneroDto verificarDatoModificar(String nuevoGenero, MultipartFile nuevaImagen, Genero genero){
        if (nuevoGenero != null)
            genero.setNombre(nuevoGenero);
        if (nuevaImagen != null && genero.getImagen() != null) {
            borrarImagenActualizar(genero.getImagen(), Objects.requireNonNull(nuevaImagen.getOriginalFilename()), directorio);
            genero.setImagen(guardarImagen(nuevaImagen, directorio));
        }
        return mapper.aGeneroDto(genero);
    }
}
