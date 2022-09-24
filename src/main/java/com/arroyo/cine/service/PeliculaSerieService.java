package com.arroyo.cine.service;

import com.arroyo.cine.dto.pelicula_serie.PeliculaSerieDto;
import com.arroyo.cine.dto.pelicula_serie.PeliculaSeriePersonalizadoPsDto;
import com.arroyo.cine.dto.personaje.PersonajeDto;
import com.arroyo.cine.entity.PeliculaSerie;
import com.arroyo.cine.mapper.pelicula_serie.PeliculaSerieMapper;
import com.arroyo.cine.mapper.pelicula_serie.PeliculaSeriePersonalizadoMapper;
import com.arroyo.cine.repository.PeliculaSerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

import static com.arroyo.cine.util.InformacionEstatica.*;

@Service
public class PeliculaSerieService {
    @Autowired
    private PeliculaSerieRepository repository;
    @Autowired
    private PeliculaSerieMapper mapper;
    @Autowired
    private PeliculaSeriePersonalizadoMapper mapperP;

    public List<PeliculaSeriePersonalizadoPsDto> getAll( String name, Integer genre, String order) {
        return mapperP.aListPeliculaSeriePersolizadaDto(filtro(repository.findAll(), name, genre, order));
    }

    public List<PeliculaSerieDto> getAllPersonalizado( String name, Integer genre, String order) {
        return mapper.aListPeliculaSerieDto(filtro(repository.findAll(), name, genre, order));
    }

    public PeliculaSerieDto getById(Integer id) {
        return mapper.aPeliculaSerieDto(repository.findById(id).orElse(new PeliculaSerie()));
    }

    public PeliculaSerieDto save(PeliculaSerieDto peliculaSerie) {
        String fechaCreacion = peliculaSerie.getFechaCreacion();
        if (!validarDatosGuardarPeliculaSerieConPersonajes(peliculaSerie))
            return new PeliculaSerieDto();
        if (validarFecha(fechaCreacion) == 1)
            peliculaSerie.setFechaCreacion(fechaCreacion.concat("T00:00:00"));
        return mapper.aPeliculaSerieDto(repository.save(mapper.aPeliculaSerie(peliculaSerie)));
    }

    public void savePersonalizado(Integer idPeli, Integer idPersonaje) {
        PeliculaSerie peliculaSerie = repository.findById(idPeli).orElse(null);
        PeliculaSerie buscarPersonaje = repository.findByIdPersonaje(idPersonaje);
        if (peliculaSerie != null && buscarPersonaje != null) {
            peliculaSerie.setIdPersonaje(idPersonaje);
            repository.insertByIdPeliculaSerieAndIdPersonaje(
                    peliculaSerie.getTitulo(), peliculaSerie.getImagen(), peliculaSerie.getFechaCreacion(),
                    peliculaSerie.getCalifiacion(), peliculaSerie.getIdPersonaje(), peliculaSerie.getIdGenero()
            );
        }
    }

    public PeliculaSerieDto update(Integer id, PeliculaSerieDto peliculaSerieDto) {
        PeliculaSerie peliculaSerie = repository.findById(id).orElse(null);
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

    public void deletePersonalizado(Integer idPeli, Integer idPersonaje) {
        repository.deleteByIdPeliculaSerieAndIdPersonaje(idPeli, idPersonaje);
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

    private boolean validarDatosPeliculaSerie(PeliculaSerieDto dto) {
        return dto.getTitulo() != null && (!dto.getTitulo().isBlank()) && dto.getFechaCreacion() != null &&
                (validarFecha(dto.getFechaCreacion()) == 1 || validarFecha(dto.getFechaCreacion()) == 2) &&
                dto.getIdPersonaje() != null && dto.getIdPersonaje() > 0 && dto.getIdGenero() != null && dto.getIdGenero() > 0;
    }

    private boolean validarDatosPersonajes(List<PersonajeDto> personajesRecivodos) {
        if (personajesRecivodos == null)
            return false;
        List<PersonajeDto> personajes = personajesRecivodos;
        int index = personajesRecivodos.size();
        int personajesValido = 0;

        for (var personaje : personajes) {
            if (personaje.getEdad() != null && personaje.getEdad() > 0 && personaje.getNombre() != null &&
                    (!personaje.getNombre().isBlank()) && personaje.getPeso() != null && personaje.getPeso() > 0)
                personajesValido++;
        }
        return personajesValido == index;
    }

    private boolean validarDatosGuardarPeliculaSerieConPersonajes(PeliculaSerieDto dto) {
        if (dto == null || dto.getPersonajes() == null)
            return false;
        return validarDatosPeliculaSerie(dto) && validarDatosPersonajes(dto.getPersonajes());
    }

    private byte validarFecha(String fecha) {
        if (fecha.matches(FECHA_CORTA))
            return 1;
        if (fecha.matches(FECHA_LARGA))
            return 2;
        return 0;
    }

    private List<PeliculaSerie> filtro(List<PeliculaSerie> peliculaSeries, String name, Integer genre, String order) {
        if (name != null && !name.isBlank() && genre != null && genre > 0) {
            return peliculaSeries.stream().filter(peliculaSerie -> peliculaSerie.getTitulo().equals(name) && peliculaSerie.getIdGenero().equals(genre)).map(setearNull).collect(Collectors.toList());
        } else if (name != null && !name.isBlank())
            return peliculaSeries.stream().filter(peliculaSerie -> peliculaSerie.getTitulo().equals(name)).map(setearNull).collect(Collectors.toList());
        else if (genre != null && genre > 0)
            return peliculaSeries.stream().filter(peliculaSerie -> peliculaSerie.getIdGenero().equals(genre)).map(setearNull).collect(Collectors.toList());
        else if (order != null && !order.isBlank())
            return ordenarLista(peliculaSeries, order).stream().map(setearNull).collect(Collectors.toList());
        else if (name == null && genre == null && order == null)
            return peliculaSeries;
        else
            return new ArrayList<>();
    }

    private List<PeliculaSerie> ordenarLista(List<PeliculaSerie> peliculaSeries, String orden) {
        if (orden.equalsIgnoreCase(ASC)) {
            peliculaSeries.sort(Comparator.comparing(PeliculaSerie::getFechaCreacion));
        } else if (orden.equalsIgnoreCase(DESC)) {
            peliculaSeries.sort(Comparator.comparing(PeliculaSerie::getFechaCreacion).reversed());
        }
        return peliculaSeries;
    }

    private final UnaryOperator<PeliculaSerie> setearNull = peliculaSerie -> {
        peliculaSerie.setIdPeliculaSerie(null);
        peliculaSerie.setPersonajes(null);
        peliculaSerie.setCalifiacion(null);
        peliculaSerie.setIdGenero(null);
        peliculaSerie.setIdPersonaje(null);
        peliculaSerie.setIdPersonaje(null);
        return peliculaSerie;
    };

}
