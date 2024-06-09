package com.web5b.guatemala.web5b_guatemala.dtos.db;

import com.web5b.guatemala.web5b_guatemala.entities.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TaskDbJoined {
  private Long task_id;
  private String task_name;
  private String task_description;

  private Long task_type_id;
  private String task_type_name;
  private Boolean task_type_enabled;

  private Long user_id;
  private String username;
  private Role user_role;
  private Boolean user_enabled;


  private Boolean task_enabled;

}
