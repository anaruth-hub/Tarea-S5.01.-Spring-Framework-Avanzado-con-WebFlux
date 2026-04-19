package com.anaruth.blackjack.infrastructure.adapter.in.web.response;

import java.util.List;

public record GameResponse(
        String id,
        String status,
        List<String> playerCard,
        List<String> dealerCard,
        int playerScore,
        int dealerScore
) {
}
