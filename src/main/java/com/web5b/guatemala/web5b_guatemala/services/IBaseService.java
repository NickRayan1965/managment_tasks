package com.web5b.guatemala.web5b_guatemala.services;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IBaseService<EntityClass, CreateDto, UpdateDto> {
  Mono<EntityClass> create(CreateDto dto);
  Mono<EntityClass> findById(Long id);
  Mono<EntityClass> update(Long id, UpdateDto dto);
  Mono<Void> delete(Long id);
  Flux<EntityClass> findAll();
  Mono<EntityClass> getDtoVerified(EntityClass dto);
}
