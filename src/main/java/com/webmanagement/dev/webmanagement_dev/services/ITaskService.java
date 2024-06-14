package com.webmanagement.dev.webmanagement_dev.services;

import com.webmanagement.dev.webmanagement_dev.dtos.req.create.CreateTaskDto;
import com.webmanagement.dev.webmanagement_dev.dtos.req.update.UpdateTaskDto;
import com.webmanagement.dev.webmanagement_dev.dtos.res.TaskDto;
import com.webmanagement.dev.webmanagement_dev.entities.Task;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ITaskService {

  Mono<TaskDto> create(CreateTaskDto dto, Long userId);

  Mono<TaskDto> update(Long id, UpdateTaskDto dto, Long userId);

  Mono<TaskDto> findOneById(Long id, Long userId);

  Mono<Void> delete(Long id, Long userId);

  Flux<TaskDto> findAllEnabledByUserId(Long userId);

  Mono<Task> getDtoVerified(Task dto);

}
