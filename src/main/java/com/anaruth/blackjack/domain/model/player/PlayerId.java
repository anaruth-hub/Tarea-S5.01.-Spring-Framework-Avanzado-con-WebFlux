package com.anaruth.blackjack.domain.model.player;

import java.util.Objects;
import java.util.UUID;

public class PlayerId {

    private final String value;

    public PlayerId(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Player id cannot be null or blank");
        }
        this.value  = value;
    }

    public static PlayerId randomId() {
        return new PlayerId(UUID.randomUUID().toString());
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PlayerId playerId)) return false;
        return Objects.equals(value, playerId.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
