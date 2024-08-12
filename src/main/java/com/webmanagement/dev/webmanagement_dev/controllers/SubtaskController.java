package com.webmanagement.dev.webmanagement_dev.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.webmanagement.dev.webmanagement_dev.dtos.req.create.CreateSubtaskDto;
import com.webmanagement.dev.webmanagement_dev.dtos.req.update.UpdateSubtaskDto;
import com.webmanagement.dev.webmanagement_dev.entities.SubTask;
import com.webmanagement.dev.webmanagement_dev.entities.User;
import com.webmanagement.dev.webmanagement_dev.security.decorators.GetRequestUser;
import com.webmanagement.dev.webmanagement_dev.services.ISubtaskService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@SecurityRequirement(name = "BearerAuth")
@Tag(name = "Subtasks")
@RestController
@RequestMapping("/subtasks")
@RequiredArgsConstructor
public class SubtaskController {
  private final ISubtaskService subtaskService;

  @PostMapping
  public Mono<SubTask> create(
      @RequestParam() Long taskId,
      @RequestBody @Valid CreateSubtaskDto dto,
      @GetRequestUser User requester) {
    return subtaskService.create(dto, taskId, requester.getId());
  }

  @PutMapping("/{id}")
  public Mono<SubTask> update(
      @PathVariable() Long id,
      @RequestBody @Valid UpdateSubtaskDto dto,
      @GetRequestUser User requester) {
    return subtaskService.updateById(id, dto, requester.getId());
  }

  @DeleteMapping("/{id}")
  public Mono<Void> delete(
      @PathVariable() Long id,
      @GetRequestUser User requester) {
    return subtaskService.delete(id, requester.getId());
  }

}
