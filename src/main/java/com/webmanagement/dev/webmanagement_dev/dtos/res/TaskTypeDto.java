package com.webmanagement.dev.webmanagement_dev.dtos.res;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskTypeDto {
  @Schema(description = "Identificador del tipo de tarea", nullable = false)
  private Long id;
  @Schema(description = "Nombre del tipo de tarea (único)", maxLength = 50, minLength = 4, nullable = false)
  private String name;
  @Schema(description = "Habilitado", nullable = false, defaultValue = "true")
  private Boolean enabled;
}
