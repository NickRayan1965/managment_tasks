package com.web5b.guatemala.web5b_guatemala.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.security.SecuritySchemes;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
  info = @Info(
    contact = @Contact(
      name = "Nick Rayan Cerrón",
      email = "nickcerron@gmail.com"
    ),
    title = "Web5B (Tasks Management System)",
    description = "This is a simple REST API for a tasks management system.",
    version = "v1.0.0"
  ),
  servers = {@Server(
    url = "http://localhost:3000",
    description = "Local"
  )}
)
@SecuritySchemes(
  {
    @SecurityScheme(
    name = "BearerAuth",
    description = "JWT Token",
    scheme = "bearer",
    type = SecuritySchemeType.HTTP,
    bearerFormat = "JWT",
    in = SecuritySchemeIn.HEADER
    ),
    @SecurityScheme(
    name = "NoAuth",
    type = SecuritySchemeType.OPENIDCONNECT
    )
  }
)
public class OpenApiConfig {
  
}
