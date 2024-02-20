package com.example.turismApi.utilities;

import java.io.IOException;

public class Utility {
    public static void openDocumentation() {
        try {
            Runtime.getRuntime().exec("cmd /c start chrome http://localhost:8080/docu");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
