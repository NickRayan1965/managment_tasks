package com.webmanagement.dev.webmanagement_dev.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;


@Configuration
@EnableAspectJAutoProxy
@PropertySource("classpath:security.properties")
public class ApplicationConfig {
}
