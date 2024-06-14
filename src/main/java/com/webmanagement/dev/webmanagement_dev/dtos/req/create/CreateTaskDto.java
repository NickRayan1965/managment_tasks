package com.webmanagement.dev.webmanagement_dev.dtos.req.create;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateTaskDto {
  private String name;
  private String description;
  private Long typeId;
}
