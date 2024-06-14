package com.webmanagement.dev.webmanagement_dev.entities.mappers;

import java.util.List;

import org.springframework.stereotype.Component;

import com.webmanagement.dev.webmanagement_dev.dtos.req.create.CreateTaskTypeDto;
import com.webmanagement.dev.webmanagement_dev.dtos.req.update.UpdateTaskTypeDto;
import com.webmanagement.dev.webmanagement_dev.dtos.res.TaskTypeDto;
import com.webmanagement.dev.webmanagement_dev.entities.TaskType;

@Component
public class TaskTypeMapper implements ITaskTypeMapper {

  @Override
  public TaskType dtoToEntity(CreateTaskTypeDto dto) {
    return TaskType.builder()
        .name(dto.getName())
        .enabled(true)
        .build();
  }

  @Override
  public TaskType dtoToEntity(UpdateTaskTypeDto dto) {
    return TaskType.builder()
        .name(dto.getName())
        .enabled(dto.getEnabled())
        .build();
  }

  @Override
  public TaskTypeDto toDto(TaskType entity) {
    return TaskTypeDto.builder()
        .id(entity.getId())
        .name(entity.getName())
        .enabled(entity.getEnabled())
        .build();
  }

  @Override
  public List<TaskTypeDto> toDto(List<TaskType> entities) {
    return entities.stream().map(this::toDto).toList();
  }

  @Override
  public TaskType dtoToEntity(TaskTypeDto dto) {
    return TaskType.builder()
        .id(dto.getId())
        .name(dto.getName())
        .enabled(dto.getEnabled())
        .build();
  }

}
