package com.project.backend_api;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "INFIERO",
                version = "1.0",
                description = "Welcome to the Custom API documentation. Explore the endpoints below!"
        ),
        servers = {
                @Server(url = "http://localhost:8080", description = "Local server")
        }
)
public class OpenAPIConfig {
}
