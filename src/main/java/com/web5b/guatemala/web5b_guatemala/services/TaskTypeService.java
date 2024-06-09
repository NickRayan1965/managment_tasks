package com.web5b.guatemala.web5b_guatemala.services;

import org.springframework.stereotype.Service;

import com.web5b.guatemala.web5b_guatemala.dtos.create.CreateTaskTypeDto;
import com.web5b.guatemala.web5b_guatemala.dtos.update.UpdateTaskTypeDto;
import com.web5b.guatemala.web5b_guatemala.entities.TaskType;
import com.web5b.guatemala.web5b_guatemala.entities.mappers.ITaskTypeMapper;
import com.web5b.guatemala.web5b_guatemala.models.NotFoundException;
import com.web5b.guatemala.web5b_guatemala.repositories.ITaskTypeRepository;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class TaskTypeService implements ITaskTypeService {

  private final ITaskTypeRepository taskTypeRepository;
  private final ITaskTypeMapper taskTypeMapper;

  @Override
  public Mono<TaskType> create(CreateTaskTypeDto dto) {
    TaskType dtoVerified = taskTypeMapper.dtoToEntity(dto);
    return taskTypeRepository.save(dtoVerified);
  }

  @Override
  public Mono<TaskType> findById(Long id) {
    return taskTypeRepository.findById(id).switchIfEmpty(Mono.error(new NotFoundException("TaskType not found")));
  }

  @Override
  public Mono<TaskType> update(Long id, UpdateTaskTypeDto dto) {
    return findById(id)
      .flatMap(taskType -> {
        TaskType dtoVerified = taskTypeMapper.dtoToEntity(dto);
        dtoVerified.setId(taskType.getId());
        return taskTypeRepository.save(dtoVerified);
      });
  }

  @Override
  public Mono<Void> delete(Long id) {
    return findById(id)
      .flatMap(taskType -> taskTypeRepository.delete(taskType));
  }

  @Override
  public Flux<TaskType> findAll() {
    return taskTypeRepository.findAllEnabled();
  }

  @Override
  public Mono<TaskType> getDtoVerified(TaskType dto) {
    return Mono.just(dto);
  }

}
