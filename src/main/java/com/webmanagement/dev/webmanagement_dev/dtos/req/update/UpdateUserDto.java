package com.webmanagement.dev.webmanagement_dev.dtos.req.update;


import com.webmanagement.dev.webmanagement_dev.dtos.req.create.CreateUserDto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UpdateUserDto extends CreateUserDto {

  @NotNull
  private Boolean enabled;
}
