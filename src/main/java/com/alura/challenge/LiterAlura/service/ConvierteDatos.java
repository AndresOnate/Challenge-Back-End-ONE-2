package com.alura.challenge.LiterAlura.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConvierteDatos{
    private ObjectMapper objectMapper = new ObjectMapper();

    public <T> T obtenerDatos(String json, Class<T> clase) {
        try {
            JsonNode root = objectMapper.readTree(json);
            JsonNode firstResult = root.path("results").get(0);
            return objectMapper.readValue(firstResult.toString(),clase);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
