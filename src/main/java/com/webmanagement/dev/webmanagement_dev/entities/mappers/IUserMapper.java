package com.webmanagement.dev.webmanagement_dev.entities.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.webmanagement.dev.webmanagement_dev.dtos.req.create.CreateUserDto;
import com.webmanagement.dev.webmanagement_dev.dtos.req.update.UpdateUserDto;
import com.webmanagement.dev.webmanagement_dev.dtos.res.UserDto;
import com.webmanagement.dev.webmanagement_dev.entities.User;

@Mapper(componentModel = "spring")
public interface IUserMapper {
  // User dtoToEntity(CreateUserDto dto);
  default User dtoToEntity(CreateUserDto dto) {
    User entity = new User();
    mergeToEntity(dto, entity);
    return entity;
  }

  // User dtoToEntity(UpdateUserDto dto);
  default User dtoToEntity(UpdateUserDto dto) {
    User entity = new User();
    mergeToEntity(dto, entity);
    return entity;
  }

  // User dtoToEntity(UserDto dto);
  default User dtoToEntity(UserDto dto) {
    User entity = new User();
    setDtoOnEntity(dto, entity);
    return entity;
  }  

  // UserDto toDto(User entity);
  default UserDto toDto(User entity) {
    UserDto dto = new UserDto();
    setEntityOnDto(entity, dto);
    return dto;
  }

  // List<UserDto> toDto(List<User> entities);
  default Iterable<UserDto> toDto(Iterable<User> entities) {
    return ((List<User>) entities).stream().map(this::toDto).toList();
  }

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "enabled", defaultValue = "true", ignore = true)
  @Mapping(target = "authorities", ignore = true)
  void mergeToEntity(CreateUserDto dto, @MappingTarget User entity);

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "authorities", ignore = true)
  void mergeToEntity(UpdateUserDto dto, @MappingTarget User entity);

  @Mapping(target = "password", ignore = true)
  @Mapping(target = "authorities", ignore = true)
  void setDtoOnEntity(UserDto dto, @MappingTarget User entity);

  void setEntityOnDto(User entity, @MappingTarget UserDto dto);
}
