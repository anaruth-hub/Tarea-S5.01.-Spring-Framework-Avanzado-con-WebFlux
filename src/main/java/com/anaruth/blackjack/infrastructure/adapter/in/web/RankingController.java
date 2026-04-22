package com.anaruth.blackjack.infrastructure.adapter.in.web;

import com.anaruth.blackjack.application.port.in.GetRankingUseCase;
import com.anaruth.blackjack.domain.model.player.Player;
import com.anaruth.blackjack.infrastructure.adapter.in.web.response.PlayerRankingResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@Tag(name = "Ranking", description = "Ranking operations")
@RestController
@RequestMapping("/ranking")
public class RankingController {

    private final GetRankingUseCase getRankingUseCase;

    public RankingController(GetRankingUseCase getRankingUseCase) {
        this.getRankingUseCase = getRankingUseCase;
    }

    @Operation(summary = "Get player ranking")
    @GetMapping
    public Flux<PlayerRankingResponse> getRanking() {
        return getRankingUseCase.getRanking()
                .map(this::toResponse);
    }

    private PlayerRankingResponse toResponse(Player player) {
        return new PlayerRankingResponse(
                player.getId().getValue(),
                player.getName().getValue(),
                player.getWins(),
                player.getLosses(),
                player.getDraws(),
                player.calculateRankingScore()
        );
    }
}