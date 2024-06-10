package com.web5b.guatemala.web5b_guatemala.entities.mappers;

import java.util.List;

import com.web5b.guatemala.web5b_guatemala.dtos.create.CreateUserDto;
import com.web5b.guatemala.web5b_guatemala.dtos.res.UserDto;
import com.web5b.guatemala.web5b_guatemala.dtos.update.UpdateUserDto;
import com.web5b.guatemala.web5b_guatemala.entities.User;

public interface IUserMapper {
  User dtoToEntity(CreateUserDto dto);
  User dtoToEntity(UpdateUserDto dto);
  User dtoToEntity(UserDto dto);
  UserDto toDto(User entity);
  List<UserDto> toDto(List<User> entities);
}
