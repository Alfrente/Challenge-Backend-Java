package com.arroyo.cine.service;

import com.arroyo.cine.dto.genero.GeneroDto;
import com.arroyo.cine.entity.Genero;
import com.arroyo.cine.mapper.genero.GeneroMapper;
import com.arroyo.cine.repository.GeneroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GeneroService {

    @Autowired
    private GeneroRepository repository;

    @Autowired
    private GeneroMapper mapper;

    @Transactional
    public GeneroDto save(GeneroDto generoDto) {
        if (generoDto.getIdeGenero() != null || generoDto.getNombreGenero() == null)
            return new GeneroDto();
        return mapper.aGeneroDto(repository.save(mapper.aGenero(generoDto)));
    }

    @Transactional
    public GeneroDto delete(GeneroDto generoDto) {
        if (generoDto.getIdeGenero() == null)
            return new GeneroDto();
        Genero genero = repository.findById(generoDto.getIdeGenero()).orElse(new Genero());
        if (genero.getIdGenero() == null || !validarTodosLosDatos(generoDto))
            return new GeneroDto();
        repository.delete(mapper.aGenero(generoDto));
        return mapper.aGeneroDto(genero);
    }

    @Transactional
    public GeneroDto deleteById(Integer idGenero) {
        Genero genero = repository.findById(idGenero).orElse(new Genero());
        if (genero.getIdGenero() == null)
            return new GeneroDto();
        repository.deleteById(genero.getIdGenero());
        return mapper.aGeneroDto(genero);
    }

    public GeneroDto getById(Integer idGenero) {
        return mapper.aGeneroDto(repository.findById(idGenero).orElse(null));
    }

    public List<GeneroDto> getAll() {
        return mapper.aListGeneroDto(repository.findAll());
    }

    @Transactional
    public GeneroDto update(Integer idGenero, String nuevoGenero) {
        Genero genero = repository.findById(idGenero).orElse(new Genero());
        genero.setNombre(nuevoGenero);
        if (genero.getIdGenero() == null)
            return new GeneroDto();
        return mapper.aGeneroDto(repository.save(genero));
    }

    private boolean validarTodosLosDatos(GeneroDto genero){
        return genero.getIdeGenero() != null && genero.getIdeGenero() > 0 && genero.getNombreGenero() != null &&
                (!genero.getNombreGenero().isBlank()) && genero.getImagenGenero() != null && (!genero.getImagenGenero().isBlank());
    }
}
