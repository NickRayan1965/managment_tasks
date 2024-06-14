package com.webmanagement.dev.webmanagement_dev.dtos.res;

import com.webmanagement.dev.webmanagement_dev.entities.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
  private Long id;
  private String username;
  private Role role;
  private Boolean enabled;
}
