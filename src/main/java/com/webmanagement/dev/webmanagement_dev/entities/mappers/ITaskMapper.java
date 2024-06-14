package com.webmanagement.dev.webmanagement_dev.entities.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.webmanagement.dev.webmanagement_dev.dtos.db.TaskDbJoined;
import com.webmanagement.dev.webmanagement_dev.dtos.req.create.CreateTaskDto;
import com.webmanagement.dev.webmanagement_dev.dtos.req.update.UpdateTaskDto;
import com.webmanagement.dev.webmanagement_dev.dtos.res.TaskDto;
import com.webmanagement.dev.webmanagement_dev.entities.Task;

@Mapper(componentModel = "spring")
public interface ITaskMapper {
  // Task dtoToEntity(CreateTaskDto dto); ok
  default Task dtoToEntity(CreateTaskDto dto) {
    Task entity = new Task();
    mergeToEntity(dto, entity);
    return entity;
  }
  // Task dtoToEntity(UpdateTaskDto dto); ok
  default Task dtoToEntity(UpdateTaskDto dto) {
    Task entity = new Task();
    mergeToEntity(dto, entity);
    return entity;
  }

  // Task dtoToEntity(TaskDto dto); ok
  default Task dtoToEntity(TaskDto dto) {
    Task entity = new Task();
    setDtoOnEntity(dto, entity);
    return entity;
  }

  // TaskDto toDto(TaskDbJoined entity); ok
  default TaskDto toDto(TaskDbJoined entity) {
    TaskDto dto = new TaskDto();
    mergeToDto(entity, dto);
    return dto;
  }

  // TaskDto toDto(Task entity); ok
  default TaskDto toDto(Task entity) {
    TaskDto dto = new TaskDto();
    setEntityOnDto(entity, dto);
    return dto;
  }

  // List<TaskDto> toDto(Iterable<Task> entities);
  default Iterable<TaskDto> toDto(Iterable<Task> entities) {
    return ((List<Task>) entities).stream().map(this::toDto).toList();
  }

  @Mapping(target = "id", source = "task_id")
  @Mapping(target = "name", source = "task_name")
  @Mapping(target = "description", source = "task_description")
  @Mapping(target = "type.id", source = "task_type_id")
  @Mapping(target = "type.name", source = "task_type_name")
  @Mapping(target = "type.enabled", source = "task_type_enabled")
  @Mapping(target = "user.id", source = "user_id")
  @Mapping(target = "user.username", source = "username")
  @Mapping(target = "user.role", source = "user_role")
  @Mapping(target = "user.enabled", source = "user_enabled")
  @Mapping(target = "enabled", source = "task_enabled")
  void mergeToDto(TaskDbJoined entity, @MappingTarget TaskDto dto);
  
  @Mapping(target = "id", ignore = true)
  @Mapping(target = "userId", ignore = true)
  void mergeToEntity(UpdateTaskDto dto, @MappingTarget Task entity);

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "userId", ignore = true)
  @Mapping(target = "enabled", ignore = true, defaultValue = "true")
  void mergeToEntity(CreateTaskDto dto, @MappingTarget Task entity);

  @Mapping(target = "userId", source = "user.id")
  @Mapping(target = "typeId", source = "type.id")
  void setDtoOnEntity(TaskDto dto, @MappingTarget Task entity);

  @Mapping(target = "user.id", source = "userId")
  @Mapping(target = "type.id", source = "typeId")
  void setEntityOnDto(Task entity, @MappingTarget TaskDto dto);
}
