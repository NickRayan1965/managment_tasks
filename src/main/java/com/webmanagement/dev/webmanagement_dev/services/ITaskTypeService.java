package com.webmanagement.dev.webmanagement_dev.services;

import com.webmanagement.dev.webmanagement_dev.dtos.req.create.CreateTaskTypeDto;
import com.webmanagement.dev.webmanagement_dev.dtos.req.update.UpdateTaskTypeDto;
import com.webmanagement.dev.webmanagement_dev.dtos.res.TaskTypeDto;
import com.webmanagement.dev.webmanagement_dev.entities.TaskType;

public interface ITaskTypeService extends IBaseService<TaskType, TaskTypeDto, CreateTaskTypeDto, UpdateTaskTypeDto> {
}
