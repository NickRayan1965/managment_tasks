package com.web5b.guatemala.web5b_guatemala.dtos.req.update;

import com.web5b.guatemala.web5b_guatemala.dtos.req.create.CreateTaskDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UpdateTaskDto extends CreateTaskDto {
  private Boolean enabled;
}
