package no.teknisk.test.model;

public class Card {

    private final String symbol;
    private final int value;
    private final Suit suit;

    public Card(String symbol, int value, Suit suit) {
        this.symbol = symbol;
        this.value = value;
        this.suit = suit;
    }

    public static Builder builder() {
        return new Builder();
    }

    public String getSymbol() {
        return symbol;
    }

    public int getValue() {
        return value;
    }

    public Suit getSuit() {
        return suit;
    }

    public static class Builder {

        private String symbol;
        private int value;
        private Suit suit;

        public Builder symbol(String symbol) {
            this.symbol = symbol;
            return this;
        }

        public Builder value(int value) {
            this.value = value;
            return this;
        }

        public Builder suit(Suit suit) {
            this.suit = suit;
            return this;
        }

        public Card build() {
            return new Card(symbol, value, suit);
        }
    }
}
