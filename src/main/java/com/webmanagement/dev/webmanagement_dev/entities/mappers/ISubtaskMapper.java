package com.webmanagement.dev.webmanagement_dev.entities.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.webmanagement.dev.webmanagement_dev.dtos.req.create.CreateSubtaskDto;
import com.webmanagement.dev.webmanagement_dev.entities.SubTask;

@Mapper(componentModel = "spring")
public interface ISubtaskMapper {

  default SubTask dtoToEntity(CreateSubtaskDto dto) {
    SubTask entity = new SubTask();
    mergeToEntity(dto, entity);
    return entity;
  }

  @Mapping(target="id", ignore = true)
  @Mapping(target="order", ignore = true)
  @Mapping(target="taskId", ignore = true)
  @Mapping(target = "isCompleted", constant = "true")
  void mergeToEntity(CreateSubtaskDto dto, @MappingTarget SubTask entity);
}
