package com.arroyo.cine.service;

import com.arroyo.cine.dto.pelicula_serie.PeliculaSerieDto;
import com.arroyo.cine.dto.pelicula_serie.PeliculaSeriePersonalizadoPsDto;
import com.arroyo.cine.dto.personaje.PersonajeDto;
import com.arroyo.cine.entity.PeliculaSerie;
import com.arroyo.cine.mapper.pelicula_serie.PeliculaSerieMapper;
import com.arroyo.cine.mapper.pelicula_serie.PeliculaSeriePersonalizadoMapper;
import com.arroyo.cine.repository.PeliculaSerieRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

import static com.arroyo.cine.service.ValidacionCompartida.convertirByte;
import static com.arroyo.cine.service.ValidacionCompartida.convertirFloat;
import static com.arroyo.cine.util.statico.ExprecionRegular.*;

@Service
public class PeliculaSerieService {

    private final PeliculaSerieRepository repository;

    private final PeliculaSerieMapper mapper;

    private final PeliculaSeriePersonalizadoMapper mapperP;

    public PeliculaSerieService(PeliculaSerieRepository repository, PeliculaSerieMapper mapper, PeliculaSeriePersonalizadoMapper mapperP) {
        this.repository = repository;
        this.mapper = mapper;
        this.mapperP = mapperP;
    }

    public List<PeliculaSerieDto> getAll(String name, Integer genre, String order) {
        return mapper.aListPeliculaSerieDto(filtro(repository.findAll(), name, genre, order));
    }

    public List<PeliculaSeriePersonalizadoPsDto> getAllParameter(String name, Integer genre, String order) {
        return mapperP.aListPeliculaSeriePersolizadaDto(filtro(repository.findAll(), name, genre, order));
    }

    public PeliculaSerieDto getById(Integer id) {
        return mapper.aPeliculaSerieDto(repository.findById(id).orElse(new PeliculaSerie()));
    }

    @Transactional
    public PeliculaSerieDto save(@NotNull PeliculaSerieDto peliculaSerie) {
        if (peliculaSerie.getIdPersonaje() == null)
            return new PeliculaSerieDto();
        PeliculaSerie buscarPersonaje = bucarPersonaje(peliculaSerie.getIdPersonaje());
        if (!validarDatosGuardarPeliculaSerieConPersonajes(peliculaSerie) && !validarDatosPeliculaSerie(peliculaSerie) && buscarPersonaje == null)
            return new PeliculaSerieDto();
        return mapper.aPeliculaSerieDto(repository.save(mapper.aPeliculaSerie(peliculaSerie)));
    }

    @Transactional
    public void savePersonalizado(@NotNull Integer idPeli, @NotNull Integer idPersonaje) {
        PeliculaSerie peliculaSerie = bucarPeliculaSerie(idPeli);
        if (peliculaSerie != null) {
            PeliculaSerieDto peliculaSerieDto = guardar(peliculaSerie);
            peliculaSerieDto.setIdPeliculaSerie(null);
            peliculaSerieDto.setPersonajes(null);
            peliculaSerieDto.setIdPersonaje(idPersonaje);
            save(peliculaSerieDto);
        }
    }

    @Transactional
    public PeliculaSerieDto update(@NotNull Integer id, @NotNull PeliculaSerieDto peliculaSerieDto) {
        PeliculaSerie peliculaSerie = repository.findById(id).orElse(null);
        if (peliculaSerie == null || id <= 0)
            return new PeliculaSerieDto();
        return mapper.aPeliculaSerieDto(repository.save(validarParametros(peliculaSerie, peliculaSerieDto)));
    }

    @Transactional
    public PeliculaSerieDto delete(@NotNull PeliculaSerieDto peliculaSerieDto) {
        if (peliculaSerieDto.getIdPeliculaSerie() == null)
            return new PeliculaSerieDto();
        PeliculaSerie peliculaSerie = repository.findById(peliculaSerieDto.getIdPeliculaSerie()).orElse(new PeliculaSerie());
        if (peliculaSerie.getIdPeliculaSerie() == null || !validarTodosLosDatos(peliculaSerieDto))
            return new PeliculaSerieDto();
        repository.delete(mapper.aPeliculaSerie(personajesNull(peliculaSerieDto)));
        return mapper.aPeliculaSerieDto(peliculaSerie);
    }

    @Transactional
    public void deletePersonalizado(@NotNull Integer idPeli, @NotNull Integer idPersonaje) {
        repository.deleteByIdPeliculaSerieAndIdPersonaje(idPeli, idPersonaje);
    }

    @Transactional
    public PeliculaSerieDto deleteById(@NotNull Integer id) {
        PeliculaSerie peliculaSerie = repository.findById(id).orElse(new PeliculaSerie());
        if (peliculaSerie.getIdPeliculaSerie() == null)
            return new PeliculaSerieDto();
        repository.deleteById(id);
        return mapper.aPeliculaSerieDto(peliculaSerie);
    }

    private boolean validarFecha(String fecha) {
        return fecha.matches(EXPRECION_FECHA);
    }

    private PeliculaSerieDto personajesNull(PeliculaSerieDto peliculaSerieDto) {
        peliculaSerieDto.setPersonajes(null);
        return peliculaSerieDto;
    }

    private PeliculaSerieDto guardar(PeliculaSerie peliculaSerie) {
        return mapper.aPeliculaSerieDto(peliculaSerie);
    }

    private PeliculaSerie bucarPersonaje(Integer idPersonaje) {
        return repository.buscarConIdPersonaje(idPersonaje);
    }

    private PeliculaSerie bucarPeliculaSerie(Integer idPeli) {
        return repository.findById(idPeli).orElse(null);
    }

    private boolean validarDatosGuardarPeliculaSerieConPersonajes(PeliculaSerieDto dto) {
        if (dto == null || dto.getPersonajes() == null)
            return false;
        return validarDatosPeliculaSerie(dto) && validarDatosPersonajes(dto.getPersonajes()) && validarFecha(dto.getFechaCreacion());
    }

    private boolean validarTodosLosDatos(PeliculaSerieDto dto) {
        return dto.getIdPeliculaSerie() != null && dto.getIdPeliculaSerie() > 0 && dto.getTitulo() != null && (!dto.getTitulo().isBlank()) && dto.getFechaCreacion() != null &&
                validarFecha(dto.getFechaCreacion()) && dto.getIdPersonaje() != null
                && dto.getIdPersonaje() > 0 && dto.getIdGenero() != null && dto.getIdGenero() > 0;
    }

    private boolean validarDatosPeliculaSerie(PeliculaSerieDto dto) {
        return dto.getTitulo() != null && (!dto.getTitulo().isBlank()) && dto.getFechaCreacion() != null &&
                validarFecha(dto.getFechaCreacion()) &&
                dto.getIdPersonaje() != null && dto.getIdPersonaje() > 0;
    }

    private List<PeliculaSerie> ordenarLista(List<PeliculaSerie> peliculaSeries, String orden) {
        if (orden.equalsIgnoreCase(ASC))
            peliculaSeries.sort(Comparator.comparing(PeliculaSerie::getFechaCreacion));
        else if (orden.equalsIgnoreCase(DESC))
            peliculaSeries.sort(Comparator.comparing(PeliculaSerie::getFechaCreacion).reversed());
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

    private boolean validarDatosPersonajes(List<PersonajeDto> personajesRecibidos) {
        if (personajesRecibidos == null)
            return false;
        int index = personajesRecibidos.size();
        int personajesValido = 0;

        for (var personaje : personajesRecibidos) {
            if (personaje.getEdad() != null && convertirByte(personaje.getEdad()) > 0 && personaje.getNombre() != null &&
                    (!personaje.getNombre().isBlank()) && personaje.getPeso() != null && convertirFloat(personaje.getPeso()) > 0)
                personajesValido++;
        }
        return personajesValido == index;
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

    private PeliculaSerie validarParametros(PeliculaSerie entity, PeliculaSerieDto dto) {
        if (dto.getTitulo() != null && !dto.getTitulo().isBlank())
            entity.setTitulo(dto.getTitulo());
        if (dto.getCaratula() != null && !dto.getCaratula().isBlank())
            entity.setImagen(dto.getCaratula());
        if (dto.getFechaCreacion() != null && !dto.getFechaCreacion().isBlank())
            entity.setFechaCreacion(LocalDate.parse(dto.getFechaCreacion()));
        if (dto.getCalifiacion() != null && dto.getCalifiacion() > 0 && dto.getCalifiacion() < 6)
            entity.setCalifiacion(dto.getCalifiacion());
        if (dto.getIdPersonaje() != null && dto.getIdPersonaje() > 0)
            entity.setIdPersonaje(dto.getIdPersonaje());
        if (dto.getIdGenero() != null && dto.getIdGenero() > 0)
            entity.setIdGenero(dto.getIdGenero());
        return entity;
    }
}
