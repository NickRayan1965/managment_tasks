package com.web5b.guatemala.web5b_guatemala.entities.mappers;

import com.web5b.guatemala.web5b_guatemala.dtos.CreateTaskTypeDto;
import com.web5b.guatemala.web5b_guatemala.dtos.UpdateTaskTypeDto;
import com.web5b.guatemala.web5b_guatemala.entities.TaskType;

public interface ITaskTypeMapper {
  TaskType dtoToEntity(CreateTaskTypeDto dto);
  TaskType dtoToEntity(UpdateTaskTypeDto dto);
}