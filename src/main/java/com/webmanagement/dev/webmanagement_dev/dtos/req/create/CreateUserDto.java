package com.webmanagement.dev.webmanagement_dev.dtos.req.create;

import com.webmanagement.dev.webmanagement_dev.entities.Role;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserDto {
  // indicar q es unico
  @Schema(description = "Nombre de usuario (unico)")
  @Size(max = 50, min = 4, message = "El nombre de usuario debe tener entre 4 y 50 caracteres")
  @NotNull(message = "El nombre de usuario no puede ser nulo")
  private String username;

  @Size(max = 100, min = 8, message = "La contraseña debe tener entre 8 y 100 caracteres")
  @NotNull(message = "La contraseña no puede ser nula")
  private String password;

  private Role role;
}
