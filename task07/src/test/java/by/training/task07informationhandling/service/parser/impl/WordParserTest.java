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

public class WordParserTest {

    private static Stream<Arguments> words() {
        return Stream.of(
                arguments("Test", List.of("T", "e", "s", "t")),
                arguments("?!", List.of("?", "!"))
        );
    }

    @ParameterizedTest
    @MethodSource("words")
    public void handleRequest(String wordToParse, List<String>expected) {

        Composite composite = new Composite(TypeManager.WORD);
        WordParser wordParser = new WordParser();
        wordParser.handleRequest(composite, wordToParse);
        final List<Component> childList = composite.getChildList();
        for (int i = 0; i < expected.size(); i++) {
            assertEquals(expected.get(i), childList.get(i).collect());
        }
    }
}