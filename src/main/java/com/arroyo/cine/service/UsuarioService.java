package com.arroyo.cine.service;

import com.arroyo.cine.model.dto.UsuarioDto;
import com.arroyo.cine.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioService {

    private final UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public boolean save(UsuarioDto dto){
        return true;
    }

}
