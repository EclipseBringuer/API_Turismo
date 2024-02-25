package com.example.turismApi;

import com.example.turismApi.utilities.Utility;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Clase principal que inicia la aplicación Spring Boot y abre la documentación en un navegador web.
 */
@SpringBootApplication
public class TurismApiApplication {

    /**
     * Método principal que inicia la aplicación Spring Boot y abre la documentación en un navegador web.
     * @param args Argumentos de línea de comandos.
     */
    public static void main(String[] args) {
        SpringApplication.run(TurismApiApplication.class, args);
        Utility.openDocumentation();
    }

}
