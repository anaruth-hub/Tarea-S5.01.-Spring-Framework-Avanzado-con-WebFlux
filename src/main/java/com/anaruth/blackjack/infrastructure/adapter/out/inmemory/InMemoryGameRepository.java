package com.anaruth.blackjack.infrastructure.adapter.out.inmemory;

import com.anaruth.blackjack.domain.model.game.Game;
import com.anaruth.blackjack.domain.port.out.GameRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class InMemoryGameRepository implements GameRepository {

    private final Map<String, Game> storage = new ConcurrentHashMap<>();

    @Override
    public Mono<Game> save(Game game) {
        storage.put(game.getId(), game);
        return Mono.just(game);
    }

    @Override
    public Mono<Game> findById(String gameId) {
        Game game = storage.get(gameId);
        return game != null ? Mono.just(game) : Mono.empty();
    }

    @Override
    public Mono<Void> deleteById(String gameId) {
        storage.remove(gameId);
        return Mono.empty();
    }
}