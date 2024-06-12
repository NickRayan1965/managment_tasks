package com.web5b.guatemala.web5b_guatemala.services;

import java.util.Arrays;

import org.springframework.stereotype.Service;

import com.web5b.guatemala.web5b_guatemala.dtos.req.create.CreateTaskDto;
import com.web5b.guatemala.web5b_guatemala.dtos.req.update.UpdateTaskDto;
import com.web5b.guatemala.web5b_guatemala.dtos.res.TaskDto;
import com.web5b.guatemala.web5b_guatemala.entities.Task;
import com.web5b.guatemala.web5b_guatemala.entities.mappers.ITaskMapper;
import com.web5b.guatemala.web5b_guatemala.models.NotFoundException;
import com.web5b.guatemala.web5b_guatemala.repositories.ITaskRepository;

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
  public Flux<TaskDto> findAll() {
    return taskRepository.findAllEnabled().map(taskMapper::toDto);
  }

  @Override
  public Mono<TaskDto> findOneById(Long id) {
    return taskRepository.findOneById(id)
        .map(taskMapper::toDto)
        .switchIfEmpty(Mono.error(new NotFoundException("Task not found.")));
  }

  @Override
  public Mono<TaskDto> create(CreateTaskDto dto) {
    return getDtoVerified(taskMapper.dtoToEntity(dto))
        .flatMap(taskRepository::save)
        .map(taskMapper::toDto);
  }

  @Override
  public Mono<TaskDto> update(Long id, UpdateTaskDto dto) {
    return findOneById(id)
        .flatMap(task -> getDtoVerified(taskMapper.dtoToEntity(dto)))
        .flatMap(taskRepository::save)
        .map(taskMapper::toDto);
  }

  @Override
  public Mono<Void> delete(Long id) {
    return findOneById(id)
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
