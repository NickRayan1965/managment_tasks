package com.web5b.guatemala.web5b_guatemala.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.web5b.guatemala.web5b_guatemala.dtos.req.create.CreateTaskDto;
import com.web5b.guatemala.web5b_guatemala.dtos.req.update.UpdateTaskDto;
import com.web5b.guatemala.web5b_guatemala.dtos.res.TaskDto;
import com.web5b.guatemala.web5b_guatemala.entities.Role;
import com.web5b.guatemala.web5b_guatemala.security.decorators.HasAuthority;
import com.web5b.guatemala.web5b_guatemala.services.TaskService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {
  private final TaskService taskService;

  @GetMapping("/{id}")
  public Mono<TaskDto> findOneById(@PathVariable(name = "id") Long id) {
    return taskService.findOneById(id);
  }

  @GetMapping
  public Flux<TaskDto> findAll() {
    return taskService.findAll();
  }

  @HasAuthority(Role.ADMIN)
  @PostMapping
  public Mono<TaskDto> create(@RequestBody CreateTaskDto dto) {
    return taskService.create(dto);
  }

  @HasAuthority(Role.ADMIN)
  @PutMapping("/{id}")
  public Mono<TaskDto> update(@PathVariable(name = "id") Long id, @RequestBody UpdateTaskDto dto) {
    return taskService.update(id, dto);
  }

  @HasAuthority(Role.ADMIN)
  @DeleteMapping("/{id}")
  @ResponseStatus(code = HttpStatus.NO_CONTENT)
  public Mono<Void> delete(@PathVariable(name = "id") Long id) {
    return taskService.delete(id);
  }

  
}
