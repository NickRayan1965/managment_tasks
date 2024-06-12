package com.web5b.guatemala.web5b_guatemala.security.helpers;

import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class DataBufferWriter {
    private final ObjectMapper objectMapper;

    public <T> Mono<Void> write(ServerHttpResponse httpResponse, T object) {
        return httpResponse
            .writeWith(Mono.fromSupplier(() -> {
                DataBufferFactory bufferFactory = httpResponse.bufferFactory();
                try {
                    return bufferFactory.wrap(objectMapper.writeValueAsBytes(object));
                } catch (Exception ex) {
                    return bufferFactory.wrap(new byte[0]);
                }
            }));
    }
}