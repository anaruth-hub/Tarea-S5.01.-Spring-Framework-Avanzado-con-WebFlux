package com.anaruth.blackjack.application.port.in;

import com.anaruth.blackjack.application.dto.PlayGameCommand;
import com.anaruth.blackjack.domain.model.game.Game;
import reactor.core.publisher.Mono;

public interface PlayGameUseCase {
    Mono<Game> playGame(PlayGameCommand command);
}
