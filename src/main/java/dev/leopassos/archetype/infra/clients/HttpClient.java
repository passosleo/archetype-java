package dev.leopassos.archetype.infra.clients;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.leopassos.archetype.application.clients.IHttpClient;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;

@Service
public class HttpClient implements IHttpClient {

    private final java.net.http.HttpClient client;
    private final ObjectMapper objectMapper;

    private static final String DEFAULT_CONTENT_TYPE = "application/json";

    public HttpClient() {
        this.client = java.net.http.HttpClient.newHttpClient();
        this.objectMapper = new ObjectMapper();
    }

    private void configureHeaders(HttpRequest.Builder builder, Map<String, String> headers) {
        if (!headers.containsKey("Content-Type")) {
            builder.header("Content-Type", DEFAULT_CONTENT_TYPE);
        }
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            builder.header(entry.getKey(), entry.getValue());
        }
    }

    public HttpResponse<String> get(String uri) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .header("Content-Type", DEFAULT_CONTENT_TYPE)
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();

        return client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    public HttpResponse<String> get(String uri, Map<String, String> headers) throws IOException, InterruptedException {
        HttpRequest.Builder requestBuilder = HttpRequest.newBuilder().uri(URI.create(uri));
        configureHeaders(requestBuilder, headers);
        HttpRequest request = requestBuilder.GET().build();
        return client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    public HttpResponse<String> post(String uri, Object body) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .header("Content-Type", DEFAULT_CONTENT_TYPE)
                .method("POST", HttpRequest.BodyPublishers.ofString(objectMapper.writeValueAsString(body)))
                .build();

        return client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    public HttpResponse<String> post(String uri, Object body, Map<String, String> headers) throws IOException, InterruptedException {
        HttpRequest.Builder requestBuilder = HttpRequest.newBuilder().uri(URI.create(uri));
        configureHeaders(requestBuilder, headers);
        HttpRequest request = requestBuilder.POST(HttpRequest.BodyPublishers.ofString((String) body)).build();
        return client.send(request, HttpResponse.BodyHandlers.ofString());
    }
}
