package com.anaruth.blackjack.infraestructure.adapter.in.web;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.awt.*;

@RestController
public class HealthController {
    @GetMapping(value = "/health", produces = MediaType.TEXT_PLAIN_VALUE)
    public Mono<String> health () {
        return Mono.just("Blackjack API is running");
    }
}
