package com.arroyo.cine.service;

import com.arroyo.cine.dto.PeliculaSerieDto;
import com.arroyo.cine.entity.PeliculaSerie;
import com.arroyo.cine.mapper.PeliculaSerieMapper;
import com.arroyo.cine.repository.PeliculaSerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static com.arroyo.cine.util.ExpecionRegular.FECHA_CORTA;
import static com.arroyo.cine.util.ExpecionRegular.FECHA_LARGA;

@Service
public class PeliculaSerieService {
    @Autowired
    private PeliculaSerieRepository repository;

    @Autowired
    private PeliculaSerieMapper mapper;

    public List<PeliculaSerieDto> getAll() {
        return mapper.aaPeliculaSerieDtoList(repository.findAll());
    }

    public PeliculaSerieDto getById(Integer id) {
        return mapper.aPeliculaSerieDto(repository.findById(id).orElse(new PeliculaSerie()));
    }

    public PeliculaSerieDto save(PeliculaSerieDto peliculaSerie) {
        String fechaCreacion = peliculaSerie.getFechaCreacion();
        if (!validarDatosGuardar(peliculaSerie))
            return new PeliculaSerieDto();
        if (validarFecha(fechaCreacion) == 1)
            peliculaSerie.setFechaCreacion(fechaCreacion.concat("T00:00:00"));
        return mapper.aPeliculaSerieDto(repository.save(mapper.aPeliculaSerie(peliculaSerie)));
    }

    public PeliculaSerieDto update(Integer id, PeliculaSerieDto peliculaSerieDto) {
        PeliculaSerie peliculaSerie = repository.findById(id).orElse(new PeliculaSerie());
        if (peliculaSerie == null || id <= 0)
            return new PeliculaSerieDto();
        return mapper.aPeliculaSerieDto(repository.save(validarParametros(peliculaSerie, peliculaSerieDto)));
    }

    public PeliculaSerieDto delete(PeliculaSerieDto peliculaSerieDto) {
        PeliculaSerie peliculaSerie = repository.findById(peliculaSerieDto.getIdPeliculaSerie()).orElse(new PeliculaSerie());
        if (peliculaSerie.getIdPeliculaSerie() == null || !validarTodosLosDatos(peliculaSerieDto))
            return new PeliculaSerieDto();
        repository.delete(mapper.aPeliculaSerie(peliculaSerieDto));
        return mapper.aPeliculaSerieDto(peliculaSerie);
    }

    public PeliculaSerieDto deleteById(Integer id) {
        PeliculaSerie peliculaSerie = repository.findById(id).orElse(new PeliculaSerie());
        if (peliculaSerie.getIdPeliculaSerie() == null)
            return new PeliculaSerieDto();
        repository.deleteById(id);
        return mapper.aPeliculaSerieDto(peliculaSerie);
    }

    private boolean validarTodosLosDatos(PeliculaSerieDto dto) {
        return dto.getIdPeliculaSerie() != null && dto.getIdPeliculaSerie() > 0 && dto.getTitulo() != null && (!dto.getTitulo().isBlank()) && dto.getFechaCreacion() != null &&
                (validarFecha(dto.getFechaCreacion()) == 1 || validarFecha(dto.getFechaCreacion()) == 2) && dto.getIdPersonaje() != null
                && dto.getIdPersonaje() > 0 && dto.getIdGenero() != null && dto.getIdGenero() > 0;
    }

    private PeliculaSerie validarParametros(PeliculaSerie entity, PeliculaSerieDto dto) {
        if (dto.getTitulo() != null && !dto.getTitulo().isBlank()) {
            entity.setTitulo(dto.getTitulo());
        }

        if (dto.getCaratula() != null && !dto.getCaratula().isBlank()) {
            entity.setImagen(dto.getCaratula());
        }

        if (dto.getFechaCreacion() != null && !dto.getFechaCreacion().isBlank()) {
            entity.setFechaCreacion(LocalDateTime.parse(dto.getFechaCreacion()));
        }

        if (dto.getCalifiacion() != null && dto.getCalifiacion() > 0 && dto.getCalifiacion() < 6) {
            entity.setCalifiacion(dto.getCalifiacion());
        }

        if (dto.getIdPersonaje() != null && dto.getIdPersonaje() > 0) {
            entity.setIdPersonaje(dto.getIdPersonaje());
        }

        if (dto.getIdGenero() != null && dto.getIdGenero() > 0) {
            entity.setIdGenero(dto.getIdGenero());
        }
        return entity;
    }

    private boolean validarDatosGuardar(PeliculaSerieDto dto) {
        return dto.getTitulo() != null && (!dto.getTitulo().isBlank()) && dto.getFechaCreacion() != null &&
                (validarFecha(dto.getFechaCreacion()) == 1 || validarFecha(dto.getFechaCreacion()) == 2) && dto.getIdPersonaje() != null && dto.getIdPersonaje() > 0 && dto.getIdGenero() != null && dto.getIdGenero() > 0;
    }

    private byte validarFecha(String fecha) {
        if (fecha.matches(FECHA_CORTA))
            return 1;
        if (fecha.matches(FECHA_LARGA))
            return 2;
        return 0;
    }
}
