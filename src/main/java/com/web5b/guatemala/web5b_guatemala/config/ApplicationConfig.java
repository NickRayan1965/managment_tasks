package com.web5b.guatemala.web5b_guatemala.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;

@Configuration
@EnableAspectJAutoProxy
@PropertySource("classpath:security.properties")public class ApplicationConfig {
    
}
