package com.example.springbatchkeycloakexample.batch.writer;

import com.example.springbatchkeycloakexample.domain.UsuarioOrigem;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class UsuarioOrigemWriterRestConfig {

    @Value("${spring.batch.url-keycloak-capital-global}")
    private String endpointUrl;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public ItemWriter<UsuarioOrigem> usuarioOrigemWriterRest() {
        return new UsuarioOrigemWriterRest(restTemplate(), endpointUrl);
    }

}
