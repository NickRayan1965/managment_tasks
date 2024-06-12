package com.web5b.guatemala.web5b_guatemala.security.aspects;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.web5b.guatemala.web5b_guatemala.entities.Role;
import com.web5b.guatemala.web5b_guatemala.models.ForbiddenException;
import com.web5b.guatemala.web5b_guatemala.security.decorators.HasAnyAuthority;
import com.web5b.guatemala.web5b_guatemala.security.decorators.HasAuthority;
import com.web5b.guatemala.web5b_guatemala.security.services.CustomSecurityService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Aspect
@Component
@RequiredArgsConstructor
public class HasAuthoritiesAspect {
  private final CustomSecurityService customSecurityService;

  @Around("@within(hasAuthority)")
  public Mono<?> checkAuthority(ProceedingJoinPoint joinPoint, HasAuthority hasAuthority) {
    return checkAuthorityHandler(joinPoint, hasAuthority);
  }

  @Around("@annotation(hasAuthority) && args(..)")
  public Mono<?> checkAuthorityMethod(ProceedingJoinPoint joinPoint, HasAuthority hasAuthority) {
    return checkAuthorityHandler(joinPoint, hasAuthority);
  }

  @Around("@annotation(hasAnyAuthority) && args(..)")
  public Mono<?> checkAnyAuthority(ProceedingJoinPoint joinPoint, HasAnyAuthority hasAnyAuthority) {
    return checkAnyAuthorityHandler(joinPoint, hasAnyAuthority);
  }

  @Around("@within(hasAnyAuthority)")
  public Mono<?> checkAnyAuthorityMethod(ProceedingJoinPoint joinPoint, HasAnyAuthority hasAnyAuthority) {
    return checkAnyAuthorityHandler(joinPoint, hasAnyAuthority);
  }

  private Mono<?> checkAuthorityHandler(ProceedingJoinPoint joinPoint, HasAuthority hasAuthority) {
    String role = hasAuthority.value().name();
    return Mono.just(hasAuthority.value())
        .flatMap(customSecurityService::hasAuthority)
        .flatMap(canAccess -> {
          if (canAccess) {
            try {
              return (Mono<?>) joinPoint.proceed();
            } catch (Throwable e) {
              return Mono.error(e);
            }
          } else {
            return Mono.error(new ForbiddenException("You don't have the required role: " + role));
          }
        });
  }

  private Mono<?> checkAnyAuthorityHandler(ProceedingJoinPoint joinPoint, HasAnyAuthority hasAnyAuthority) {
    Role[] rolesEnum = hasAnyAuthority.value();
    String[] roles = Arrays.stream(rolesEnum).map(Enum::name).toArray(String[]::new);
    System.out.println("Checking any authority");
    System.out.println(roles[0]);
    return Mono.just(rolesEnum)
        .flatMap(customSecurityService::hasAnyAuthority)
        .flatMap(canAccess -> {
          if (canAccess) {
            try {
              System.out.println("Proceeding");
              return (Mono<?>) joinPoint.proceed();
            } catch (Throwable e) {
              return Mono.error(e);
            }
          } else {
            return Mono
                .error(new ForbiddenException("You don't have any of the required roles: " + String.join(", ", roles)));
          }
        });
  }
}
