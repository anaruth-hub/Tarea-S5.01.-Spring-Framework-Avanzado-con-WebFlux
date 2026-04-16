package com.anaruth.blackjack.domain.model.game;

import com.anaruth.blackjack.domain.model.player.Player;

import java.util.UUID;

public class Game {
    private final String id;
    private final Player player;
    private final Hand playerHand;
    private final Hand dealerHand;
    private final Deck deck;
    private GameStatus status;

    public Game(Player player) {
        if (player == null) {
            throw new IllegalArgumentException("Player cannot be null");
        }

        this.id = UUID.randomUUID().toString();
        this.player = player;
        this.playerHand = new Hand();
        this.dealerHand = new Hand();
        this.deck = new Deck();
        this.status = GameStatus.IN_PROGRESS;

        dealInitialCards();
        evaluateInitialState();
    }

    private void dealInitialCards() {
        playerHand.addCard(deck.drawCard());
        dealerHand.addCard(deck.drawCard());
        playerHand.addCard(deck.drawCard());
        dealerHand.addCard(deck.drawCard());
    }

    private void evaluateInitialState() {
        int playerScore = playerHand.calculateScore();
        int dealerScore = dealerHand.calculateScore();

        if (playerScore == 21 && dealerScore == 21) {
            status = GameStatus.DRAW;
        } else if (playerScore == 21) {
            status = GameStatus.PLAYER_WON;
        } else if (dealerScore == 21) {
            status = GameStatus.DEALER_WON;
        }
    }

    public void play(PlayType playType) {
        validateGameIsStillPlayable();

        if (playType == null) {
            throw new IllegalArgumentException("Play type cannot be null");
        }

        switch (playType) {
            case HIT -> hit();
            case STAND -> stand();
        }
    }

    private void hit() {
        playerHand.addCard(deck.drawCard());

        if (playerHand.isBust()) {
            status = GameStatus.DEALER_WON;
        } else if (playerHand.calculateScore() == 21) {
            stand();

        }
    }

    private void stand() {
        dealerTurn();
        resolveWinner();
    }

    private void dealerTurn() {
        while (dealerHand.calculateScore() < 17) {
            dealerHand.addCard(deck.drawCard());
        }
    }

    private void resolveWinner() {
        int playerScore = playerHand.calculateScore();
        int dealerScore = dealerHand.calculateScore();

        if (dealerHand.isBust()) {
            status = GameStatus.PLAYER_WON;
            return;
        }

        if (playerScore > dealerScore) {
            status = GameStatus.PLAYER_WON;
        } else if (dealerScore > playerScore) {
            status = GameStatus.DEALER_WON;

        } else {
            status = GameStatus.DRAW;
        }
    }

    private void validateGameIsStillPlayable() {
        if (status != GameStatus.IN_PROGRESS) {
            throw new IllegalStateException("Game is already finished");
        }
    }

    public String getId() {
        return id;
    }

    public Player getPlayer() {
        return player;
    }

    public Hand getPlayerHand() {
        return playerHand;
    }

    public Hand getDealerHand() {
        return dealerHand;
    }

    public Deck getDeck() {
        return deck;
    }

    public GameStatus getStatus() {
        return status;
    }
}
