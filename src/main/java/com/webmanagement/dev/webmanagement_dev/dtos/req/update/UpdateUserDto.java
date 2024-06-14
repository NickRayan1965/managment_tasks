package com.webmanagement.dev.webmanagement_dev.dtos.req.update;


import com.webmanagement.dev.webmanagement_dev.dtos.req.create.CreateUserDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UpdateUserDto extends CreateUserDto {
  private Boolean enabled;
}
