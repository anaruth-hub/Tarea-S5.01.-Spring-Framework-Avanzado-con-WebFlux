package com.anaruth.blackjack.application.service;

import com.anaruth.blackjack.application.port.in.GetRankingUseCase;
import com.anaruth.blackjack.domain.model.player.Player;
import com.anaruth.blackjack.domain.port.out.PlayerRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.Comparator;

@Service
public class GetRankingService implements GetRankingUseCase {

    private final PlayerRepository playerRepository;

    public GetRankingService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Override
    public Flux<Player> getRanking() {
        return playerRepository.findAll()
                .sort(Comparator.comparingInt(Player::calculateRankingScore).reversed());
    }
}