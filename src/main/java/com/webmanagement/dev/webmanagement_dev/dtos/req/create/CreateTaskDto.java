package com.webmanagement.dev.webmanagement_dev.dtos.req.create;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateTaskDto {

  @NotNull(message = "El nombre no puede ser nulo")
  @Size(min = 4, max = 50, message = "El nombre debe tener entre 4 y 50 caracteres")
  private String name;

  @Size(max = 1000, min = 4, message = "La descripción debe tener entre 4 y 1000 caracteres")
  private String description;

  @Schema(description = "Identificador del tipo de tarea", nullable = true)
  private Long typeId;
}
