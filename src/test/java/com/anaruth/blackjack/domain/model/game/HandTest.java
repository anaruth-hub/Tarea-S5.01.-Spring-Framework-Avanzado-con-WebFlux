package com.anaruth.blackjack.domain.model.game;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HandTest {

    @Test
    void shouldCalculateScoreWithoutAceAdjustment() {
        Hand hand = new Hand();

        hand.addCard(new Card(Suit.HEARTS, Rank.TEN));
        hand.addCard(new Card(Suit.SPADES, Rank.NINE));

        assertEquals(19, hand.calculateScore());
    }

    @Test
    void shouldAdjustAceValueWhenScoreExceedsTwentyOne() {
        Hand hand = new Hand();

        hand.addCard(new Card(Suit.HEARTS, Rank.ACE));
        hand.addCard(new Card(Suit.SPADES, Rank.NINE));
        hand.addCard(new Card(Suit.CLUBS, Rank.FIVE));

        assertEquals(15, hand.calculateScore());
    }

    @Test
    void shouldDetectBustHand() {
        Hand hand = new Hand();

        hand.addCard(new Card(Suit.HEARTS, Rank.KING));
        hand.addCard(new Card(Suit.SPADES, Rank.NINE));
        hand.addCard(new Card(Suit.CLUBS, Rank.FIVE));

        assertTrue(hand.isBust());
    }


}
