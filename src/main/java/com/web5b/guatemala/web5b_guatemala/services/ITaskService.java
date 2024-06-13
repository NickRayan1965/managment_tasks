package com.web5b.guatemala.web5b_guatemala.services;

import com.web5b.guatemala.web5b_guatemala.dtos.req.create.CreateTaskDto;
import com.web5b.guatemala.web5b_guatemala.dtos.req.update.UpdateTaskDto;
import com.web5b.guatemala.web5b_guatemala.dtos.res.TaskDto;
import com.web5b.guatemala.web5b_guatemala.entities.Task;

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
