package com.arroyo.cine.service;

import com.arroyo.cine.model.SecurityUser;
import com.arroyo.cine.repository.UsuarioRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JpaUserDetailsService implements UserDetailsService {

    private final UsuarioRepository repository;

    public JpaUserDetailsService(UsuarioRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository
                .findByNombreUsuario(username)
                .map(SecurityUser::new)
                .orElseThrow(() -> new UsernameNotFoundException("El nombre usuario no funciona: " + username));
    }
}
