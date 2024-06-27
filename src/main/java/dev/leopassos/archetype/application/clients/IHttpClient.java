package dev.leopassos.archetype.application.clients;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.Map;

public interface IHttpClient {
    HttpResponse<String> get(String uri) throws IOException, InterruptedException;

    HttpResponse<String> get(String uri, Map<String, String> headers) throws IOException, InterruptedException;

    HttpResponse<String> post(String uri, Object object) throws IOException, InterruptedException;

    HttpResponse<String> post(String uri, Object body, Map<String, String> headers) throws IOException, InterruptedException;
}
