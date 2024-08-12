package com.webmanagement.dev.webmanagement_dev.services;

import com.webmanagement.dev.webmanagement_dev.dtos.req.create.CreateSubtaskDto;
import com.webmanagement.dev.webmanagement_dev.entities.SubTask;

import reactor.core.publisher.Mono;

public interface ISubtaskService {
  Mono<SubTask> create(CreateSubtaskDto dto, Long taskId, Long userId);

  Mono<SubTask> getDtoVerified(SubTask dto);
}
