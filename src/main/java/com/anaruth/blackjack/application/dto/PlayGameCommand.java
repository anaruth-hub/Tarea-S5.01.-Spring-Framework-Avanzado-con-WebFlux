package com.anaruth.blackjack.application.dto;

import com.anaruth.blackjack.domain.model.game.PlayType;

public record PlayGameCommand(String gameId, PlayType playType) {
}
