package no.teknisk.test.model;

import java.util.ArrayList;
import java.util.List;

public class Deck {

    private final List<Card> cards;

    public Deck(List<Card> cards) {
        this.cards = cards == null ? new ArrayList<>() : cards;
    }

    public List<Card> getCards() {
        return cards;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private List<Card> cards;

        public Builder cards(List<Card> cards) {
            this.cards = cards;
            return this;
        }

        public Deck build() {
            return new Deck(cards);
        }
    }
}
