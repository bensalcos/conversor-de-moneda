package com.challenge.conversor.service;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.Scanner;

public class ApiService {
    private static final String URL_BASE = "https://v6.exchangerate-api.com/v6/";
    private static final String API_KEY = "aa6109720dae9d6c7a408134";
    public Map<String, Double> obtenerTasas(String codigoDivisa) throws IOException {

        URL url = new URL(URL_BASE + API_KEY + "/latest/" + codigoDivisa);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();

        if (connection.getResponseCode() == 200) {
            Scanner scanner = new Scanner(url.openStream());
            StringBuilder response = new StringBuilder();
            while (scanner.hasNext()) {
                response.append(scanner.nextLine());
            }
            scanner.close();

            // Convertir la respuesta JSON en un JsonObject
            JsonObject jsonResponse = new Gson().fromJson(response.toString(), JsonObject.class);

            // Extraer el objeto "conversion_rates" y convertirlo a un Map
            return new Gson().fromJson(jsonResponse.getAsJsonObject("conversion_rates"), Map.class);
        } else {
            throw new IOException("Error al conectarse a la API: " + connection.getResponseCode());
        }
    }
}
