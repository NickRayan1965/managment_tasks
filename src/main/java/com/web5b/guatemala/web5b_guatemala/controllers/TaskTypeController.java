package com.web5b.guatemala.web5b_guatemala.controllers;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web5b.guatemala.web5b_guatemala.dtos.req.create.CreateTaskTypeDto;
import com.web5b.guatemala.web5b_guatemala.dtos.req.update.UpdateTaskTypeDto;
import com.web5b.guatemala.web5b_guatemala.dtos.res.TaskTypeDto;
import com.web5b.guatemala.web5b_guatemala.services.ITaskTypeService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/task-types")
@RequiredArgsConstructor
public class TaskTypeController {
  private final ITaskTypeService taskTypeService;

  @GetMapping
  public Flux<TaskTypeDto> findAll() {
    return taskTypeService.findAll();
  }

  @GetMapping("/{id}")
  public Mono<TaskTypeDto> findById(@PathVariable(name = "id") Long id) {
    return taskTypeService.findOneById(id);
  }

  @PostMapping
  public Mono<TaskTypeDto> create(@RequestBody CreateTaskTypeDto dto) {
    return taskTypeService.create(dto);
  }

  @PutMapping("/{id}")
  public Mono<TaskTypeDto> update(@PathVariable(name = "id") Long id, @RequestBody UpdateTaskTypeDto dto) {
    return taskTypeService.update(id, dto);
  }

  @DeleteMapping("/{id}")
  public Mono<Void> delete(@PathVariable(name = "id") Long id) {
    return taskTypeService.delete(id);
  }
}
