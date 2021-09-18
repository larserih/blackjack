package no.teknisk.test.service;

import no.teknisk.test.model.Card;
import no.teknisk.test.model.Hand;
import no.teknisk.test.model.Player;
import no.teknisk.test.model.Suit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ResultServiceTest {

    ResultService resultService = new ResultService();

    @Test
    @DisplayName("Throws IllegalStateException when no winner")
    void illegalStateNoWinner() {
        List<Hand> noHands = Collections.emptyList();
        assertThatThrownBy(() -> resultService.announceWinner(noHands))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("There must be a winner to announce");
    }

    @Test
    @DisplayName("Throws IllegalStateException when missing player hand")
    void illegalStateMissingPlayerHand() {
        List<Hand> hands = Collections.singletonList(createHand(Player.DEALER, Collections.emptyList(), true));
        assertThatThrownBy(() -> resultService.announceWinner(hands))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("sam must have a hand to show");
    }

    @Test
    @DisplayName("The winner is announced")
    void announceWinner() {
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bo));

        List<Card> samsCards = Arrays.asList(
                createCard(Suit.D, "Q"),
                createCard(Suit.S, "A")
        );
        List<Card> dealersCards = Arrays.asList(
                createCard(Suit.C, "4"),
                createCard(Suit.H, "A"),
                createCard(Suit.C, "A")
        );
        List<Hand> hands = Arrays.asList(
                createHand(Player.SAM, samsCards, true),
                createHand(Player.DEALER, dealersCards, false)
        );

        String expectedAnnouncement = "sam\n" +
                "sam: DQ,SA\n" +
                "dealer: C4,HA,CA\n";

        resultService.announceWinner(hands);

        String linesPrintedToSout = bo.toString();
        assertThat(linesPrintedToSout).isEqualTo(expectedAnnouncement);
    }

    private Card createCard(Suit suit, String symbol) {
        return Card.builder()
                .suit(suit)
                .symbol(symbol)
                .build();
    }

    private Hand createHand(Player player, List<Card> cards, boolean winner) {
        return Hand.builder()
                .player(player)
                .cards(cards)
                .winner(winner)
                .build();
    }
}