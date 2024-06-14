package com.webmanagement.dev.webmanagement_dev.security.decorators;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.webmanagement.dev.webmanagement_dev.entities.Role;



@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface HasAuthority {
  Role value();
}
