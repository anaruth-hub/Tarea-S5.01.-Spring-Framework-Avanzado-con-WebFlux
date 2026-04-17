package com.anaruth.blackjack.application.port.in;

import com.anaruth.blackjack.application.dto.CreateGameCommand;
import com.anaruth.blackjack.domain.model.game.Game;
import reactor.core.publisher.Mono;

public interface CreateGameUseCase {
    Mono<Game> createGame(CreateGameCommand command);
}
