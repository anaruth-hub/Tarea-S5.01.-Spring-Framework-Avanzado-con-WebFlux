package com.anaruth.blackjack.domain.model.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Deck {

    private final List<Card>  cards;

    public Deck() {
        this.cards = new ArrayList<>();
        initializeDeck();
        shuffle();
    }

    private void initializeDeck(){
       for(Suit suit : Suit.values()) {
           for(Rank rank : Rank.values()) {
               cards.add(new Card(suit, rank));
           }
       }
    }

        public void shuffle() {
            Collections.shuffle(cards);
        }

        public Card drawCard() {
            if (cards.isEmpty()) {
               throw new IllegalStateException("Cannot draw a card from an empty deck");
            }

            return cards.remove(0);
    }

    public  boolean isEmpty() {
        return cards.isEmpty();
    }

    public int remainingCards() {
        return cards.size();
    }

    public List<Card> getCards() {
        return List.copyOf(cards);
    }

}
