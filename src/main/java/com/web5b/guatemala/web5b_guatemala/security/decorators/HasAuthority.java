package com.web5b.guatemala.web5b_guatemala.security.decorators;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.web5b.guatemala.web5b_guatemala.entities.Role;



@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface HasAuthority {
  Role value();
}
