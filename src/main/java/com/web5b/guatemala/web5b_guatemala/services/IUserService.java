package com.web5b.guatemala.web5b_guatemala.services;

import com.web5b.guatemala.web5b_guatemala.dtos.req.create.CreateUserDto;
import com.web5b.guatemala.web5b_guatemala.dtos.req.update.UpdateUserDto;
import com.web5b.guatemala.web5b_guatemala.dtos.res.UserDto;
import com.web5b.guatemala.web5b_guatemala.entities.User;


public interface IUserService extends IBaseService<User, UserDto, CreateUserDto, UpdateUserDto> {
}
