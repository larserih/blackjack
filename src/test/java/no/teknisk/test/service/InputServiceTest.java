package no.teknisk.test.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class InputServiceTest {

    InputService inputService = new InputService();

    private static final String FOLDER = "src/test/resources/";

    @ParameterizedTest(name = "{index}. {0} -> file path=''{1}'', expected cards=''{2}''")
    @DisplayName("Get list of strings from file in given file path")
    @MethodSource("createInputAndExpectedOutput")
    void getStringsFromFile(String name, String filePath, List<String> expectedCards) {
        List<String> cardsFromFile = inputService.getCardsFromFile(filePath);

        assertThat(cardsFromFile).isNotEmpty().hasSize(expectedCards.size());
        assertThat(cardsFromFile).usingDefaultElementComparator().isEqualTo(expectedCards);
    }

    @ParameterizedTest(name = "{index}. {0} -> file path=''{1}'', expected error message=''{2}''")
    @DisplayName("Errors with file paths provided")
    @MethodSource("createInputAndExpectedOutputErrors")
    void errorWithFiles(String name, String filePath, String expectedErrorMessage) {
        assertThatThrownBy(() -> inputService.getCardsFromFile(filePath))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(expectedErrorMessage);
    }

    public static Stream<Arguments> createInputAndExpectedOutput() {
        String tenCards = FOLDER + "tenCards.txt";
        String oneCard = FOLDER + "oneCard.txt";

        return Stream.of(
                Arguments.of("Ten card deck is provided", tenCards, Arrays.asList("DJ", "CA", "D4", "HK", "H7", "SJ", "S5", "S9", "D10", "CQ")),
                Arguments.of("One card deck is provided", oneCard, Collections.singletonList("DK"))
        );
    }

    public static Stream<Arguments> createInputAndExpectedOutputErrors() {
        String empty = FOLDER + "empty.txt";
        String doesNotExist = "doesNotExist.txt";

        return Stream.of(
                Arguments.of("Empty file", empty, String.format("The file provided in path %s has no cards", empty)),
                Arguments.of("File path is invalid", doesNotExist, String.format("Couldn't read content from file in path %s", doesNotExist))
        );
    }
}