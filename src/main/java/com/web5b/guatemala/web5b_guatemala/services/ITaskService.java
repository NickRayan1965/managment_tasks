package com.web5b.guatemala.web5b_guatemala.services;

import com.web5b.guatemala.web5b_guatemala.dtos.create.CreateTaskDto;
import com.web5b.guatemala.web5b_guatemala.dtos.res.TaskDto;
import com.web5b.guatemala.web5b_guatemala.dtos.update.UpdateTaskDto;
import com.web5b.guatemala.web5b_guatemala.entities.Task;

public interface ITaskService extends IBaseService<Task, TaskDto, CreateTaskDto, UpdateTaskDto>{
  
}
