package com.anaruth.blackjack.application.service;

import com.anaruth.blackjack.application.port.in.GetGameUseCase;
import com.anaruth.blackjack.domain.exception.GameNotFoundException;
import com.anaruth.blackjack.domain.model.game.Game;
import com.anaruth.blackjack.domain.port.out.GameRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class GetGameService implements GetGameUseCase {

    private final GameRepository gameRepository;

    public GetGameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Override
    public Mono<Game> getGameById(String gameId) {
        validateGameId(gameId);

        return gameRepository.findById(gameId)
                .switchIfEmpty(Mono.error(new GameNotFoundException(gameId)));
    }

    private void validateGameId(String gameId) {
        if (gameId == null || gameId.isBlank()) {
            throw new IllegalArgumentException("Game id cannot be null or blank");
        }
    }
}