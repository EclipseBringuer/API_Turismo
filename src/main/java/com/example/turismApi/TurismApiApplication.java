package com.example.turismApi;

import com.example.turismApi.utilities.Utility;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TurismApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(TurismApiApplication.class, args);
        Utility.openDocumentation();
    }

}
