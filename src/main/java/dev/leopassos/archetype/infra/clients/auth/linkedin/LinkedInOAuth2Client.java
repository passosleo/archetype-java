package dev.leopassos.archetype.infra.clients.auth.linkedin;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.leopassos.archetype.application.clients.IHttpClient;
import dev.leopassos.archetype.application.clients.auth.IOAuth2Client;
import dev.leopassos.archetype.application.dtos.auth.OAuth2CredentialsDTO;
import dev.leopassos.archetype.helpers.Helpers;
import dev.leopassos.archetype.infra.dtos.auth.linkedin.LinkedInAccessTokenDTO;
import dev.leopassos.archetype.infra.dtos.auth.linkedin.LinkedInUserEmailDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.net.http.HttpResponse;
import java.util.Map;

@Slf4j
@Qualifier("linkedin")
@Component
@RequiredArgsConstructor
public class LinkedInOAuth2Client implements IOAuth2Client {

    private final IHttpClient httpClient;
    private final ObjectMapper objectMapper;

    @Value("${spring.security.oauth2.client.provider.linkedin.token-uri}")
    private String tokenUri;
    @Value("${spring.security.oauth2.client.provider.linkedin.user-info-uri}")
    private String userInfoUri;


    @Override
    public String getAccessToken(OAuth2CredentialsDTO credentials) {
        try {
            Map<String, String> body = Map.of(
                    "client_id", credentials.getClientId(),
                    "client_secret", credentials.getClientSecret(),
                    "code", credentials.getCode(),
                    "redirect_uri", credentials.getRedirectUri(),
                    "grant_type", credentials.getGrantType()
            );
            var headers = Map.of("Content-Type", "application/x-www-form-urlencoded");
            HttpResponse<String> response = httpClient.post(tokenUri, Helpers.encodeFormData(body), headers);
            if (response.statusCode() == HttpStatus.OK.value()) {
                LinkedInAccessTokenDTO data = objectMapper.readValue(response.body(), new TypeReference<>() {
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
            var authorizationHeader = Map.of("Authorization", String.format("Bearer %s", accessToken));
            HttpResponse<String> response = httpClient.get(userInfoUri, authorizationHeader);
            if (response.statusCode() == HttpStatus.OK.value()) {
                LinkedInUserEmailDTO data = objectMapper.readValue(response.body(), new TypeReference<>() {
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