package com.web5b.guatemala.web5b_guatemala.entities.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;

import com.web5b.guatemala.web5b_guatemala.entities.Role;


@ReadingConverter
public class RoleReadConverter implements Converter<String, Role> {

  @Override
  public Role convert(String source) {
    System.out.println("RoleReadConverter.convert()");
    return Role.valueOf(source);
  }

}