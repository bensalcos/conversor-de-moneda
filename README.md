# Conversor de Moneda - Oracle ONE Education Challenge

## Descripción
Este proyecto es parte del **Challenge Conversor de Moneda** del programa **Oracle ONE Education**. La aplicación permite realizar conversiones entre 161 divisas utilizando tasas de cambio actualizadas obtenidas de una API externa.

## Características
- Conversión entre 161 monedas internacionales.
- Obtención de tasas de cambio actualizadas en tiempo real mediante la API **https://v6.exchangerate-api.com**.
- Validación de entradas para garantizar datos correctos.
- Interfaz gráfica mediante consola.
- Uso de patrones de diseño para estructurar el código de manera eficiente y escalable.

## Tecnologías Utilizadas
- **Lenguaje:** Java
- **Bibliotecas:**
  - [Gson](https://github.com/google/gson): Para el manejo y parsing del JSON de la API.
  - `HttpURLConnection`: Para realizar solicitudes HTTP.
- **IDE:** IntelliJ IDEA

## Requisitos Previos
1. Tener instalado **Java 17** o superior.
2. Un IDE de tu preferencia (IntelliJ, Eclipse, NetBeans, etc.).
3. Conexión a Internet para consumir la API de tasas de cambio.

## Cómo Ejecutar el Proyecto
1. Clona este repositorio:
   ```bash
   git clone https://github.com/bensalcos/conversor-de-moneda.git
  
2. Abre el proyecto en tu IDE.
3. Ejecuta el archivo principal (Main.java) para iniciar la aplicación
