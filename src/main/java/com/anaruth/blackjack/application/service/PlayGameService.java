package com.anaruth.blackjack.application.service;

import com.anaruth.blackjack.application.dto.PlayGameCommand;
import com.anaruth.blackjack.application.port.in.PlayGameUseCase;
import com.anaruth.blackjack.domain.exception.GameNotFoundException;
import com.anaruth.blackjack.domain.model.game.Game;
import com.anaruth.blackjack.domain.port.out.GameRepository;
import reactor.core.publisher.Mono;

public class PlayGameService implements PlayGameUseCase {

    private final GameRepository gameRepository;

    public PlayGameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Override
    public Mono<Game> playGame(PlayGameCommand command) {
        validateCommand(command);

        return gameRepository.findById(command.gameId())
                .switchIfEmpty(Mono.error(new GameNotFoundException(command.gameId())))
                .flatMap(game -> {
                    game.play(command.playType());
                    return gameRepository.save(game);
                });
    }

    private void validateCommand(PlayGameCommand command) {
        if (command == null) {
            throw new IllegalArgumentException("Play game command cannot be null");
        }

        if (command.gameId() == null || command.gameId().isBlank()) {
            throw new IllegalArgumentException("Game id cannot be null or blank");
        }

        if (command.playType() == null) {
            throw new IllegalArgumentException("Play type cannot be null");
        }
    }
}