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
  @Size(max = 50, min = 4)
  @NotNull
  private String username;

  @Size(max = 100, min = 8)
  @NotNull
  private String password;

  private Role role;
}
