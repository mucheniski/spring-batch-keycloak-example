package com.example.springbatchkeycloakexample.api.repository;

import com.example.springbatchkeycloakexample.domain.UsuarioOrigem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioOrigemRepository extends JpaRepository<UsuarioOrigem, Integer> {
}
