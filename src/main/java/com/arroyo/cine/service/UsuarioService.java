package com.arroyo.cine.service;

import com.arroyo.cine.exception.Excepcion;
import com.arroyo.cine.model.mapper.usuario.UsuarioMapper;
import com.arroyo.cine.model.dto.UsuarioDto;
import com.arroyo.cine.repository.UsuarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.arroyo.cine.service.validacion.usuario.ParametroEntradaUsuario.*;
import static com.arroyo.cine.util.statico.MensajeError.*;

@Service
public class UsuarioService {

    private final UsuarioRepository repository;
    private final UsuarioMapper mapper;

    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository repository, UsuarioMapper mapper, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.mapper = mapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void save(UsuarioDto dto) {
        validarUsuarioDto(dto);
        validarCorreo(dto.getCorreo());
        validarUsuario(dto.getUsuario());
        validarSiExisteUsuario(dto.getUsuario());
        validarSiExisteCorreo(dto.getCorreo());
        validarRol(dto.getRol());
        dto.setContrasena(passwordEncoder.encode(dto.getContrasena()));
        repository.save(mapper.aUsuario(dto));
    }

    @Transactional
    public void saveRolUser(UsuarioDto dto) {
        dto.setRol("ROLE_USER");
        save(dto);
    }

    @Transactional
    public void saveLineaComando(UsuarioDto dto) {
        if (correoExiste(dto.getCorreo()))
            repository.save(mapper.aUsuario(dto));
    }

    private void validarSiExisteCorreo(String correo) {
        if (!correoExiste(correo))
            throw new Excepcion(MENSAJE_CODIGO_ERROR, ERROR, "El correo " + correo + " no esta disponible", HttpStatus.BAD_REQUEST);
    }

    private boolean correoExiste(String correo) {
        return repository.findByCorreo(correo) == null;
    }

    private void validarSiExisteUsuario(String usuario) {
        if (repository.findTopByNombreUsuario(usuario).isPresent())
            throw new Excepcion(MENSAJE_CODIGO_ERROR, ERROR, "El usuario " + usuario + " no esta disponible", HttpStatus.BAD_REQUEST);
    }
}
