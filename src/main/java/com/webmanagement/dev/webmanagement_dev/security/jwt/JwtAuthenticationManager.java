package com.webmanagement.dev.webmanagement_dev.security.jwt;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.webmanagement.dev.webmanagement_dev.repositories.IUserRepository;
import com.webmanagement.dev.webmanagement_dev.security.errors.JwtAuthenticationException;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationManager implements ReactiveAuthenticationManager {

  private final JwtProvider jwtProvider;
  private final IUserRepository userRepository;

  @Override
  public Mono<Authentication> authenticate(Authentication authentication) {
    if (authentication == null || authentication.getCredentials() == null) {
      return Mono.empty();
    }
    return Mono.just(authentication)
        .map(a -> a.getCredentials().toString())
        .filter(jwtProvider::validate)
        .switchIfEmpty(Mono.error(new JwtAuthenticationException("Invalid JWT token")))
        .map(auth -> jwtProvider.getClaims(auth))
        .flatMap(claims -> {
          return Mono.just(claims.getSubject())
              .flatMap(userRepository::findOneByUsername)
              .switchIfEmpty(Mono.error(new UsernameNotFoundException("User not found")))
              .filter(user -> user.isEnabled())
              .switchIfEmpty(Mono.error(new DisabledException("User is disabled talk to admin to enable your account")))
              .map(user -> {
                AbstractAuthenticationToken authenticationResponse = new UsernamePasswordAuthenticationToken(user.getUsername(), null,
                    user.getAuthorities());
                authenticationResponse.setDetails(user);
                return authenticationResponse;
              });
        })

    ;
  }
}
