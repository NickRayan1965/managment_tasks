package com.webmanagement.dev.webmanagement_dev.dtos.res;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskTypeDto {
  private Long id;
  private String name;
  private Boolean enabled;
}
