package com.ch4ll3ng3.alura.libreria.servicio;

import com.ch4ll3ng3.alura.libreria.dto.LibroDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class ServivioGundex {



    private static final String API_URL = "https://gutendex.com/books/";

    public List<LibroDTO> searchBooks(String query) throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        String encodedQuery = URLEncoder.encode(query, StandardCharsets.UTF_8);
        HttpRequest request = HttpRequest.newBuilder().uri(new URI(API_URL + "?search=" + encodedQuery))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            String jsonResponse = response.body();
            ObjectMapper objectMapper = new ObjectMapper();
            GutendexResponse gutendexRespuesta = objectMapper.readValue(jsonResponse, GutendexResponse.class);
            return gutendexRespuesta.getResults();
        } else {
            throw new RuntimeException("Error Al querer Buscar Los libros Deseados: " + response.statusCode());
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    private static class GutendexResponse {
        private List<LibroDTO> results;

        public List<LibroDTO> getResults() {
            return results;
        }
    }

}
