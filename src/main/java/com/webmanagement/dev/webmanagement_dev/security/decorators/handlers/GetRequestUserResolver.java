package com.webmanagement.dev.webmanagement_dev.security.decorators.handlers;

import org.springframework.core.MethodParameter;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.BindingContext;
import org.springframework.web.reactive.result.method.HandlerMethodArgumentResolver;
import org.springframework.web.server.ServerWebExchange;

import com.webmanagement.dev.webmanagement_dev.security.decorators.GetRequestUser;

import reactor.core.publisher.Mono;

@Component
public class GetRequestUserResolver implements HandlerMethodArgumentResolver {

  @Override
  public boolean supportsParameter(MethodParameter parameter) {
    return parameter.hasParameterAnnotation(GetRequestUser.class);
  }

  @Override
  public Mono<Object> resolveArgument(MethodParameter parameter, BindingContext bindingContext,
      ServerWebExchange exchange) {
    return ReactiveSecurityContextHolder.getContext()
        .map(securityContext -> securityContext.getAuthentication())
        .map(authentication -> authentication.getDetails())
        .switchIfEmpty(Mono.error(new Exception("User not found in context")));
  }

}
