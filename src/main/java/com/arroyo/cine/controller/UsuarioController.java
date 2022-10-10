package com.arroyo.cine.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class UsuarioController {

    @GetMapping("/login")
    public ResponseEntity<String> login(){
        return new ResponseEntity<>("Usuario registrado", HttpStatus.ACCEPTED);
    }

    @PostMapping("/register")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<String> registrar(){
        return new ResponseEntity<>("Usuario", HttpStatus.ACCEPTED);
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<String> getUser(){
        return new ResponseEntity<>("Usuario", HttpStatus.ACCEPTED);
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> getAdmin(){
        return new ResponseEntity<>("Usuario admin", HttpStatus.ACCEPTED);
    }
}
