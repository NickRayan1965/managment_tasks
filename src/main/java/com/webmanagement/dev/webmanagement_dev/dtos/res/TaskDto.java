package com.webmanagement.dev.webmanagement_dev.dtos.res;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TaskDto {
  private Long id;
  private String name;
  private String description;
  private TaskTypeDto type;
  private UserDto user;
  private Boolean enabled;
}
