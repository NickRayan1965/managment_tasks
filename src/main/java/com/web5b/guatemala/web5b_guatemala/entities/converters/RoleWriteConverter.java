package com.web5b.guatemala.web5b_guatemala.entities.converters;

import org.springframework.data.convert.WritingConverter;
import org.springframework.core.convert.converter.Converter;
import com.web5b.guatemala.web5b_guatemala.entities.Role;


@WritingConverter
public class RoleWriteConverter implements Converter<Role, String> {
  @Override
  public String convert(Role source) {
    return source.name();
  }
}
