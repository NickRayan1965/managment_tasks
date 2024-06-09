package com.web5b.guatemala.web5b_guatemala.services;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IBaseService<EntityClass, Dto, CreateDto, UpdateDto> {
  Mono<Dto> create(CreateDto dto);
  Mono<Dto> findOneById(Long id);
  Mono<Dto> update(Long id, UpdateDto dto);
  Mono<Void> delete(Long id);
  Flux<Dto> findAll();
  Mono<EntityClass> getDtoVerified(EntityClass dto);
}
