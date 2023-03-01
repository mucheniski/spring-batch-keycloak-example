package com.example.springbatchkeycloakexample.batch.writer;

import com.example.springbatchkeycloakexample.domain.UsuarioOrigem;
import com.example.springbatchkeycloakexample.dto.TokenCapitalGlobalDTO;
import com.example.springbatchkeycloakexample.dto.UserRealmDTO;
import com.example.springbatchkeycloakexample.properties.RealmCapitalGlobalProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Slf4j
public class UsuarioOrigemWriterRest implements ItemWriter<UsuarioOrigem> {

    private RestTemplate restTemplate;
    private String endpointUrl;

    @Autowired
    private RealmCapitalGlobalProperties realmCapitalGlobalProperties;

    public UsuarioOrigemWriterRest(RestTemplate restTemplate, String endpointUrl) {
        this.restTemplate = restTemplate;
        this.endpointUrl = endpointUrl;
    }

    @Override
    public void write(List<? extends UsuarioOrigem> usuarios) throws Exception {
        for (UsuarioOrigem usuarioOrigem : usuarios) {
            enviaPostParaRealm(usuarioOrigem);
        }
    }

    private void enviaPostParaRealm(UsuarioOrigem usuarioOrigem) throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException {

        TokenCapitalGlobalDTO tokenCapitalGlobalDTO = obterToken();
        log.info("ACCESS TOKEN - {}", tokenCapitalGlobalDTO.getAccessToken());

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(tokenCapitalGlobalDTO.getAccessToken());

        UserRealmDTO userRealmDTO = populaUsuario(usuarioOrigem);

        ResponseEntity<UserRealmDTO> response = restTemplate.exchange(
                realmCapitalGlobalProperties.getEndpoints().get("insert-user"),
                HttpMethod.POST,
                new HttpEntity<>(userRealmDTO, headers),
                UserRealmDTO.class
        );


    }

    private TokenCapitalGlobalDTO obterToken() throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException {

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("grant_type", realmCapitalGlobalProperties.getGrantType());
        map.add("client_id", realmCapitalGlobalProperties.getClientId());
        map.add("client_secret", realmCapitalGlobalProperties.getClientSecret());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<MultiValueMap<String, String>> requestBody = new HttpEntity<>(map, headers);

        return restTemplate.postForObject(realmCapitalGlobalProperties.getEndpoints().get("token"), requestBody, TokenCapitalGlobalDTO.class);

    }

    private static UserRealmDTO populaUsuario(UsuarioOrigem usuarioOrigem) {

        // TODO: Passas os dados do usuário para o UserRealm

        UserRealmDTO userRealmDTO = new UserRealmDTO();
        userRealmDTO.setUsername("70468328033");
        userRealmDTO.setEmail("70468328033@gmail.com");
        userRealmDTO.setEnabled(true);
        userRealmDTO.setEmailVerified(true);
        userRealmDTO.getRequiredActions().add("UPDATE_PASSWORD");
        return userRealmDTO;
    }

}
