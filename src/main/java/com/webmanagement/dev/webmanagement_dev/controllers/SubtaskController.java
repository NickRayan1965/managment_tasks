package com.webmanagement.dev.webmanagement_dev.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.webmanagement.dev.webmanagement_dev.dtos.req.create.CreateSubtaskDto;
import com.webmanagement.dev.webmanagement_dev.entities.SubTask;
import com.webmanagement.dev.webmanagement_dev.entities.User;
import com.webmanagement.dev.webmanagement_dev.security.decorators.GetRequestUser;
import com.webmanagement.dev.webmanagement_dev.services.ISubtaskService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import org.springframework.web.bind.annotation.PostMapping;
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
    @RequestParam("taskId") Long taskId,
    @RequestBody @Valid CreateSubtaskDto dto,
    @GetRequestUser User requester) {
    return subtaskService.create(dto, taskId, requester.getId());
  }
  
}
