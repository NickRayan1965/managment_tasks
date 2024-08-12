package com.webmanagement.dev.webmanagement_dev.repositories;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

import com.webmanagement.dev.webmanagement_dev.dtos.db.TaskDbJoined;
import com.webmanagement.dev.webmanagement_dev.entities.Task;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ITaskRepository extends R2dbcRepository<Task, Long>{
  @Query("select tasks.id as task_id, tasks.name as task_name, tasks.description as task_description, tasks.type_id as task_type_id, task_types.name as task_type_name, task_types.enabled as task_type_enabled, tasks.user_id as user_id, users.username as username, users.role as user_role, users.enabled as user_enabled, tasks.enabled as task_enabled from tasks left join task_types on tasks.type_id = task_types.id left join users on tasks.user_id = users.id where tasks.id = $1")
  Mono<TaskDbJoined> findOneById(Long id);

  @Query("select tasks.id as task_id, tasks.name as task_name, tasks.description as task_description, tasks.type_id as task_type_id, task_types.name as task_type_name, task_types.enabled as task_type_enabled, tasks.user_id as user_id, users.username as username, users.role as user_role, users.enabled as user_enabled, tasks.enabled as task_enabled from tasks left join task_types on tasks.type_id = task_types.id left join users on tasks.user_id = users.id where tasks.id = $1 and tasks.user_id = $2")
  Mono<TaskDbJoined> findOneByIdAndUserId(Long id, Long userId);

  @Query("select tasks.id as task_id, tasks.name as task_name, tasks.description as task_description, tasks.type_id as task_type_id, task_types.name as task_type_name, task_types.enabled as task_type_enabled, tasks.user_id as user_id, users.username as username, users.role as user_role, users.enabled as user_enabled, tasks.enabled as task_enabled from tasks left join task_types on tasks.type_id = task_types.id left join users on tasks.user_id = users.id where tasks.enabled = true and tasks.user_id = $1")
  Flux<TaskDbJoined> findAllEnabledByUserId(Long userId);

  
}
