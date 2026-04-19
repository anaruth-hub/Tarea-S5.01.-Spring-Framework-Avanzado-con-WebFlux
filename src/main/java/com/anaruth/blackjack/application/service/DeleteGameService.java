package com.anaruth.blackjack.application.service;

import com.anaruth.blackjack.application.port.in.DeleteGameUseCase;
import com.anaruth.blackjack.domain.exception.GameNotFoundException;
import com.anaruth.blackjack.domain.port.out.GameRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class DeleteGameService implements DeleteGameUseCase {

    private final GameRepository gameRepository;

    public DeleteGameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Override
    public Mono<Void> deleteGameById(String gameId) {
        validateGameId(gameId);

        return gameRepository.findById(gameId)
                .switchIfEmpty(Mono.error(new GameNotFoundException(gameId)))
                .then(gameRepository.deleteById(gameId));
    }

    private void validateGameId(String gameId) {
        if (gameId == null || gameId.isBlank()) {
            throw new IllegalArgumentException("Game id cannot be null or blank");
        }
    }
}