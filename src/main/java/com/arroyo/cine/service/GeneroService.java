package com.arroyo.cine.service;

import com.arroyo.cine.dto.genero.GeneroDto;
import com.arroyo.cine.entity.Genero;
import com.arroyo.cine.mapper.genero.GeneroMapper;
import com.arroyo.cine.repository.GeneroRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.List;

@Service
public class GeneroService {

    private final GeneroRepository repository;

    private final GeneroMapper mapper;

    public GeneroService(GeneroRepository repository, GeneroMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Transactional
    public GeneroDto save(@NotNull GeneroDto generoDto) {
        if (validarIdGeneroNombre(generoDto))
            return generoDto;
        return mapper.aGeneroDto(repository.save(mapper.aGenero(generoDto)));
    }

    @Transactional
    public GeneroDto delete(@NotNull GeneroDto generoDto) {
        if (generoDto.getIdeGenero() == null)
            return new GeneroDto();
        Genero genero = repository.findById(generoDto.getIdeGenero()).orElse(new Genero());
        if (validarIdGenero(genero.getIdGenero()) || !validarDatosObligatorios(generoDto))
            return new GeneroDto();
        repository.delete(mapper.aGenero(generoDto));
        return mapper.aGeneroDto(genero);
    }

    @Transactional
    public GeneroDto deleteById(Integer idGenero) {
        Genero genero = repository.findById(idGenero).orElse(new Genero());
        if (validarIdGenero(genero.getIdGenero()))
            return new GeneroDto();
        repository.deleteById(genero.getIdGenero());
        return mapper.aGeneroDto(genero);
    }

    public GeneroDto getById(@NotNull Integer idGenero) {
        return mapper.aGeneroDto(repository.findById(idGenero).orElse(null));
    }

    public List<GeneroDto> getAll() {
        return mapper.aListGeneroDto(repository.findAll());
    }

    @Transactional
    public GeneroDto update(@NotNull Integer idGenero, @NotNull String nuevoGenero) {
        Genero genero = repository.findById(idGenero).orElse(new Genero());
        genero.setNombre(nuevoGenero);
        if (genero.getIdGenero() == null)
            return new GeneroDto();
        return mapper.aGeneroDto(repository.save(genero));
    }

    private boolean validarDatosObligatorios(GeneroDto genero) {
        return genero.getIdeGenero() != null && genero.getIdeGenero() > 0 && genero.getNombreGenero() != null &&
                (!genero.getNombreGenero().isBlank());
    }

    private boolean validarIdGeneroNombre(GeneroDto generoDto) {
        return generoDto.getIdeGenero() != null && generoDto.getNombreGenero() == null;
    }

    private boolean validarIdGenero(Integer idGenero) {
        return idGenero == null;
    }

    private boolean validarEntidad(Genero genero) {
        return genero == null;
    }
}
