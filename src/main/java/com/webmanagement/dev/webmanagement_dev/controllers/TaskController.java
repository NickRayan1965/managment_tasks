package com.webmanagement.dev.webmanagement_dev.controllers;

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

import com.webmanagement.dev.webmanagement_dev.dtos.req.create.CreateTaskDto;
import com.webmanagement.dev.webmanagement_dev.dtos.req.update.UpdateTaskDto;
import com.webmanagement.dev.webmanagement_dev.dtos.res.TaskDto;
import com.webmanagement.dev.webmanagement_dev.entities.User;
import com.webmanagement.dev.webmanagement_dev.security.decorators.GetRequestUser;
import com.webmanagement.dev.webmanagement_dev.services.TaskService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@SecurityRequirement(name = "BearerAuth")
@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {
  private final TaskService taskService;

  @GetMapping("/{id}")
  public Mono<TaskDto> findOneById(@PathVariable(name = "id") Long id, @GetRequestUser User requester) {
    return taskService.findOneById(id, requester.getId());
  }

  @GetMapping
  public Flux<TaskDto> findAll(@GetRequestUser User requester) {
    return taskService.findAllEnabledByUserId(requester.getId());
  }

  @PostMapping
  public Mono<TaskDto> create(@RequestBody @Valid CreateTaskDto dto, @GetRequestUser User requester) {    
    return taskService.create(dto, requester.getId());
  }

  @PutMapping("/{id}")
  public Mono<TaskDto> update(@PathVariable(name = "id") Long id, @RequestBody @Valid UpdateTaskDto dto,
      @GetRequestUser User requester) {
    return taskService.update(id, dto, requester.getId());
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(code = HttpStatus.NO_CONTENT)
  public Mono<Void> delete(@PathVariable(name = "id") Long id, @GetRequestUser User requester) {
    return taskService.delete(id, requester.getId());
  }

}
