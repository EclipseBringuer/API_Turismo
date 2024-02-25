package com.example.turismApi.utilities;

import java.io.IOException;

/**
 * Clase de utilidad que proporciona métodos para operaciones comunes.
 */
public class Utility {
    /**
     * Abre la documentación en un navegador web.
     */
    public static void openDocumentation() {
        try {
            Runtime.getRuntime().exec("cmd /c start chrome http://localhost:8080/docu");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
