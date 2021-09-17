package no.teknisk.test;

import no.teknisk.test.service.DeckService;
import no.teknisk.test.service.GameService;
import no.teknisk.test.service.InputService;
import no.teknisk.test.service.OrchestrationService;
import no.teknisk.test.service.ResultService;

public class BlackjackApplication {

    public static void main(String[] args) {
        final InputService inputService = new InputService();
        final DeckService deckService = new DeckService();
        final GameService gameService = new GameService();
        final ResultService resultService = new ResultService();
        final OrchestrationService orchestrationService = new OrchestrationService(inputService, deckService, gameService, resultService);

        if (args.length == 0) {
            orchestrationService.playWithNewDeck();
            return;
        }
        orchestrationService.playWithProvidedDeck(args[0]);
    }
}
