package com.web5b.guatemala.web5b_guatemala.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web5b.guatemala.web5b_guatemala.dtos.create.CreateTaskDto;
import com.web5b.guatemala.web5b_guatemala.dtos.res.TaskDto;
import com.web5b.guatemala.web5b_guatemala.services.TaskService;

import lombok.RequiredArgsConstructor;
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

  @PostMapping
  public Mono<TaskDto> create(@RequestBody CreateTaskDto dto) {
    return taskService.create(dto);
  }

  
}
