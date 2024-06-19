package com.webmanagement.dev.webmanagement_dev.entities.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.webmanagement.dev.webmanagement_dev.dtos.req.create.CreateTaskTypeDto;
import com.webmanagement.dev.webmanagement_dev.dtos.req.update.UpdateTaskTypeDto;
import com.webmanagement.dev.webmanagement_dev.dtos.res.TaskTypeDto;
import com.webmanagement.dev.webmanagement_dev.entities.TaskType;

@Mapper(componentModel = "spring")
public interface ITaskTypeMapper {
  // TaskType dtoToEntity(CreateTaskTypeDto dto);
  default TaskType dtoToEntity(CreateTaskTypeDto dto) {
    TaskType entity = new TaskType();
    mergeToEntity(dto, entity);
    return entity;
  }
  // TaskType dtoToEntity(UpdateTaskTypeDto dto);
  default TaskType dtoToEntity(UpdateTaskTypeDto dto) {
    TaskType entity = new TaskType();
    mergeToEntity(dto, entity);
    return entity;
  }
  
  // TaskType dtoToEntity(TaskTypeDto dto);
  default TaskType dtoToEntity(TaskTypeDto dto) {
    TaskType entity = new TaskType();
    setDtoOnEntity(dto, entity);
    return entity;
  }
  // TaskTypeDto toDto(TaskType entity);
  default TaskTypeDto toDto(TaskType entity) {
    TaskTypeDto dto = new TaskTypeDto();
    setEntityOnDto(entity, dto);
    return dto;
  }
  // List<TaskTypeDto> toDto(List<TaskType> entities);
  default Iterable<TaskTypeDto> toDto(Iterable<TaskType> entities) {
    return ((List<TaskType>) entities).stream().map(this::toDto).toList();
  }

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "name", source = "name")
  @Mapping(target = "enabled", constant = "true")
  void mergeToEntity(CreateTaskTypeDto dto, @MappingTarget TaskType entity);

  @Mapping(target = "id", ignore = true)
  void mergeToEntity(UpdateTaskTypeDto dto, @MappingTarget TaskType entity);

  void setDtoOnEntity(TaskTypeDto dto, @MappingTarget TaskType entity);

  void setEntityOnDto(TaskType entity, @MappingTarget TaskTypeDto dto);
}