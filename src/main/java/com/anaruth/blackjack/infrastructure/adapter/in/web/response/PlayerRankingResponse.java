package com.anaruth.blackjack.infrastructure.adapter.in.web.response;

public record PlayerRankingResponse(
        String playerId,
        String playerName,
        int wins,
        int losses,
        int draws,
        int score
) {
}