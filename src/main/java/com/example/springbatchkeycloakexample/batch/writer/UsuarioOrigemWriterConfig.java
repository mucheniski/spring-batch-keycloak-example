package com.example.springbatchkeycloakexample.batch.writer;

import com.example.springbatchkeycloakexample.domain.UsuarioOrigem;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UsuarioOrigemWriterConfig {

    // Usado apenas para teste se o reader está funcionando
    @Bean
    ItemWriter<UsuarioOrigem> usuarioOrigemWriter() {
        return items -> items.forEach(System.out::println);
    }

}
