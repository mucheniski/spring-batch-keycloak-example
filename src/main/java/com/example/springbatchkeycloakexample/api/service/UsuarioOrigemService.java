package com.example.springbatchkeycloakexample.api.service;

import com.example.springbatchkeycloakexample.api.repository.UsuarioOrigemRepository;
import com.example.springbatchkeycloakexample.domain.UsuarioOrigem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioOrigemService {

    @Autowired
    private UsuarioOrigemRepository usuarioOrigemRepository;

    public List<UsuarioOrigem> findAll() {
        return usuarioOrigemRepository.findAll();
    }

    public UsuarioOrigem insert(UsuarioOrigem usuarioOrigem) {
        return usuarioOrigemRepository.save(usuarioOrigem);
    }

}
