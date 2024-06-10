package com.web5b.guatemala.web5b_guatemala.services;

import org.springframework.stereotype.Service;

import com.web5b.guatemala.web5b_guatemala.dtos.create.CreateTaskTypeDto;
import com.web5b.guatemala.web5b_guatemala.dtos.res.TaskTypeDto;
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
  public Mono<TaskTypeDto> create(CreateTaskTypeDto dto) {
    return getDtoVerified(taskTypeMapper.dtoToEntity(dto))
        .flatMap(taskTypeRepository::save)
        .map(taskTypeMapper::toDto);
  }

  @Override
  public Mono<TaskTypeDto> findOneById(Long id) {
    return taskTypeRepository.findById(id)
        .map(taskTypeMapper::toDto)
        .switchIfEmpty(Mono.error(new NotFoundException("TaskType not found")));
  }

  @Override
  public Mono<TaskTypeDto> update(Long id, UpdateTaskTypeDto dto) {
    return findOneById(id)
        .flatMap(taskType -> getDtoVerified(taskTypeMapper.dtoToEntity(dto)))
        .flatMap(taskTypeRepository::save)
        .map(taskTypeMapper::toDto);
  }

  @Override
  public Mono<Void> delete(Long id) {
    return findOneById(id)
        .map(taskTypeMapper::dtoToEntity)
        .flatMap(taskType -> {
          taskType.setEnabled(false);
          return taskTypeRepository.save(taskType);
        })
        .then();
  }

  @Override
  public Flux<TaskTypeDto> findAll() {
    return taskTypeRepository.findAllEnabled().map(taskTypeMapper::toDto);
  }

  @Override
  public Mono<TaskType> getDtoVerified(TaskType dto) {
    return Mono.just(dto);
  }

}
