package com.web5b.guatemala.web5b_guatemala.entities.mappers;

import java.util.List;

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
        .id(entity.getUser_id())
        .username(entity.getUsername())
        .role(entity.getUser_role())
        .enabled(entity.getUser_enabled())
        .build();
    TaskTypeDto type = entity.getTask_type_id() != null ? TaskTypeDto.builder()
        .id(entity.getTask_type_id())
        .name(entity.getTask_type_name())
        .enabled(entity.getTask_type_enabled())
        .build() : null;
    TaskDto task = TaskDto.builder()
        .id(entity.getTask_id())
        .name(entity.getTask_name())
        .description(entity.getTask_description())
        .enabled(entity.getTask_enabled())
        .user(user)
        .type(type)
        .build();
    return task;
  }

  @Override
  public List<TaskDto> toDto(List<TaskDbJoined> entities) {
    return entities.stream().map(this::toDto).toList();
  }

  @Override
  public TaskDto toDto(Task entity) {
    UserDto user = UserDto.builder()
        .id(entity.getUserId())
        .build();
    TaskTypeDto type = entity.getTypeId() != null ? TaskTypeDto.builder()
        .id(entity.getTypeId())
        .build() : null;
    return TaskDto.builder()
        .id(entity.getId())
        .name(entity.getName())
        .description(entity.getDescription())
        .enabled(entity.getEnabled())
        .user(user)
        .type(type)
        .build();
  }

  @Override
  public List<TaskDto> toDto(Iterable<Task> entities) {
    return ((List<Task>) entities).stream().map(this::toDto).toList();
  }

  @Override
  public Task dtoToEntity(TaskDto dto) {
    return Task.builder()
        .id(dto.getId())
        .name(dto.getName())
        .description(dto.getDescription())
        .enabled(dto.getEnabled())
        .userId(dto.getUser().getId())
        .typeId(dto.getType() != null ? dto.getType().getId() : null)
        .build();
  }

}
