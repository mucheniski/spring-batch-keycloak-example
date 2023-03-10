package com.example.springbatchkeycloakexample.batch.step;

import com.example.springbatchkeycloakexample.domain.UsuarioOrigem;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UsuarioStepConfiguration {

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Step usuarioStep(
            ItemReader<UsuarioOrigem> usuarioOrigemReader,
//            @Qualifier("usuarioOrigemWriter") ItemWriter<UsuarioOrigem> usuarioOrigemWriter
            @Qualifier("usuarioOrigemWriterRest") ItemWriter<UsuarioOrigem> usuarioOrigemWriterRest
    ) {
        return stepBuilderFactory
                .get("usuarioStep")
                .<UsuarioOrigem, UsuarioOrigem>chunk(10)
                .reader(usuarioOrigemReader)
                .writer(usuarioOrigemWriterRest)
                .build();
    }

}
