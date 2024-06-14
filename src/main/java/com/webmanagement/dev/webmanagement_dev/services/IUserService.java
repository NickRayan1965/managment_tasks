package com.webmanagement.dev.webmanagement_dev.services;

import com.webmanagement.dev.webmanagement_dev.dtos.req.create.CreateUserDto;
import com.webmanagement.dev.webmanagement_dev.dtos.req.update.UpdateUserDto;
import com.webmanagement.dev.webmanagement_dev.dtos.res.UserDto;
import com.webmanagement.dev.webmanagement_dev.entities.User;


public interface IUserService extends IBaseService<User, UserDto, CreateUserDto, UpdateUserDto> {
}
