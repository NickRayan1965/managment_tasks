package com.webmanagement.dev.webmanagement_dev.services;

import com.webmanagement.dev.webmanagement_dev.dtos.req.create.CreateSubtaskDto;
import com.webmanagement.dev.webmanagement_dev.dtos.req.update.UpdateSubtaskDto;
import com.webmanagement.dev.webmanagement_dev.entities.SubTask;

import reactor.core.publisher.Mono;

public interface ISubtaskService {
  Mono<SubTask> create(CreateSubtaskDto dto, Long taskId, Long userId);

  Mono<SubTask> updateById(Long id, UpdateSubtaskDto dto, Long userId);

  Mono<SubTask> findOneById(Long id, Long userId);

  Mono<Void> delete(Long id, Long userId);

  Mono<SubTask> getDtoVerified(SubTask dto);
}
