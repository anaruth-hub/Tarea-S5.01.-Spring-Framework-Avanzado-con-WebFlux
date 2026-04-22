package com.anaruth.blackjack.application.port.in;

import com.anaruth.blackjack.domain.model.player.Player;
import reactor.core.publisher.Flux;

public interface GetRankingUseCase {
    Flux<Player> getRanking();
}
