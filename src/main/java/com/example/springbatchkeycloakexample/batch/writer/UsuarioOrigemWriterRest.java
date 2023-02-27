package com.example.springbatchkeycloakexample.batch.writer;

import com.example.springbatchkeycloakexample.domain.UsuarioOrigem;
import org.springframework.batch.item.ItemWriter;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class UsuarioOrigemWriterRest implements ItemWriter<UsuarioOrigem> {

    private RestTemplate restTemplate;
    private String endpointUrl;

    public UsuarioOrigemWriterRest(RestTemplate restTemplate, String endpointUrl) {
        this.restTemplate = restTemplate;
        this.endpointUrl = endpointUrl;
    }

    @Override
    public void write(List<? extends UsuarioOrigem> usuarios) throws Exception {
        for (UsuarioOrigem usuarioOrigem : usuarios) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<UsuarioOrigem> request = new HttpEntity<>(usuarioOrigem, headers);
            restTemplate.postForObject(endpointUrl, request, UsuarioOrigem.class);
        }
    }
}
