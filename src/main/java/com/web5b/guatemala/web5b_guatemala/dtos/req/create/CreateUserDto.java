package com.web5b.guatemala.web5b_guatemala.dtos.req.create;

import com.web5b.guatemala.web5b_guatemala.entities.Role;

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
