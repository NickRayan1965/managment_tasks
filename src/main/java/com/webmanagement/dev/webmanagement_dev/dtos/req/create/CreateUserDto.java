package com.webmanagement.dev.webmanagement_dev.dtos.req.create;

import com.webmanagement.dev.webmanagement_dev.entities.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserDto {
  private String username;
  private String password;
  private Role role;
}
