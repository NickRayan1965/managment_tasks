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
  private Long taskId;
  private String taskName;
  private String taskDescription;

  private Long taskTypeId;
  private String taskTypeName;

  private Long userId;
  private String username;
  private Role userRole;
  private Boolean userEnabled;


  private Boolean taskEnabled;

}
