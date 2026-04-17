package com.anaruth.blackjack.application.port.in;

import reactor.core.publisher.Mono;

public interface DeleteGameUseCase {
    Mono<Void> deleteGameById(String gameID);
}
