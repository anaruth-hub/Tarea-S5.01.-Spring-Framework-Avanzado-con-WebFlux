package com.anaruth.blackjack.domain.model.player;

import java.util.Objects;

public class PlayerName {

    private final  String value;

    public PlayerName(String value) {
        if(value == null || value.isBlank()) {
            throw new IllegalArgumentException("Player name cannot be null or blanck");
         }

        String normalizedValue = value.trim();

        if(normalizedValue.length() < 2 || normalizedValue.length() > 30) {
            throw new IllegalArgumentException("Player namee must be between 2 and 30 characters");

        }

        this.value = normalizedValue;
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof  PlayerName that)) return false;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
