package no.teknisk.test.service;

import no.teknisk.test.model.Deck;
import no.teknisk.test.model.Hand;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrchestrationServiceTest {

    @Mock
    InputService inputServiceMock;
    @Mock
    private DeckService deckServiceMock;
    @Mock
    private GameService gameServiceMock;
    @Mock
    private ResultService resultServiceMock;
    @InjectMocks
    private OrchestrationService orchestrationService;

    private static final Deck DECK = Deck.builder().build();
    private static final List<Hand> HANDS = Collections.singletonList(Hand.builder().build());

    @Test
    @DisplayName("Orchestrate game with provided deck")
    void playWithProvidedDeck() {
        String filepath = "/does/not/exist.txt";
        List<String> cardStrings = Arrays.asList("CA", "D5");
        when(inputServiceMock.getCardsFromFile(filepath))
                .thenReturn(cardStrings);
        when(deckServiceMock.prepareProvidedDeck(cardStrings))
                .thenReturn(DECK);
        when(gameServiceMock.playBlackjack(DECK))
                .thenReturn(HANDS);

        orchestrationService.playWithProvidedDeck(filepath);

        verify(inputServiceMock).getCardsFromFile(filepath);
        verify(deckServiceMock).prepareProvidedDeck(cardStrings);
        verify(gameServiceMock).playBlackjack(DECK);
        verify(resultServiceMock).announceWinner(HANDS);
    }

    @Test
    @DisplayName("Orchestrate game with new deck")
    void playWithNewDeck() {
        when(deckServiceMock.prepareNewDeck())
                .thenReturn(DECK);
        when(gameServiceMock.playBlackjack(DECK))
                .thenReturn(HANDS);

        orchestrationService.playWithNewDeck();

        verify(deckServiceMock).prepareNewDeck();
        verify(gameServiceMock).playBlackjack(DECK);
        verify(resultServiceMock).announceWinner(HANDS);
    }
}