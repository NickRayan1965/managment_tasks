package com.webmanagement.dev.webmanagement_dev.dtos.res;

import com.webmanagement.dev.webmanagement_dev.entities.Role;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
  @Schema(title = "Identificador del usuario")
  private Long id;

  @Schema(description = "Nombre de usuario (unico)", maxLength = 50, minLength = 4)
  private String username;
  
  @Schema(description = "Rol del usuario", nullable = true)
  private Role role;

  @Schema(description = "Habilitado", defaultValue = "true")
  private Boolean enabled;
}
