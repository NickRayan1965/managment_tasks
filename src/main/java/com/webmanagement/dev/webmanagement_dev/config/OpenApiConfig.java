package com.webmanagement.dev.webmanagement_dev.config;

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
    title = "Tasks Management System",
    description = "This is a simple REST API for a tasks management system.",
    version = "v1.0.0"
  ),
  servers = {
    @Server(
      url = "http://hostnick.ddns.net:6012",
      description = "Production"
    ),
    @Server(
    url = "http://localhost:3000",
    description = "Local"
    )
  }
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
  }
)
public class OpenApiConfig {
  
}
