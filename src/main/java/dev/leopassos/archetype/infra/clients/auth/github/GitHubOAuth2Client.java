package dev.leopassos.archetype.infra.clients.auth.github;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.leopassos.archetype.application.clients.IHttpClient;
import dev.leopassos.archetype.application.clients.auth.IOAuth2Client;
import dev.leopassos.archetype.application.dtos.auth.OAuth2CredentialsDTO;
import dev.leopassos.archetype.infra.dtos.auth.github.GitHubAccessTokenDTO;
import dev.leopassos.archetype.infra.dtos.auth.github.GitHubUserEmailDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;

@Slf4j
@Qualifier("github")
@Component
@RequiredArgsConstructor
public class GitHubOAuth2Client implements IOAuth2Client {

    private final IHttpClient httpClient;
    private final ObjectMapper objectMapper;

    @Value("${spring.security.oauth2.client.provider.github.token-uri}")
    private String tokenUri;
    @Value("${spring.security.oauth2.client.provider.github.user-info-uri}")
    private String userInfoUri;


    @Override
    public String getAccessToken(OAuth2CredentialsDTO credentials) {
        try {
            var headers = Map.of("Accept", "application/json");
            HttpResponse<String> response = httpClient.post(tokenUri, credentials, headers);
            if (response.statusCode() == HttpStatus.OK.value()) {
                GitHubAccessTokenDTO data = objectMapper.readValue(response.body(), new TypeReference<>() {
                });
                String accessToken = data.getAccessToken();
                if (accessToken != null) return accessToken;
            }
            throw new RuntimeException("Failed to get access token");
        } catch (Exception ex) {
            log.info("Erro ao conectar com GitHub: {}", ex.getMessage());
            throw new RuntimeException(ex.getMessage());
        }
    }

    @Override
    public String getUsername(String accessToken) {
        try {
            var headers = Map.of(
                    "Authorization", String.format("Bearer %s", accessToken),
                    "Accept", "application/json"
            );
            HttpResponse<String> response = httpClient.get(userInfoUri + "/emails", headers);
            if (response.statusCode() == HttpStatus.OK.value()) {
                List<GitHubUserEmailDTO> data = objectMapper.readValue(response.body(), new TypeReference<>() {
                });
                return data.stream()
                        .filter(email -> Boolean.TRUE.equals(email.getPrimary()))
                        .findFirst()
                        .orElseThrow()
                        .getEmail();
            }
            throw new RuntimeException("Failed to get user email, status code: " + response.statusCode());
        } catch (Exception ex) {
            log.info("Erro ao conectar com GitHub: {}", ex.getMessage());
            throw new RuntimeException(ex.getMessage());
        }
    }
}
