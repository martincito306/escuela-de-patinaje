package com.patinaje.v1.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

@Bean
public OpenAPI customOpenAPI() {
        Server localServer = new Server();
        localServer.setUrl("http://localhost:8085");
        localServer.setDescription("Servidor de desarrollo local");

        Contact contact = new Contact();
        contact.setName("Club Paen - Escuela de Patinaje");
        contact.setEmail("contacto@clubpaen.com");
        contact.setUrl("http://localhost:8085/inicio");

        License license = new License()
                .name("MIT License")
                .url("https://opensource.org/licenses/MIT");

        Info info = new Info()
                .title("API Club Paen - Escuela de Patinaje Envigado")
                .version("1.0.0")
                .description("API REST para la gestión de programas, horarios y mensajes de contacto de la escuela de patinaje Club Paen en Envigado, Antioquia.")
                .contact(contact)
                .license(license);

        return new OpenAPI()
                .info(info)
                .servers(List.of(localServer));
    }
}
