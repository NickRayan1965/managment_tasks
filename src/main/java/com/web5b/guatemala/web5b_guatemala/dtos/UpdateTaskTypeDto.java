package com.web5b.guatemala.web5b_guatemala.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UpdateTaskTypeDto extends CreateTaskTypeDto {
  private Boolean enabled;
}
