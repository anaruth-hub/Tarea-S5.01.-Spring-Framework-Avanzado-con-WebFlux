package com.anaruth.blackjack.domain.model.game;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DeckTest {

    @Test
    void shouldCreateDeckWithFiftyTwoCards() {
        Deck deck = new Deck();

        assertEquals(52, deck.remainingCards());
    }

    @Test
    void shouldDrawOneCardAndReduceDeckSize() {
        Deck deck = new Deck();

        Card drawnCard = deck.drawCard();

        assertNotNull(drawnCard);
        assertEquals(51, deck.remainingCards());
    }

    @Test
    void shouldBecomeEmptyAfterDrawingAllCards() {
        Deck deck = new Deck();

        for (int i = 0; i < 52; i++) {
            deck.drawCard();
        }

        assertTrue(deck.isEmpty());
    }

    @Test
    void shouldThrowExceptionWhenDrawingFromEmptyDeck() {
        Deck deck = new Deck();

        for (int i = 0; i < 52; i++) {
            deck.drawCard();
        }

        assertThrows(IllegalStateException.class, deck::drawCard);
    }
}
