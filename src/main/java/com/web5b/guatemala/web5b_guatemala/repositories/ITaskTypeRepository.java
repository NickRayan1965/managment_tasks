package com.web5b.guatemala.web5b_guatemala.repositories;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

import com.web5b.guatemala.web5b_guatemala.entities.TaskType;

import reactor.core.publisher.Flux;

public interface ITaskTypeRepository extends R2dbcRepository<TaskType, Long> {
  @Query("select * from task_types where enabled = true")
  Flux<TaskType> findAllEnabled(); 
}
