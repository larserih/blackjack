package no.teknisk.test.service;

import no.teknisk.test.model.Deck;
import no.teknisk.test.model.Hand;

import java.util.List;

public class OrchestrationService {

    private final InputService inputService;
    private final DeckService deckService;
    private final GameService gameService;
    private final ResultService resultService;

    public OrchestrationService(InputService inputService,
                                DeckService deckService,
                                GameService gameService,
                                ResultService resultService) {
        this.inputService = inputService;
        this.deckService = deckService;
        this.gameService = gameService;
        this.resultService = resultService;
    }

    public void playWithProvidedDeck(String filepath) {
        List<String> cardStrings = inputService.getCardsFromFile(filepath);
        Deck deck = deckService.prepareProvidedDeck(cardStrings);
        playAndAnnounceWinner(deck);
    }

    public void playWithNewDeck() {
        Deck deck = deckService.prepareNewDeck();
        playAndAnnounceWinner(deck);
    }

    private void playAndAnnounceWinner(Deck deck) {
        List<Hand> hands = gameService.playBlackjack(deck);
        resultService.announceWinner(hands);
    }
}
