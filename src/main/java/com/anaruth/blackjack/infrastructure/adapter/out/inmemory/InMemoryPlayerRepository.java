package com.anaruth.blackjack.infrastructure.adapter.out.inmemory;

import com.anaruth.blackjack.domain.model.player.Player;
import com.anaruth.blackjack.domain.port.out.PlayerRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class InMemoryPlayerRepository implements PlayerRepository {

    private final Map<String, Player> storage = new ConcurrentHashMap<>();

    @Override
    public Mono<Player> save(Player player) {
        storage.put(player.getId().getValue(), player);
        return Mono.just(player);
    }

    @Override
    public Mono<Player> findById(String playerId) {
        Player player = storage.get(playerId);
        return player != null ? Mono.just(player) : Mono.empty();
    }

    @Override
    public Mono<Player> update(Player player) {
        storage.put(player.getId().getValue(), player);
        return Mono.just(player);
    }
        @Override
        public Flux<Player> findAll() {
            return Flux.fromIterable(storage.values());
    }
}