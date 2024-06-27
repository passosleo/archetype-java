package dev.leopassos.archetype.infra.clients.auth.facebook;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.leopassos.archetype.application.clients.IHttpClient;
import dev.leopassos.archetype.application.clients.auth.IOAuth2Client;
import dev.leopassos.archetype.application.dtos.auth.OAuth2CredentialsDTO;
import dev.leopassos.archetype.infra.dtos.auth.facebook.FacebookAccessTokenDTO;
import dev.leopassos.archetype.infra.dtos.auth.facebook.FacebookUserEmailDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.net.http.HttpResponse;
import java.util.Map;

@Slf4j
@Component
@Qualifier("facebook")
@RequiredArgsConstructor
public class FacebookIOAuth2Client implements IOAuth2Client {

    private final IHttpClient httpClient;
    private final ObjectMapper objectMapper;

    @Value("${spring.security.oauth2.client.registration.facebook.token-uri}")
    private String tokenUri;
    @Value("${spring.security.oauth2.client.registration.facebook.user-info-uri}")
    private String userInfoUri;

    @Override
    public String getAccessToken(OAuth2CredentialsDTO credentials) {
        try {
            HttpResponse<String> response = httpClient.post(tokenUri, credentials);
            if (response.statusCode() == HttpStatus.OK.value()) {
                FacebookAccessTokenDTO data = objectMapper.readValue(response.body(), new TypeReference<>() {
                });
                String accessToken = data.getAccessToken();
                if (accessToken != null) return accessToken;
            }
            throw new RuntimeException("Failed to get access token");
        } catch (Exception ex) {
            log.info("Erro ao conectar com Facebook: {}", ex.getMessage());
            throw new RuntimeException(ex.getMessage());
        }
    }

    @Override
    public String getUsername(String accessToken) {
        try {
            var authorizationHeader = Map.of("Authorization", "Bearer " + accessToken);
            HttpResponse<String> response = httpClient.get(userInfoUri + "?fields=name,email", authorizationHeader);
            if (response.statusCode() == HttpStatus.OK.value()) {
                FacebookUserEmailDTO data = objectMapper.readValue(response.body(), new TypeReference<>() {
                });
                return data.getEmail();
            }
            throw new RuntimeException("Failed to get user email, status code: " + response.statusCode());
        } catch (Exception ex) {
            log.info("Erro ao conectar com GitHub: {}", ex.getMessage());
            throw new RuntimeException(ex.getMessage());
        }
    }
}
