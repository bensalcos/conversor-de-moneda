package com.challenge.conversor.service;


import com.challenge.conversor.model.Divisa;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class CargarDivisas {

    private static List<Divisa> loadCurrenciesFromJson() throws IOException {
        Gson gson = new Gson();
        Type listType = new TypeToken<List<Divisa>>() {}.getType();
        String filePath = "src/main/resources/divisas.json";
        try (FileReader reader = new FileReader(filePath)) {
            // Leer el archivo JSON y convertirlo en una lista de Currency
            return gson.fromJson(reader, listType);
        }
    }

    public List<Divisa> obtenerDivisas() {
        try {

            List<Divisa> divisas = new ArrayList<>();
            divisas = loadCurrenciesFromJson();
            return divisas;
        } catch (IOException e) {
            System.err.println("Error al leer el archivo JSON: " + e.getMessage());
        }
        return null;
    }
}