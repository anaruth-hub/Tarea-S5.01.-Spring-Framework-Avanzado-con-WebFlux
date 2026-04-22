package com.anaruth.blackjack.domain.model.player;

public class Player {

    private final PlayerId id;
    private PlayerName name;
    private int wins;
    private int losses;
    private int draws;

    public Player(PlayerId id, PlayerName name) {
        if (id == null) {
            throw new IllegalArgumentException("Player id cannot be null");
        }
        if (name == null) {
            throw new IllegalArgumentException("Player name cannot be null");
        }

        this.id = id;
        this.name = name;
        this.wins = 0;
        this.losses = 0;
        this.draws = 0;

    }

    public PlayerId getId() {
        return id;
    }

    public PlayerName getName() {
        return name;
    }

    public int getWins() {
        return wins;
    }

    public int getLosses() {
        return losses;
    }

    public int getDraws() {
        return draws;
    }
    public void rename(PlayerName newName) {
        if (newName == null) {
            throw new IllegalArgumentException("New player name cannot be null");
        }
        this.name = newName;
    }

    public void registerWin() {
        wins++;
    }

    public void registerLoss() {
        losses++;
    }

    public void registerDraw() {
        draws++;
    }

    public int calculateRankingScore() {
        return (wins * 3) + draws;
    }
}
