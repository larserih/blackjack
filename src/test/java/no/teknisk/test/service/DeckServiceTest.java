package no.teknisk.test.service;

import no.teknisk.test.model.Card;
import no.teknisk.test.model.Deck;
import no.teknisk.test.model.Suit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class DeckServiceTest {

    private final DeckService deckService = new DeckService();

    private static final int TOTAL_SUM_OF_BLACKJACK_DECK = 380;

    @ParameterizedTest(name = "{index}. {0}")
    @DisplayName("Input creates correct cards in deck")
    @MethodSource("createInputAndExpectedCards")
    void correctCardsAreCreated(String name, List<String> cardsAsString, List<Card> expectedCards) {
        Deck deck = deckService.prepareProvidedDeck(cardsAsString);

        assertThat(deck.getCards())
                .usingRecursiveFieldByFieldElementComparator()
                .isEqualTo((expectedCards));
    }

    @Test
    @DisplayName("Prepares a whole new deck with 52 cards")
    void prepareNewDeck() {
        Deck deck = deckService.prepareNewDeck();

        List<Card> cards = deck.getCards();
        assertThat(cards).hasSize(52);
        assertThat(cards)
                .extracting(Card::getSuit)
                .contains(Suit.values());
        assertThat(cards.stream()
                .map(Card::getValue)
                .reduce(0, Integer::sum))
                .isEqualTo(TOTAL_SUM_OF_BLACKJACK_DECK);
    }

    public static Stream<Arguments> createInputAndExpectedCards() {
        List<String> twoOfSpadesAsString = Collections.singletonList("S2");
        List<Card> twoOfSpades = Collections.singletonList(createCard("2", Suit.S, 2));

        List<String> letteredCardsAsString = Arrays.asList("SJ", "CQ", "DK", "HA");
        List<Card> letteredCards = Arrays.asList(
                createCard("J", Suit.S, 10),
                createCard("Q", Suit.C, 10),
                createCard("K", Suit.D, 10),
                createCard("A", Suit.H, 11));

        return Stream.of(
                Arguments.of("Single card is created successfully", twoOfSpadesAsString, twoOfSpades),
                Arguments.of("Lettered cards are given the correct values", letteredCardsAsString, letteredCards),
                Arguments.of("Empty list of cards", Collections.emptyList(), Collections.emptyList())
        );
    }

    private static Card createCard(String symbol, Suit suit, int value) {
        return Card.builder()
                .symbol(symbol)
                .suit(suit)
                .value(value)
                .build();
    }
}