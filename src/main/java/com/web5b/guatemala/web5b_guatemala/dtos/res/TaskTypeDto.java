package com.web5b.guatemala.web5b_guatemala.dtos.res;

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
}
