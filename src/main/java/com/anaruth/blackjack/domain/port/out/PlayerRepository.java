package com.anaruth.blackjack.domain.port.out;

import com.anaruth.blackjack.domain.model.player.Player;
import reactor.core.publisher.Mono;

public interface PlayerRepository {
    Mono<Player> save(Player player);
    Mono<Player> findById(String playerId);
    Mono<Player> update(Player player);
}
