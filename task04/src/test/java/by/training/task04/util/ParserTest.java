package by.training.task04.util;

import by.training.task04.entity.Sentence;
import by.training.task04.entity.Text;
import by.training.task04.entity.Word;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;

public class ParserTest {

    @DataProvider(name = "dataForParseTextFromFile")
    public Object[][] createDataForParseTextFromFile() {
        Sentence sentence = new Sentence(List.of(
                new Word("this"),
                new Word("is"),
                new Word("the"),
                new Word("sample"),
                new Word("of"),
                new Word("the"),
                new Word("text")));

        List<Sentence> sentenceList = List.of(sentence, sentence);

        return new Object[][]{
                {"src/main/resources/text/testParse.txt", new Text("Test", sentenceList)}
        };
    }

    @Test(description = "positive scenario of parsing the text",
    dataProvider = "dataForParseTextFromFile")
    public void testParseTextFromFile(String fileName, Text expected) {
        Text actual = Parser.parseTextFromFile(fileName);
        assertEquals(actual, expected);

    }

    @Test
    public void testParseTextFromString() {
    }

    @Test
    public void testParseStringToWordList() {
    }
}