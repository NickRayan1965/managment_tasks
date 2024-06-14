package com.webmanagement.dev.webmanagement_dev.repositories;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

import com.webmanagement.dev.webmanagement_dev.entities.TaskType;

import reactor.core.publisher.Flux;

public interface ITaskTypeRepository extends R2dbcRepository<TaskType, Long> {
  @Query("select * from task_types where enabled = true")
  Flux<TaskType> findAllEnabled(); 
}
