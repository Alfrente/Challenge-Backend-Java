package com.arroyo.cine.service;

import com.arroyo.cine.dto.GeneroDto;
import com.arroyo.cine.entity.Genero;
import com.arroyo.cine.exception.custom.genero.GeneroExcepcion;
import com.arroyo.cine.mapper.genero.GeneroMapper;
import com.arroyo.cine.repository.GeneroRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.arroyo.cine.service.validacion.ValidacionGenerica.convertirEntero;
import static com.arroyo.cine.service.validacion.ValidacionGenerica.validarId;
import static com.arroyo.cine.service.validacion.genero.ParametroEntradaGenero.*;
import static com.arroyo.cine.util.statico.RespuestaExcepcion.*;

@Service
public class GeneroService {
    private final GeneroRepository repository;
    private final GeneroMapper mapper;
    private Genero genero;

    public GeneroService(GeneroRepository repository, GeneroMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<GeneroDto> getAll() {
        return traerTodo();
    }

    public GeneroDto getById(String idGenero) {
        return mapper.aGeneroDto(buscarGeneroConId(idGenero));
    }

    @Transactional
    public GeneroDto save(GeneroDto dto) {
        velidarGeneroDto(dto);
        validarNombreGeneroDto(dto.getNombreGenero());
        validarDireccionImagenGenero(dto.getImagenGenero());
        return mapper.aGeneroDto(repository.save(mapper.aGenero(dto)));
    }

    @Transactional
    public GeneroDto update(String idGenero, String nuevoGenero) {
        this.genero = null;
        this.genero = buscarGeneroConId(idGenero);
        validarNombreGeneroDto(nuevoGenero);
        this.genero.setNombre(nuevoGenero);
        return mapper.aGeneroDto(repository.save(genero));
    }

    @Transactional
    public GeneroDto delete(GeneroDto dto) {
        this.genero = null;
        velidarGeneroDto(dto);
        this.genero = buscarGeneroConId(dto.getIdeGenero());
        validarGeneroDtoEliminar(dto, genero);
        repository.delete(mapper.aGenero(dto));
        return mapper.aGeneroDto(this.genero);
    }

    @Transactional
    public GeneroDto deleteById(String idGenero) {
        this.genero = null;
        this.genero = buscarGeneroConId(idGenero);
        repository.deleteById(this.genero.getIdGenero());
        return mapper.aGeneroDto(this.genero);
    }

    private List<GeneroDto> traerTodo() {
        List<Genero> generoList = repository.findAll();
        if (generoList.isEmpty())
            throw new GeneroExcepcion(CODIGO_ERROR, ERROR, NO_HAY + GENERO + DISPONIBLE, HttpStatus.BAD_REQUEST);
        return mapper.aListGeneroDto(generoList);
    }

    private Genero buscarGeneroConId(String idGenero) {
        validarId(idGenero, POR_FAVOR_INGRESE + EL_ID + DE_EL + GENERO + VALIDO);
        return repository.findById(convertirEntero(idGenero)).
                orElseThrow(() -> new GeneroExcepcion(CODIGO_ERROR, ERROR, EL + GENERO + NO_DISPONIBLE, HttpStatus.BAD_REQUEST));
    }
}
