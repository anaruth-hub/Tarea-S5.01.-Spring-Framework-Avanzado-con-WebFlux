package com.anaruth.blackjack.infrastructure.adapter.in.web.response;

import java.util.List;

public record GameResponse(
        String id,
        String status,
        String playerId,
        String playerName,
        List<String> playerCard,
        List<String> dealerCard,
        int playerScore,
        int dealerScore
) {
}
