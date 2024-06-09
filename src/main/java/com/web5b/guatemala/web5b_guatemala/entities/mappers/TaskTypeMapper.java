package com.web5b.guatemala.web5b_guatemala.entities.mappers;

import java.util.List;

import org.springframework.stereotype.Component;

import com.web5b.guatemala.web5b_guatemala.dtos.create.CreateTaskTypeDto;
import com.web5b.guatemala.web5b_guatemala.dtos.res.TaskTypeDto;
import com.web5b.guatemala.web5b_guatemala.dtos.update.UpdateTaskTypeDto;
import com.web5b.guatemala.web5b_guatemala.entities.TaskType;

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

}
