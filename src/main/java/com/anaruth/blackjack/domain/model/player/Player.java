package com.anaruth.blackjack.domain.model.player;

public class Player {

    private final PlayerId id;
    private PlayerName name;

    public Player(PlayerId id, PlayerName name) {
        if (id == null) {
            throw new IllegalArgumentException("Player id cannot be null");
        }
        if (name == null) {
            throw new IllegalArgumentException("Player name cannot be null");
        }

        this.id = id;
        this.name = name;

    }

    public PlayerId getId() {
        return id;
    }

    public PlayerName getName() {
        return name;
    }

    public void rename(PlayerName newName) {
        if (newName == null) {
            throw new IllegalArgumentException("New player name cannot be null");
        }
        this.name = newName;
    }
}
