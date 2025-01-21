package com.challenge.conversor.controller;
import com.challenge.conversor.model.Divisa;
import com.challenge.conversor.service.ApiService;
import com.challenge.conversor.service.CargarDivisas;
import com.challenge.conversor.service.ConversorService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class MenuController {

    private static final int ROWS_PER_PAGE = 10; // Filas por página
    private static final int COLUMNS = 3; // Columnas por página
    private static final int ITEMS_PER_PAGE = ROWS_PER_PAGE * COLUMNS; // Total de divisas por página
    private static void esperarTecla(Scanner scanner) {
        System.out.println("\nPresione Enter para volver al menú...");
        scanner.nextLine(); // Consume el salto de línea pendiente
        scanner.nextLine(); // Espera a que el usuario presione Enter
    }
    public void start() {
        Scanner scanner = new Scanner(System.in);
        CargarDivisas cargarDivisas = new CargarDivisas();
        List<Divisa> divisas = new ArrayList<>();
        divisas = cargarDivisas.obtenerDivisas();
        ConversorService conversorService = new ConversorService();


        int currentPage = 0;

        while (true) {
            // Calcula los índices de la página actual
            int start = currentPage * ITEMS_PER_PAGE;
            int end = Math.min(start + ITEMS_PER_PAGE, divisas.size());

            // Muestra las divisas en la página actual
            System.out.println("\n--- Página " + (currentPage + 1) + " de " + (int) Math.ceil((double) divisas.size() / ITEMS_PER_PAGE) + " ---");

            for (int row = 0; row < ROWS_PER_PAGE; row++) {
                StringBuilder rowBuilder = new StringBuilder();
                for (int col = 0; col < COLUMNS; col++) {
                    int index = start + row + (col * ROWS_PER_PAGE);
                    if (index < end) {
                        Divisa divisa = divisas.get(index);
                        rowBuilder.append(String.format("%-3d %-3s %-30s", index + 1, divisa.codigo(), divisa.nombre()));
                    } else {
                        rowBuilder.append(String.format("%-5s %-10s %-20s %-20s", "", "", "", "")); // Espacios vacíos
                    }
                }
                System.out.println(rowBuilder);
            }

            // Opciones del menú
            System.out.println("\nOpciones:");
            if (currentPage > 0) {
                System.out.println("1. Página anterior");
            }
            if (end < divisas.size()) {
                System.out.println("2. Página siguiente");
            }
            System.out.println("3. Seleccionar divisa");
            System.out.println("0. Salir");

            // Captura la opción del usuario
            System.out.print("Elige una opción: ");
            int option = scanner.nextInt();

            // Maneja las opciones
            if (option == 0) {
                System.out.println("Saliendo...");
                break;
            } else if (option == 1 && currentPage > 0) {
                currentPage--;
            } else if (option == 2 && end < divisas.size()) {
                currentPage++;
            } else if (option == 3) {
                System.out.print("Ingresa el número de la divisa: ");
                int currencyIndex = scanner.nextInt() - 1;
                if (currencyIndex >= 0 && currencyIndex < divisas.size()) {
                    Divisa divisaSeleccionada = divisas.get(currencyIndex);
                    System.out.println("Seleccionaste: " + divisaSeleccionada.codigo() + " - " + divisaSeleccionada.nombre() + " - " + divisaSeleccionada.pais());
                    System.out.print("Ingresa la cantidad a convertir: ");
                    double cantidad = scanner.nextDouble();
                    System.out.print("Ingresa el código de la divisa a la que deseas convertir: ");
                    String codigoDivisa = scanner.next().toUpperCase();

                    ConversorService conversor = new ConversorService();
                    ApiService apiService = new ApiService();

                    try {
                        var tasas = apiService.obtenerTasas(divisaSeleccionada.codigo());
                        BigDecimal tasaBigDecimal = new BigDecimal(Double.toString(tasas.get(codigoDivisa)));

                        var divisaConvertida = conversor.convertir(cantidad,tasas.get(codigoDivisa));
                        System.out.println("Divisa origen: " + divisaSeleccionada.codigo() + " - " + divisaSeleccionada.nombre());
                        System.out.println("Divisa destino: " + codigoDivisa + " --- Tasa: " + tasaBigDecimal);
                        System.out.println("Resultado: " + codigoDivisa + " " + String.format("%.2f", divisaConvertida));
                        esperarTecla(scanner);

                    } catch (Exception e) {
                        System.out.println("Error al convertir la divisa: " + e.getMessage());
                    }



                } else {
                    System.out.println("Número inválido.");
                }
            } else {
                System.out.println("Opción inválida.");
            }
        }
        scanner.close();
    }
}