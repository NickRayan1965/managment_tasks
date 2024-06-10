package com.web5b.guatemala.web5b_guatemala.entities.mappers;

import java.util.List;

import org.springframework.stereotype.Component;

import com.web5b.guatemala.web5b_guatemala.dtos.create.CreateUserDto;
import com.web5b.guatemala.web5b_guatemala.dtos.res.UserDto;
import com.web5b.guatemala.web5b_guatemala.dtos.update.UpdateUserDto;
import com.web5b.guatemala.web5b_guatemala.entities.User;

@Component
public class UserMapper implements IUserMapper {

  @Override
  public User dtoToEntity(CreateUserDto dto) {
    return User.builder()
        .username(dto.getUsername())
        .password(dto.getPassword())
        .role(dto.getRole())
        .enabled(true)
        .build();
  }

  @Override
  public User dtoToEntity(UpdateUserDto dto) {
    return User.builder()
        .username(dto.getUsername())
        .password(dto.getPassword())
        .role(dto.getRole())
        .enabled(dto.getEnabled())
        .build();
  }

  @Override
  public UserDto toDto(User entity) {
    return UserDto.builder()
        .id(entity.getId())
        .username(entity.getUsername())
        .role(entity.getRole())
        .enabled(entity.getEnabled())
        .build();
  }

  @Override
  public List<UserDto> toDto(List<User> entities) {
    return entities.stream().map(this::toDto).toList();
  }

  @Override
  public User dtoToEntity(UserDto dto) {
    return User.builder()
        .id(dto.getId())
        .username(dto.getUsername())
        .role(dto.getRole())
        .enabled(dto.getEnabled())
        .build();
  }

}
