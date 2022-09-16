package com.arroyo.cine.service;

import com.arroyo.cine.dto.GeneroDto;
import com.arroyo.cine.entity.Genero;
import com.arroyo.cine.mapper.GeneroMapper;
import com.arroyo.cine.repository.GeneroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GeneroService {

    @Autowired
    private GeneroRepository generoRepository;

    @Autowired
    private GeneroMapper generoMapper;

    public GeneroDto save(GeneroDto generoDto) {
        Genero genero = generoMapper.aGenero(generoDto);
        return generoMapper.aGeneroDto(generoRepository.save(genero));
    }

    public GeneroDto delete(GeneroDto generoDto) {
        Genero genero = generoRepository.findById(generoDto.getId_genero()).orElse(new Genero());
        if (genero.getId_genero() != null && genero.getId_genero() > 0)
            generoRepository.delete(generoMapper.aGenero(generoDto));
        return generoMapper.aGeneroDto(genero);
    }

    public GeneroDto deleteById(Integer idGenero) {
        Genero genero = generoRepository.findById(idGenero).orElse(new Genero());
        generoRepository.deleteById(genero.getId_genero());
        return generoMapper.aGeneroDto(genero);
    }

    public GeneroDto getById(Integer idGenero) {
        return generoMapper.aGeneroDto(generoRepository.findById(idGenero).orElse(new Genero()));
    }

    public List<GeneroDto> getAll() {
        return generoMapper.aListGeneroDto(generoRepository.findAll());
    }

    public GeneroDto update(Integer idGenero, String nuevoGenero) {
        Genero genero = generoRepository.findById(idGenero).orElse(new Genero());
        genero.setNombre(nuevoGenero);
        if (genero.getId_genero() == null)
            return generoMapper.aGeneroDto(genero);
        return generoMapper.aGeneroDto(generoRepository.save(genero));
    }
}
