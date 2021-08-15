package by.training.task07informationhandling.service.impl;

import by.training.task07informationhandling.entity.composite.impl.Composite;
import by.training.task07informationhandling.manager.TypeManager;
import by.training.task07informationhandling.service.TextService;
import by.training.task07informationhandling.service.exception.TextServiceException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TextServiceImplTest {
    private TextService textService = new TextServiceImpl();

    @ParameterizedTest
    @DisplayName("negative scenario of parseText() method")
    @NullAndEmptySource
    @ValueSource(strings = {"wrong/wrongfilename.txt", "data/two_symbols.txt", "data/empty.txt"})
    public void errorReadFromFile(String filename) {
        Assertions.assertThrows(TextServiceException.class, () -> {textService.readFromFile(filename);});
    }

    @ParameterizedTest
    @DisplayName("positive scenario of parseText() method")
    @ValueSource(strings = {"data/text.txt"})
    public void readFromFile(String filename) throws TextServiceException {
        String expected = "\tIt has survived - not only (five) centuries, but also the leap into 13<<2 electronic typesetting, remaining 30>>>3 essentially ~6&9|(3&4) unchanged." +
                " It was popularised in the 5|(1&2&(3|(4&(25^5|6&47)|3)|2)|1) with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum." +
                "\r\n" +
                "\tIt is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using (~71&(2&3|(3|(2&1>>2|2)&2)|10&2))|78 Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using (Content here), content here', making it look like readable English." +
                "\r\n\tIt is a (8^5|1&2<<(2|5>>2&71))|1200 established fact that a reader will be of a page when looking at its layout?!" +
                "\r\n\tBye...\r\n";
        final String actual = textService.readFromFile(filename);
        assertEquals(actual, expected);
    }

    @ParameterizedTest
    @ValueSource(strings = {"data/text.txt"})
    public void sortByWordLengthInSentence(String filename) throws TextServiceException {
        String text = textService.readFromFile(filename);
        Composite composite = textService.parseText(text);
        textService.sortByWordLengthInSentence(composite);
        String sortedText =
                "     - It has not but the only also leap into 13<<2 (five) 30>>>3 survived centuries, remaining unchanged. electronic ~6&9|(3&4) typesetting, essentially It in of of was the the and with more with like Lorem Ipsum Aldus Lorem Ipsum. sheets release desktop Letraset passages, recently software versions PageMaker including containing publishing popularised 5|(1&2&(3|(4&(25^5|6&47)|3)|2)|1)\n" +
                "     a a a It is be by of at the its long fact that will page when reader layout. content looking readable distracted established a of is it of as to it The has that look like point using Ipsum using here), here', normal making letters, opposed content English. (Content readable more-or-less distribution (~71&(2&3|(3|(2&1>>2|2)&2)|10&2))|78\n" +
                "     a a a It is be of at its fact that will page when reader layout?! looking established (8^5|1&2<<(2|5>>2&71))|1200\n" +
                "     Bye...\n";
        assertEquals(sortedText, composite.collect());

    }

    @ParameterizedTest
    @ValueSource(strings = {"data/text.txt"})
    public void sortBySentenceNumber(String filename) throws TextServiceException {
        String text = textService.readFromFile(filename);
        Composite composite = textService.parseText(text);
        textService.sortBySentenceNumber(composite);
        String sortedText =
                "     It is a (8^5|1&2<<(2|5>>2&71))|1200 established fact that a reader will be of a page when looking at its layout?!\n" +
                        "     Bye...\n" +
                        "     It has survived - not only (five) centuries, but also the leap into 13<<2 electronic typesetting, remaining 30>>>3 essentially ~6&9|(3&4) unchanged. It was popularised in the 5|(1&2&(3|(4&(25^5|6&47)|3)|2)|1) with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.\n" +
                        "     It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using (~71&(2&3|(3|(2&1>>2|2)&2)|10&2))|78 Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using (Content here), content here', making it look like readable English.\n";
        assertEquals(sortedText, composite.collect());

    }

    @ParameterizedTest
    @ValueSource(strings = {"data/text.txt"})
    public void sortLexemes(String filename) throws TextServiceException {
        String text = textService.readFromFile(filename);
        Composite composite = textService.parseText(text);
        final String sortedLexemes = textService.sortLexemes(composite, 'a');
        String expected = " PageMaker passages readable readable Letraset " +
                "a a a a a a a also and as at at containing distracted " +
                "essentially established established fact fact has has " +
                "layout layout leap making normal page page popularised " +
                "reader reader release remaining software that that that " +
                "unchanged was (8^5|1&2<<(2|5>>2&71))|1200 (Content (five) " +
                "(~71&(2&3|(3|(2&1>>2|2)&2)|10&2))|78 - 13<<2 30>>>3 5|(1&2&(3|(4&(25^5|6&47)|3)|2)|1) " +
                "Aldus Bye English Ipsum Ipsum Ipsum It It It It Lorem Lorem The be be but by centuries " +
                "content content desktop distribution electronic here' here) in including into " +
                "is is is it it its its letters like like long look looking looking " +
                "more more-or-less not of of of of of of only opposed point publishing " +
                "recently sheets survived the the the the to " +
                "typesetting using using versions when when will will with with ~6&9|(3&4)";
        assertEquals(expected, sortedLexemes);

    }

}