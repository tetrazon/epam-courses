package by.training.task07informationhandling.service.parser.impl;

import by.training.task07informationhandling.entity.composite.Component;
import by.training.task07informationhandling.entity.composite.impl.Composite;
import by.training.task07informationhandling.manager.TypeManager;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class SentenceParserTest {

    private static Stream<Arguments> sentences() {
        return Stream.of(
                arguments("Test sentence for lexeme parsing... ", List.of("Test", "sentence", "for", "lexeme", "parsing...")),
                arguments("Test or not?! ", List.of("Test", "or", "not?!"))
        );
    }

    @ParameterizedTest
    @MethodSource("sentences")
    public void handleRequest(String sentenceToParse, List<String>expected) {

        Composite composite = new Composite(TypeManager.SENTENCE);
        WordParser wordParser = new WordParser();
        SentenceParser sentenceParser = new SentenceParser(wordParser, TypeManager.LEXEME);
        sentenceParser.handleRequest(composite, sentenceToParse);
        final List<Component> childList = composite.getChildList();
        for (int i = 0; i < expected.size(); i++) {
            assertEquals(expected.get(i), childList.get(i).collect());
        }
    }
}