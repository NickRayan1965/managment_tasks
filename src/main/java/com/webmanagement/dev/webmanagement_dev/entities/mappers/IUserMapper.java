package com.webmanagement.dev.webmanagement_dev.entities.mappers;

import java.util.List;

import com.webmanagement.dev.webmanagement_dev.dtos.req.create.CreateUserDto;
import com.webmanagement.dev.webmanagement_dev.dtos.req.update.UpdateUserDto;
import com.webmanagement.dev.webmanagement_dev.dtos.res.UserDto;
import com.webmanagement.dev.webmanagement_dev.entities.User;

public interface IUserMapper {
  User dtoToEntity(CreateUserDto dto);
  User dtoToEntity(UpdateUserDto dto);
  User dtoToEntity(UserDto dto);
  User toEntity(CreateUserDto createUserDto);
  UserDto toDto(User entity);
  List<UserDto> toDto(List<User> entities);
}
