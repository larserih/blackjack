package no.teknisk.test.service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

public class InputService {

    private static final String DELIMITER = ",";
    private static final String WHITESPACE_REGEX = "\\s";

    public List<String> getCardsFromFile(String filepath) {
        try {
            String content = Files.readString(Path.of(filepath), StandardCharsets.UTF_8);
            if (content.isBlank()) {
                throw new IllegalArgumentException(String.format("The file provided in path %s has no cards", filepath));
            }
            content = content.replaceAll(WHITESPACE_REGEX, "");
            return Arrays.asList(content.split(DELIMITER));

        } catch (IOException e) {
            throw new IllegalArgumentException(String.format("Couldn't read content from file in path %s", filepath), e);
        }
    }
}
