package com.webmanagement.dev.webmanagement_dev.repositories;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

import com.webmanagement.dev.webmanagement_dev.entities.SubTask;

import reactor.core.publisher.Mono;


public interface ISubtaskRepository extends R2dbcRepository<SubTask, Long>{

  @Query("select max(ordernumber) from subtasks st where st.task_id = $1")
  Mono<Integer> getLastItemNumberOfSubtasksByTaskId(Long taskId);

  @Query("select * from subtasks st left join tasks t on t.id = st.task_id left join users u on u.id = t.user_id where st.id = $1 and u.id = $2")
  Mono<SubTask> findByIdAndUserTaskId(Long id, Long userId);

}
