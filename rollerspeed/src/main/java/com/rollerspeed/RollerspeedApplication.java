package com.rollerspeed;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// Esta es la clase principal que arranca toda la aplicación
@SpringBootApplication
public class RollerspeedApplication {

    public static void main(String[] args) {
        SpringApplication.run(RollerspeedApplication.class, args);
        System.out.println("\n===========================================");
        System.out.println("🛼 Club Paen - Sistema iniciado correctamente");
        System.out.println("📍 Abrí tu navegador en: http://localhost:8080");
        System.out.println("===========================================\n");
    }
}
