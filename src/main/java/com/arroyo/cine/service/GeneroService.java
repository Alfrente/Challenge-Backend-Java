package com.arroyo.cine.service;

import com.arroyo.cine.dto.genero.GeneroDto;
import com.arroyo.cine.entity.Genero;
import com.arroyo.cine.exception.custom.genero.GeneroDatosExcepciones;
import com.arroyo.cine.exception.custom.genero.GeneroExcepcionGenerico;
import com.arroyo.cine.mapper.genero.GeneroMapper;
import com.arroyo.cine.repository.GeneroRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

import static com.arroyo.cine.util.InformacionEstatica.REGEX_NOMBRE_GENERO;

@Service
public class GeneroService {

    private final GeneroRepository repository;

    private final GeneroMapper mapper;

    private Genero genero;

    public GeneroService(GeneroRepository repository, GeneroMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Transactional
    public GeneroDto save(@Valid @NotNull GeneroDto dto) {
        validarEntradaNombreGenero(dto.getNombreGenero());
        return mapper.aGeneroDto(repository.save(mapper.aGenero(dto)));
    }

    @Transactional
    public GeneroDto delete(@NotNull GeneroDto dto) {
        this.genero = null;
        validarIdGenero(dto.getIdeGenero());
        this.genero = buscarGeneroIdGenero(dto.getIdeGenero());
        validarGeneroDtoEliminar(dto, genero);    /** *********************************************************/
        repository.delete(mapper.aGenero(dto));
        return mapper.aGeneroDto(this.genero);
    }

    @Transactional
    public GeneroDto deleteById(@NotNull Integer idGenero) {
        this.genero = null;
        this.genero = buscarGeneroIdGenero(idGenero);
        repository.deleteById(this.genero.getIdGenero());
        return mapper.aGeneroDto(this.genero);
    }

    public GeneroDto getById(@NotNull Integer idGenero) {
        return mapper.aGeneroDto(buscarGeneroIdGenero(idGenero));
    }

    public List<GeneroDto> getAll() {
        return mapper.aListGeneroDto(repository.findAll());
    }

    @Transactional
    public GeneroDto update(@NotNull Integer idGenero, @NotNull String nuevoGenero) {
        this.genero = null;
        this.genero = buscarGeneroIdGenero(idGenero);
        validarEntradaNombreGenero(nuevoGenero);
        this.genero.setNombre(nuevoGenero);
        return mapper.aGeneroDto(repository.save(genero));
    }

    private Genero buscarGeneroIdGenero(Integer idGenero) {
        return repository.findById(idGenero).
                orElseThrow(() -> new GeneroExcepcionGenerico("El id del genero no esta disponible", HttpStatus.BAD_REQUEST));
    }

    private void validarIdGenero(Integer idGenero) {
        if (idGenero == null || idGenero <= 0)
            throw new GeneroExcepcionGenerico("Ingrese el id del genero", HttpStatus.BAD_REQUEST);
    }

    private void validarGeneroDtoEliminar(GeneroDto dto, Genero genero) {
        int error = 0;
        List<String> excepciones = new ArrayList<>();
        if (dto == null) {
            throw new GeneroExcepcionGenerico("Por favor ingrese los datos requeridos", HttpStatus.BAD_REQUEST);
        }
        if (dto.getNombreGenero() == null || dto.getNombreGenero().isBlank() || (!dto.getNombreGenero().equals(genero.getNombre()))) {
            error++;
            excepciones.add("El nombre del genero Ingresado es incorrecto");
        }

        if (dto.getImagenGenero() == null || dto.getImagenGenero().isBlank() || (!dto.getImagenGenero().equals(genero.getImagen()))) {
            error++;
            excepciones.add("El nombre de La imagen ingresado es incorrecto");
        }

        if (error == 1 || error == 2)
            throw new GeneroDatosExcepciones(excepciones, HttpStatus.BAD_REQUEST);
    }

    private void validarEntradaNombreGenero(String nombre) {
        if (nombre != null && !nombre.matches(REGEX_NOMBRE_GENERO)) {
            throw new GeneroExcepcionGenerico("El nombre del genero es incorrecto", HttpStatus.BAD_REQUEST);
        }
    }
}
