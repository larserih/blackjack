package no.teknisk.test.model;

import java.util.ArrayList;
import java.util.List;

public class Hand {

    private static final int SEVENTEEN = 17;
    private static final int TWENTY_ONE = 21;

    private final Player player;
    private final List<Card> cards;
    private boolean winner;

    public Hand(Player player, List<Card> cards, boolean winner) {
        this.player = player;
        this.cards = cards == null ? new ArrayList<>() : cards;
        this.winner = winner;
    }

    public Player getPlayer() {
        return player;
    }

    public List<Card> getCards() {
        return cards;
    }

    public boolean isWinner() {
        return winner;
    }

    public void setWinner(boolean winner) {
        this.winner = winner;
    }

    public void addCard(Card card) {
        this.cards.add(card);
    }

    public boolean has21() {
        return getCardsValue() == TWENTY_ONE;
    }

    public boolean hasOver21() {
        return getCardsValue() > TWENTY_ONE;
    }

    public boolean hasLessThan17() {
        return getCardsValue() < SEVENTEEN;
    }

    public int getCardsValue() {
        return cards.stream()
                .map(Card::getValue)
                .reduce(0, Integer::sum);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private Player player;
        private List<Card> cards;
        private boolean winner;

        public Builder player(Player player) {
            this.player = player;
            return this;
        }

        public Builder cards(List<Card> cards) {
            this.cards = cards;
            return this;
        }

        public Builder winner(boolean winner) {
            this.winner = winner;
            return this;
        }

        public Hand build() {
            return new Hand(player, cards, winner);
        }
    }

}
