package by.training.task07informationhandling.service.parser.impl;

import by.training.task07informationhandling.entity.composite.Component;
import by.training.task07informationhandling.entity.composite.impl.Composite;
import by.training.task07informationhandling.manager.TypeManager;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class ParagraphParserTest {

    private static Stream<Arguments> paragraphs() {
        return Stream.of(
                arguments(" Test sentence for paragraph parsing! Second sentence! Third sentence? ",
                        List.of("Test sentence for paragraph parsing!", "Second sentence!", "Third sentence?")),
                arguments(" Test sentence, for paragraph: parsing... Second - sentence! Third; sentence?! ",
                        List.of("Test sentence, for paragraph: parsing...", "Second - sentence!", "Third; sentence?!"))
        );
    }

    @ParameterizedTest
    @MethodSource("paragraphs")
    public void handleRequest(String sentenceToParse, List<String>expected) {

        WordParser wordParser = new WordParser();
        SentenceParser sentenceParser = new SentenceParser(wordParser, TypeManager.LEXEME);
        ParagraphParser paragraphParser = new ParagraphParser(sentenceParser, TypeManager.SENTENCE);
        Composite composite = new Composite(TypeManager.SENTENCE);
        paragraphParser.handleRequest(composite, sentenceToParse);
        final List<Component> childList = composite.getChildList();
        for (int i = 0; i < expected.size(); i++) {
            assertEquals(expected.get(i), childList.get(i).collect());
        }
    }
}