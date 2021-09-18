package no.teknisk.test.service;

import no.teknisk.test.model.Card;
import no.teknisk.test.model.Deck;
import no.teknisk.test.model.Hand;
import no.teknisk.test.model.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class GameServiceTest {

    private final GameService gameService = new GameService();

    @ParameterizedTest(name = "{index}. {0} -> expected winner=''{2}'', sam's score='{3}', dealer's score={4}")
    @DisplayName("Winner is set correctly based on card values")
    @MethodSource("createInputAndExpectedWinner")
    void correctWinnerIsSet(String name, Deck deck, Player expectedWinner, int samsScore, int dealersScore) {
        List<Hand> hands = gameService.playBlackjack(deck);

        assertThat(hands.stream()
                .filter(Hand::isWinner)
                .map(Hand::getPlayer))
                .containsOnly(expectedWinner);

        assertThat(hands.stream()
                .filter(hand -> Player.SAM.equals(hand.getPlayer()))
                .map(Hand::getCardsValue))
                .containsOnly(samsScore);

        assertThat(hands.stream()
                .filter(hand -> Player.DEALER.equals(hand.getPlayer()))
                .map(Hand::getCardsValue))
                .containsOnly(dealersScore);
    }

    @Test
    @DisplayName("Throws IllegalStateException when there are no cards left to pull")
    void IllegalStateExceptionWhenTheDeckRanOutOfCards() {
        Deck emptyDeck = Deck.builder().build();
        assertThatThrownBy(() -> gameService.playBlackjack(emptyDeck))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("There are no more cards in the deck");
    }

    public static Stream<Arguments> createInputAndExpectedWinner() {
        Deck aces = createDeck(createCards(11, 11, 11, 11));
        Deck blackjack = createDeck(createCards(11, 11, 10, 10));
        Deck samBusts = createDeck(createCards(6, 3, 10, 10, 8));
        Deck dealerBusts = createDeck(createCards(8, 8, 10, 10, 6));
        Deck dealerHighestScore = createDeck(createCards(9, 2, 8, 5, 6, 6));

        return Stream.of(
                Arguments.of("Both gets 22 initally", aces, Player.DEALER, 22, 22),
                Arguments.of("Both gets 21 initally", blackjack, Player.SAM, 21, 21),
                Arguments.of("Sam busts", samBusts, Player.DEALER, 24, 13),
                Arguments.of("Dealer busts", dealerBusts, Player.SAM, 18, 24),
                Arguments.of("Dealer with highest score", dealerHighestScore, Player.DEALER, 17, 19)
        );
    }

    private static List<Card> createCards(int... cardValues) {
        List<Card> cards = new ArrayList<>();
        for (int value : cardValues) {
            cards.add(Card.builder()
                    .value(value)
                    .build());
        }
        return cards;
    }

    private static Deck createDeck(List<Card> cards) {
        return Deck.builder()
                .cards(cards)
                .build();
    }
}