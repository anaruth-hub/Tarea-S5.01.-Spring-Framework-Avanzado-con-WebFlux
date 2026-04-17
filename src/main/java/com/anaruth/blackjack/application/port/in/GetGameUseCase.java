package com.anaruth.blackjack.application.port.in;

import com.anaruth.blackjack.domain.model.game.Game;
import reactor.core.publisher.Mono;

public interface GetGameUseCase {
    Mono<Game> getGameById(String gameId);
}
