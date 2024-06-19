package com.webmanagement.dev.webmanagement_dev.services;

import org.springframework.stereotype.Service;

import com.webmanagement.dev.webmanagement_dev.dtos.db.TaskDbJoined;
import com.webmanagement.dev.webmanagement_dev.dtos.req.create.CreateTaskDto;
import com.webmanagement.dev.webmanagement_dev.dtos.req.update.UpdateTaskDto;
import com.webmanagement.dev.webmanagement_dev.dtos.res.TaskDto;
import com.webmanagement.dev.webmanagement_dev.dtos.res.TaskTypeDto;
import com.webmanagement.dev.webmanagement_dev.dtos.res.UserDto;
import com.webmanagement.dev.webmanagement_dev.entities.Task;
import com.webmanagement.dev.webmanagement_dev.entities.mappers.ITaskMapper;
import com.webmanagement.dev.webmanagement_dev.models.NotFoundException;
import com.webmanagement.dev.webmanagement_dev.repositories.ITaskRepository;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class TaskService implements ITaskService {
  private final ITaskRepository taskRepository;
  private final IUserService userService;
  private final ITaskTypeService taskTypeService;
  private final ITaskMapper taskMapper;

  @Override
  public Flux<TaskDto> findAllEnabledByUserId(Long userId) {
    return taskRepository.findAllEnabledByUserId(userId).map(taskMapper::toDto);
  }

  @Override
  public Mono<TaskDto> findOneById(Long id, Long userId) {
    Mono<TaskDbJoined> taskPromise = userId != null ? taskRepository.findOneByIdAndUserId(id, userId)
        : taskRepository.findOneById(id);
    return taskPromise
        .map(taskMapper::toDto)
        .switchIfEmpty(Mono.error(new NotFoundException("Task not found.")));
  }

  @Override
  public Mono<TaskDto> create(CreateTaskDto dto, Long userId) {
    return Mono.just(taskMapper.dtoToEntity(dto))
        .doOnNext(task -> task.setUserId(userId))
        .flatMap(this::getDtoVerified)
        .flatMap(taskRepository::save)
        .map(taskMapper::toDto);
  }

  @Override
  public Mono<TaskDto> update(Long id, UpdateTaskDto dto, Long userId) {
    return findOneById(id, userId)
        .map(taskMapper::dtoToEntity)
        .flatMap(task -> {
          return getDtoVerified(taskMapper.dtoToEntity(dto))
              .map(t -> task);
        })
        .doOnNext(task -> taskMapper.mergeToEntity(dto, task))
        .flatMap(taskRepository::save)
        .map(taskMapper::toDto);
  }

  @Override
  public Mono<Void> delete(Long id, Long userId) {
    return findOneById(id, userId)
        .map(taskMapper::dtoToEntity)
        .flatMap(task -> {
          task.setEnabled(false);
          return taskRepository.save(task);
        })
        .then();
  }

  @Override
  public Mono<Task> getDtoVerified(Task task) {
    Long typeId = task.getTypeId();
    Long userId = task.getUserId();
    Mono<UserDto> userPromise = userId != null ? userService.findOneById(userId) : Mono.empty();
    Mono<TaskTypeDto> taskTypePromise = typeId != null ? taskTypeService.findOneById(typeId) : Mono.empty();
    return Flux.merge(userPromise, taskTypePromise)
        .collectList()
        .map(list -> task);
  }

}
