package com.anaruth.blackjack.infrastructure.adapter.in.web.request;

import com.anaruth.blackjack.domain.model.game.PlayType;

public record PlayGameRequest(PlayType playType) {
}
