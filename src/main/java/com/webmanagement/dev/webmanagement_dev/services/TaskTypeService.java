package com.webmanagement.dev.webmanagement_dev.services;

import org.springframework.stereotype.Service;

import com.webmanagement.dev.webmanagement_dev.dtos.req.create.CreateTaskTypeDto;
import com.webmanagement.dev.webmanagement_dev.dtos.req.update.UpdateTaskTypeDto;
import com.webmanagement.dev.webmanagement_dev.dtos.res.TaskTypeDto;
import com.webmanagement.dev.webmanagement_dev.entities.TaskType;
import com.webmanagement.dev.webmanagement_dev.entities.mappers.ITaskTypeMapper;
import com.webmanagement.dev.webmanagement_dev.models.NotFoundException;
import com.webmanagement.dev.webmanagement_dev.repositories.ITaskTypeRepository;

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
