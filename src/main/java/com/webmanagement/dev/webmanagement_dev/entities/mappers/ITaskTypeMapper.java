package com.webmanagement.dev.webmanagement_dev.entities.mappers;

import java.util.List;

import com.webmanagement.dev.webmanagement_dev.dtos.req.create.CreateTaskTypeDto;
import com.webmanagement.dev.webmanagement_dev.dtos.req.update.UpdateTaskTypeDto;
import com.webmanagement.dev.webmanagement_dev.dtos.res.TaskTypeDto;
import com.webmanagement.dev.webmanagement_dev.entities.TaskType;

public interface ITaskTypeMapper {
  TaskType dtoToEntity(CreateTaskTypeDto dto);
  TaskType dtoToEntity(UpdateTaskTypeDto dto);
  TaskType dtoToEntity(TaskTypeDto dto);
  TaskTypeDto toDto(TaskType entity);
  List<TaskTypeDto> toDto(List<TaskType> entities);
}