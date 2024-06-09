package com.web5b.guatemala.web5b_guatemala.entities.mappers;

import org.springframework.stereotype.Component;

import com.web5b.guatemala.web5b_guatemala.dtos.create.CreateTaskDto;
import com.web5b.guatemala.web5b_guatemala.dtos.db.TaskDbJoined;
import com.web5b.guatemala.web5b_guatemala.dtos.res.TaskDto;
import com.web5b.guatemala.web5b_guatemala.dtos.res.TaskTypeDto;
import com.web5b.guatemala.web5b_guatemala.dtos.res.UserDto;
import com.web5b.guatemala.web5b_guatemala.dtos.update.UpdateTaskDto;
import com.web5b.guatemala.web5b_guatemala.entities.Task;

@Component
public class TaskMapper implements ITaskMapper {

  @Override
  public Task dtoToEntity(CreateTaskDto dto) {
    return Task.builder()
        .name(dto.getName())
        .description(dto.getDescription())
        .typeId(dto.getTypeId())
        .userId(dto.getUserId())
        .enabled(true)
        .build();
  }

  @Override
  public Task dtoToEntity(UpdateTaskDto dto) {
    return Task.builder()
        .name(dto.getName())
        .description(dto.getDescription())
        .typeId(dto.getTypeId())
        .userId(dto.getUserId())
        .enabled(dto.getEnabled())
        .build();
  }

  @Override
  public TaskDto toDto(TaskDbJoined entity) {
    UserDto user = UserDto.builder()
        .id(entity.getUserId())
        .username(entity.getUsername())
        .role(entity.getUserRole())
        .enabled(entity.getUserEnabled())
        .build();
    TaskTypeDto type = entity.getTaskTypeId() != null ? TaskTypeDto.builder()
        .id(entity.getTaskTypeId())
        .name(entity.getTaskTypeName())
        .build() : null;
    TaskDto task = TaskDto.builder()
        .id(entity.getTaskId())
        .name(entity.getTaskName())
        .description(entity.getTaskDescription())
        .enabled(entity.getTaskEnabled())
        .user(user)
        .type(type)
        .build();
    return task;
  }

}
