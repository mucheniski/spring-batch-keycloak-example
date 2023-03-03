package com.example.springbatchkeycloakexample;

import com.example.springbatchkeycloakexample.api.service.UsuarioOrigemService;
import com.example.springbatchkeycloakexample.domain.UsuarioOrigem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBatchKeycloakExampleApplication implements CommandLineRunner {

	@Autowired
	private UsuarioOrigemService usuarioOrigemService;

	public static void main(String[] args) {
		SpringApplication.run(SpringBatchKeycloakExampleApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		for (int i = 0; i <= 1000000; i++) {
			UsuarioOrigem usuarioOrigem = new UsuarioOrigem(i, "usuario"+i, "CPF"+i, "email"+i );
			usuarioOrigemService.insert(usuarioOrigem);
		}

//		UsuarioOrigem usuarioOrigem1 = new UsuarioOrigem(null,"Usuario1", "CPF", "Email");
//		UsuarioOrigem usuarioOrigem2 = new UsuarioOrigem(null,"Usuario2", "CPF", "Email");
//		UsuarioOrigem usuarioOrigem3 = new UsuarioOrigem(null,"Usuario3", "CPF", "Email");
//
//		usuarioOrigemService.insert(usuarioOrigem1);
//		usuarioOrigemService.insert(usuarioOrigem2);
//		usuarioOrigemService.insert(usuarioOrigem3);

	}
}
