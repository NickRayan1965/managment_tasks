package com.webmanagement.dev.webmanagement_dev.controllers;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.webmanagement.dev.webmanagement_dev.dtos.req.create.CreateTaskTypeDto;
import com.webmanagement.dev.webmanagement_dev.dtos.req.update.UpdateTaskTypeDto;
import com.webmanagement.dev.webmanagement_dev.dtos.res.TaskTypeDto;
import com.webmanagement.dev.webmanagement_dev.entities.Role;
import com.webmanagement.dev.webmanagement_dev.security.decorators.HasAuthority;
import com.webmanagement.dev.webmanagement_dev.services.ITaskTypeService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@SecurityRequirement(name = "BearerAuth")
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

  @HasAuthority(Role.ADMIN)
  @PostMapping
  public Mono<TaskTypeDto> create(@RequestBody CreateTaskTypeDto dto) {
    return taskTypeService.create(dto);
  }

  @HasAuthority(Role.ADMIN)
  @PutMapping("/{id}")
  public Mono<TaskTypeDto> update(@PathVariable(name = "id") Long id, @RequestBody UpdateTaskTypeDto dto) {
    return taskTypeService.update(id, dto);
  }

  @HasAuthority(Role.ADMIN)
  @DeleteMapping("/{id}")
  public Mono<Void> delete(@PathVariable(name = "id") Long id) {
    return taskTypeService.delete(id);
  }
}
