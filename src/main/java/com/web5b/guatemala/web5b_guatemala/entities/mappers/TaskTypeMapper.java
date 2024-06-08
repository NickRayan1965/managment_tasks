package com.web5b.guatemala.web5b_guatemala.entities.mappers;

import org.springframework.stereotype.Component;

import com.web5b.guatemala.web5b_guatemala.dtos.CreateTaskTypeDto;
import com.web5b.guatemala.web5b_guatemala.dtos.UpdateTaskTypeDto;
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

}
