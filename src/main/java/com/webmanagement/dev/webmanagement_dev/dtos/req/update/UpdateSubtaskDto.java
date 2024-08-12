package com.webmanagement.dev.webmanagement_dev.dtos.req.update;

import com.webmanagement.dev.webmanagement_dev.dtos.req.create.CreateSubtaskDto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UpdateSubtaskDto extends CreateSubtaskDto {
  @Schema(description = "¿Esta completado?")
  Boolean isCompleted;
}
