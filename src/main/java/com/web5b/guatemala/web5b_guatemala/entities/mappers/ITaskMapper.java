package com.web5b.guatemala.web5b_guatemala.entities.mappers;

import java.util.List;

import com.web5b.guatemala.web5b_guatemala.dtos.db.TaskDbJoined;
import com.web5b.guatemala.web5b_guatemala.dtos.req.create.CreateTaskDto;
import com.web5b.guatemala.web5b_guatemala.dtos.req.update.UpdateTaskDto;
import com.web5b.guatemala.web5b_guatemala.dtos.res.TaskDto;
import com.web5b.guatemala.web5b_guatemala.entities.Task;

public interface ITaskMapper {
  Task dtoToEntity(CreateTaskDto dto);

  Task dtoToEntity(UpdateTaskDto dto);
  
  Task dtoToEntity(TaskDto dto);

  TaskDto toDto(TaskDbJoined entity);

  TaskDto toDto(Task entity);
  List<TaskDto> toDto(Iterable<Task> entities);

  List<TaskDto> toDto(List<TaskDbJoined> entities);
}
