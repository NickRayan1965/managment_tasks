package com.web5b.guatemala.web5b_guatemala.services;

import com.web5b.guatemala.web5b_guatemala.dtos.create.CreateTaskTypeDto;
import com.web5b.guatemala.web5b_guatemala.dtos.res.TaskTypeDto;
import com.web5b.guatemala.web5b_guatemala.dtos.update.UpdateTaskTypeDto;
import com.web5b.guatemala.web5b_guatemala.entities.TaskType;

public interface ITaskTypeService extends IBaseService<TaskType, TaskTypeDto, CreateTaskTypeDto, UpdateTaskTypeDto> {
}
