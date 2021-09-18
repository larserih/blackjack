package no.teknisk.test.service;

import no.teknisk.test.model.Card;
import no.teknisk.test.model.Deck;
import no.teknisk.test.model.Suit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class DeckService {

    private static final int LOWEST_CARD_VALUE = 2;
    private static final int HEIGHEST_CARD_VALUE = 14;

    public Deck prepareProvidedDeck(List<String> cardStrings) {
        List<Card> cards = new ArrayList<>();

        cardStrings.forEach(cardString ->
                cards.add(createCardFromString(cardString)));

        return createDeck(cards);
    }

    public Deck prepareNewDeck() {
        List<Card> cards = new ArrayList<>();

        Arrays.stream(Suit.values())
                .forEach(suit -> cards.addAll(createCardsInSuit(suit)));

        Collections.shuffle(cards);
        return createDeck(cards);
    }

    private Deck createDeck(List<Card> cards) {
        return Deck.builder()
                .cards(cards)
                .build();
    }

    private Card createCardFromString(String card) {
        String symbol = card.substring(1);
        Suit suit = Suit.valueOf(card.substring(0, 1));
        return createCard(symbol, suit);
    }

    private List<Card> createCardsInSuit(Suit suit) {
        List<Card> cards = new ArrayList<>();
        for (int i = LOWEST_CARD_VALUE; i <= HEIGHEST_CARD_VALUE; i++) {
            String symbol = findSymbol(i);
            cards.add(createCard(symbol, suit));
        }
        return cards;
    }

    private Card createCard(String symbol, Suit suit) {
        return Card.builder()
                .symbol(symbol)
                .value(findValue(symbol))
                .suit(suit)
                .build();
    }

    private int findValue(String value) {
        switch (value) {
            case "J":
            case "Q":
            case "K":
                return 10;
            case "A":
                return 11;
            default:
                return Integer.parseInt(value);
        }
    }

    private String findSymbol(int value) {
        switch (value) {
            case 11:
                return "J";
            case 12:
                return "Q";
            case 13:
                return "K";
            case 14:
                return "A";
            default:
                return String.valueOf(value);
        }
    }
}
