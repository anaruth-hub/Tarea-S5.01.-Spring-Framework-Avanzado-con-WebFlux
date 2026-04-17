package com.anaruth.blackjack.domain.port.out;

import com.anaruth.blackjack.domain.model.game.Game;
import reactor.core.publisher.Mono;

public interface GameRepository {
    Mono<Game> save(Game game);
    Mono<Game> findById(String gameId);
    Mono<Void> deleteById(String gameId);
}
