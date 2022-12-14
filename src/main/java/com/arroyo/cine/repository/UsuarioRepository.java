package com.arroyo.cine.repository;

import com.arroyo.cine.model.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Optional<Usuario> findTopByNombreUsuario(String username);

    Usuario findByCorreo(String correo);
}
