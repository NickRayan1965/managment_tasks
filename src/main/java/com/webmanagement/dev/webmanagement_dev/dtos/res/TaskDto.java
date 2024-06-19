package com.webmanagement.dev.webmanagement_dev.dtos.res;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TaskDto {

  @Schema(title = "Identificador de la tarea", nullable = false)
  private Long id;

  @Schema(description = "Nombre de la tarea", maxLength = 50, minLength = 4, nullable = false)
  private String name;

  @Schema(description = "Descripción de la tarea", nullable = true, maxLength = 1000)
  private String description;

  @Schema(description = "Tipo de tarea", nullable = true)
  private TaskTypeDto type;

  @Schema(description = "Usuario asignado a la tarea", nullable = false)
  private UserDto user;

  @Schema(description = "Habilitado", nullable = false, defaultValue = "true")
  private Boolean enabled;
}
