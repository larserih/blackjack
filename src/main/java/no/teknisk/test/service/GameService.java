package no.teknisk.test.service;

import no.teknisk.test.model.Card;
import no.teknisk.test.model.Deck;
import no.teknisk.test.model.Hand;
import no.teknisk.test.model.Player;

import java.util.Arrays;
import java.util.List;

public class GameService {

    private static final int INITIAL_TURNS = 2;

    public List<Hand> playBlackjack(Deck deck) {
        Hand sam = createHand(Player.SAM);
        Hand dealer = createHand(Player.DEALER);

        dealInitialHands(deck, sam, dealer);

        if (sam.has21()) {
            return samWins(sam, dealer);
        } else if (sam.hasOver21()) {
            return dealerWins(sam, dealer);
        }

        while (sam.hasLessThan17()) {
            sam.addCard(pullCard(deck));
        }

        if (sam.hasOver21()) {
            return dealerWins(sam, dealer);
        }

        while (dealer.getCardsValue() < sam.getCardsValue()) {
            dealer.addCard(pullCard(deck));
        }

        if (dealer.hasOver21()) {
            return samWins(sam, dealer);
        } else {
            return dealerWins(sam, dealer);
        }
    }

    private List<Hand> samWins(Hand sam, Hand dealer) {
        sam.setWinner(true);
        return Arrays.asList(sam, dealer);
    }

    private List<Hand> dealerWins(Hand sam, Hand dealer) {
        dealer.setWinner(true);
        return Arrays.asList(sam, dealer);
    }

    private void dealInitialHands(Deck deck, Hand sam, Hand dealer) {
        for (int i = 0; i < INITIAL_TURNS; i++) {
            sam.addCard(pullCard(deck));
            dealer.addCard(pullCard(deck));
        }
    }

    private Card pullCard(Deck deck) {
        try {
            return deck.getCards().remove(0);
        } catch (IndexOutOfBoundsException e) {
            throw new IllegalStateException("There are no more cards in the deck", e);
        }
    }

    private Hand createHand(Player player) {
        return Hand.builder()
                .player(player)
                .build();
    }
}
