package com.web5b.guatemala.web5b_guatemala.dtos.create;

import com.web5b.guatemala.web5b_guatemala.entities.Role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserDto {
  private String username;
  private String password;
  private Role role;
}
