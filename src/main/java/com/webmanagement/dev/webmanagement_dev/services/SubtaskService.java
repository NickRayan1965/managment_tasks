package com.webmanagement.dev.webmanagement_dev.services;

import org.springframework.stereotype.Service;

import com.webmanagement.dev.webmanagement_dev.dtos.req.create.CreateSubtaskDto;
import com.webmanagement.dev.webmanagement_dev.dtos.req.update.UpdateSubtaskDto;
import com.webmanagement.dev.webmanagement_dev.entities.SubTask;
import com.webmanagement.dev.webmanagement_dev.entities.mappers.ISubtaskMapper;
import com.webmanagement.dev.webmanagement_dev.models.NotFoundException;
import com.webmanagement.dev.webmanagement_dev.repositories.ISubtaskRepository;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class SubtaskService implements ISubtaskService {

  private final ITaskService taskService;
  private final ISubtaskMapper subtaskMapper;
  private final ISubtaskRepository subtaskRepository;

  @Override
  public Mono<SubTask> create(CreateSubtaskDto dto, Long taskId, Long userId) {
    return Mono.just(subtaskMapper.dtoToEntity(dto))
        .flatMap(entity -> {
          return taskService.findOneById(taskId, userId)
          .map(task -> entity);
        })
        .flatMap(this::getDtoVerified)// *verificacion del dto, aunque por ahora no es estricatamente necesario
        .doOnNext(subtask -> subtask.setTaskId(taskId))
        .flatMap(subtask -> {
          return subtaskRepository.getLastItemNumberOfSubtasksByTaskId(taskId) // * obtenemos el ultimo item de las subtareas de una tarea
          .switchIfEmpty(Mono.just(0))    
          .map(lastItemNumber -> {
                subtask.setOrder(lastItemNumber + 1);
                return subtask;
              });
        })
        .flatMap(subtaskRepository::save)
        ;

  }

  @Override
  public Mono<SubTask> getDtoVerified(SubTask dto) {
    return Mono.just(dto);
  }

  @Override
  public Mono<SubTask> updateById(Long id, UpdateSubtaskDto dto, Long userId) {
    return findOneById(id, userId).flatMap(subtask -> {
      return getDtoVerified(subtaskMapper.dtoToEntity(dto)).map(st -> subtask);
    })
    .doOnNext(subtask -> subtaskMapper.mergeToEntiy(dto, subtask))
    .flatMap(subtaskRepository::save)
    ;
  }

  @Override
  public Mono<SubTask> findOneById(Long id, Long userId) {
    return subtaskRepository.findByIdAndUserTaskId(id, userId)
    .switchIfEmpty(Mono.error(new NotFoundException("Task not found")));
  }

}
