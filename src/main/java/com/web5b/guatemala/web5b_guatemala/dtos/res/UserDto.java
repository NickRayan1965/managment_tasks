package com.web5b.guatemala.web5b_guatemala.dtos.res;

import com.web5b.guatemala.web5b_guatemala.entities.Role;

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
