package com.webmanagement.dev.webmanagement_dev.dtos.req.create;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateSubtaskDto {
  @NotNull(message = "El nombre no puede ser nulo")
  @Size(min = 4, max = 50, message = "El nombre debe tener entre 4 y 50 caracteres")
  private String name;
}
