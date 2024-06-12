package com.web5b.guatemala.web5b_guatemala.security.services;

import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.stereotype.Service;

import com.web5b.guatemala.web5b_guatemala.entities.Role;

import reactor.core.publisher.Mono;

@Service()
public class CustomSecurityService {
  public Mono<Boolean> hasAuthority(Role role) {
    return ReactiveSecurityContextHolder.getContext()
        .map(securityContext -> securityContext.getAuthentication().getAuthorities().stream()
            .anyMatch(a -> a.getAuthority().equals(role.name())));
  }

  public Mono<Boolean> hasAnyAuthority(Role[] roles) {
    return ReactiveSecurityContextHolder.getContext()
        .map(securityContext -> securityContext.getAuthentication().getAuthorities().stream()
            .anyMatch(a -> {
              for (Role role : roles) {
                if (a.getAuthority().equals(role.name())) {
                  return true;
                }
              }
              return false;
            }));
  }

}
