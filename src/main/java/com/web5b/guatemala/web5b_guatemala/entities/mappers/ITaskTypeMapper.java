package com.web5b.guatemala.web5b_guatemala.entities.mappers;

import java.util.List;

import com.web5b.guatemala.web5b_guatemala.dtos.req.create.CreateTaskTypeDto;
import com.web5b.guatemala.web5b_guatemala.dtos.req.update.UpdateTaskTypeDto;
import com.web5b.guatemala.web5b_guatemala.dtos.res.TaskTypeDto;
import com.web5b.guatemala.web5b_guatemala.entities.TaskType;

public interface ITaskTypeMapper {
  TaskType dtoToEntity(CreateTaskTypeDto dto);
  TaskType dtoToEntity(UpdateTaskTypeDto dto);
  TaskType dtoToEntity(TaskTypeDto dto);
  TaskTypeDto toDto(TaskType entity);
  List<TaskTypeDto> toDto(List<TaskType> entities);
}