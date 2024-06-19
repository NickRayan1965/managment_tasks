package com.webmanagement.dev.webmanagement_dev.dtos.req.create;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateTaskTypeDto {
  @NotNull @Size(min = 4, max = 50)
  private String name;
}
