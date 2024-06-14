package com.webmanagement.dev.webmanagement_dev.services;

import java.util.Arrays;

import org.springframework.stereotype.Service;

import com.webmanagement.dev.webmanagement_dev.dtos.db.TaskDbJoined;
import com.webmanagement.dev.webmanagement_dev.dtos.req.create.CreateTaskDto;
import com.webmanagement.dev.webmanagement_dev.dtos.req.update.UpdateTaskDto;
import com.webmanagement.dev.webmanagement_dev.dtos.res.TaskDto;
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
    return getDtoVerified(taskMapper.dtoToEntity(dto))
        .doOnNext(task -> task.setUserId(userId))
        .flatMap(taskRepository::save)
        .map(taskMapper::toDto);
  }

  @Override
  public Mono<TaskDto> update(Long id, UpdateTaskDto dto, Long userId) {
    return findOneById(id, userId)
          .map(taskMapper::dtoToEntity)
          .flatMap(task-> {
            return getDtoVerified(taskMapper.dtoToEntity(dto))
                .map(t -> task);
          })
          .doOnNext(task -> taskMapper.mergeToEntity(dto, task))
          .flatMap(taskRepository::save)
          .map(taskMapper::toDto)    
        ;
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
    Flux<Mono<?>> resultFlux = Flux.fromIterable(Arrays.asList(
        task.getUserId() != null ? userService.findOneById(task.getUserId()) : Mono.empty(),
        task.getTypeId() != null ? taskTypeService.findOneById(task.getTypeId()) : Mono.empty()));
    return Flux.zip(resultFlux, objects -> objects)
        .then(Mono.just(task));
  }

}
