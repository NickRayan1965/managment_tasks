package com.web5b.guatemala.web5b_guatemala.config;
import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

import com.web5b.guatemala.web5b_guatemala.entities.converters.RoleWriteConverter;
import com.web5b.guatemala.web5b_guatemala.entities.converters.RoleReadConverter;

import io.r2dbc.spi.ConnectionFactory;
import lombok.RequiredArgsConstructor;

@Configuration
@EnableR2dbcRepositories
@RequiredArgsConstructor
public class R2dbcConfig extends AbstractR2dbcConfiguration  {
  private final ConnectionFactory connectionFactory;
  @Override
  public ConnectionFactory connectionFactory() {
    return connectionFactory;
  }
  // @Override
  // protected List<Object> getCustomConverters() {
  //   return Arrays.asList(new RoleReadConverter(), new RoleWriteConverter());
  // }
  

  
}
