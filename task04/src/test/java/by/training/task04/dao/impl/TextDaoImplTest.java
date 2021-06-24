package by.training.task04.dao.impl;

import by.training.task04.entity.Sentence;
import by.training.task04.entity.Text;
import by.training.task04.entity.Word;
import by.training.task04.util.exception.TextParserException;
import by.training.task04.repository.impl.RepositoryImpl;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Optional;

import static org.testng.Assert.*;

public class TextDaoImplTest {
    private RepositoryImpl repository = new RepositoryImpl();
    private TextDaoImpl textDao = new TextDaoImpl(repository);


    @DataProvider(name = "dataForTestCreateFromFile")
    public Object[][] createDataForTestCreateFromFile() {
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

    @DataProvider(name = "sampleText")
    public Object[][] createTextSample() {
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
                {new Text("Test", sentenceList), "Test"}
        };
    }

    @DataProvider(name = "dataForTestSetHead")
    public Object[][] createDataForTestSetHead() {
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
                {new Text("Test", sentenceList), "New"}
        };
    }

    @DataProvider(name = "dataForGetSentences")
    public Object[][] createDataForGetSentences() {
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
                {new Text("Test", sentenceList),
                "This is the sample of the text .\n" +
                        "This is the sample of the text .\n"}
        };
    }

    @DataProvider(name = "dataForAddSentence")
    public Object[][] createDataForAddSentence() {
        Sentence sentence = new Sentence(List.of(
                new Word("this"),
                new Word("is"),
                new Word("the"),
                new Word("sample"),
                new Word("of"),
                new Word("the"),
                new Word("text")));

        List<Sentence> sentenceList = List.of(sentence, sentence);
        List<Sentence> sentenceListAdded = List.of(sentence, sentence, sentence);

        return new Object[][]{
                {new Text("Test", sentenceList),
                        sentence,
                        new Text("Test", sentenceListAdded)}
        };
    }

    @DataProvider(name = "dataForAddWordInSentence")
    public Object[][] createDataForAddWordInSentence() {
        Sentence sentence = new Sentence(List.of(
                new Word("this"),
                new Word("is"),
                new Word("the"),
                new Word("sample"),
                new Word("of"),
                new Word("the"),
                new Word("text")));

        Word newWord = new Word("new");
        Sentence sentenceWithNewWord = new Sentence(List.of(
                new Word("this"),
                new Word("is"),
                new Word("the"),
                new Word("sample"),
                new Word("of"),
                new Word("the"),
                new Word("text"),
                newWord));

        List<Sentence> sentenceList = List.of(sentence);
        List<Sentence> sentenceListAdded = List.of(sentenceWithNewWord);

        return new Object[][]{
                {new Text("Test", sentenceList),
                        newWord,
                        new Text("Test", sentenceListAdded)}
        };
    }

    @DataProvider(name = "dataForRemoveWordInSentence")
    public Object[][] createDataForRemoveWordInSentence() {
        Sentence sentence = new Sentence(List.of(
                new Word("this"),
                new Word("is"),
                new Word("the"),
                new Word("sample"),
                new Word("of"),
                new Word("the"),
                new Word("text")));

        Sentence sentenceWithoutFirstWord = new Sentence(List.of(
                new Word("is"),
                new Word("the"),
                new Word("sample"),
                new Word("of"),
                new Word("the"),
                new Word("text")));

        List<Sentence> sentenceList = List.of(sentence);
        List<Sentence> sentenceListWithoutWord = List.of(sentenceWithoutFirstWord);

        return new Object[][]{
                {new Text("Test", sentenceList),
                        new Text("Test", sentenceListWithoutWord)}
        };
    }

    @DataProvider(name = "dataForCreateFromWrongFile")
    public Object[][] createDataForCreateFromWrongFile() {
        return new Object[][]{
                {""},
                {null},
                {"not_existing_file.txt"}
        };
    }

    @Test(description = "positive scenario of creation text from file",
    dataProvider ="dataForTestCreateFromFile")
    public void testCreateFromFile(String filename, Text expected) {
        int actualIndex = textDao.createFromFile(filename);
        Optional<Text> optionalText = textDao.read(actualIndex);
        assertTrue(optionalText.isPresent());
        assertEquals(optionalText.get(), expected);
    }

    @Test(description = "negative scenario of creation text from file",
            dataProvider ="dataForCreateFromWrongFile",
            expectedExceptions = TextParserException.class)
    public void testCreateFromWrongFile(String filename) {
        textDao.createFromFile(filename);
    }

    @Test(description = "positive scenario of getHead method",
    dataProvider = "sampleText")
    public void testGetHead(Text text, String expected) {
        repository.create(text.toString());
        assertEquals(textDao.getHead(0), expected);

    }

    @Test(description = "positive scenario of setHead method",
            dataProvider = "dataForTestSetHead")
    public void testSetHead(Text text, String expected) {
        int index = repository.create(text.toString());
        textDao.setHead(index, expected);
        assertEquals(textDao.getHead(index), expected);

    }
    @Test(description = "positive scenario of getSentences method",
            dataProvider = "dataForGetSentences")
    public void testGetSentences(Text text, String expected) {
        int index = repository.create(text.toString());
        assertEquals(textDao.getSentences(index), expected);
    }

    @Test(description = "positive scenario of addSentence method",
            dataProvider = "dataForAddSentence")
    public void testAddSentence(Text text, Sentence sentence, Text expected) {
        int index = repository.create(text.toString());
        textDao.addSentence(index, sentence);
        Optional<Text> optionalText = textDao.read(index);
        assertTrue(optionalText.isPresent());
        assertEquals(optionalText.get(), expected);
    }

    @Test(description = "positive scenario of addWordInSentence method",
            dataProvider = "dataForAddWordInSentence")
    public void testAddWordInSentence(Text text, Word newWord, Text expected) {
        int index = repository.create(text.toString());
        textDao.addWordInSentence(index,0, newWord);
        Optional<Text> optionalText = textDao.read(index);
        assertTrue(optionalText.isPresent());
        assertEquals(optionalText.get(), expected);
    }

    @Test(description = "positive scenario of removeWordInSentence method",
            dataProvider = "dataForRemoveWordInSentence")
    public void testRemoveWordInSentence(Text text, Text expected) {
        int index = repository.create(text.toString());
        textDao.removeWordInSentence(index,0, 0);
        Optional<Text> optionalText = textDao.read(index);
        assertTrue(optionalText.isPresent());
        assertEquals(optionalText.get(), expected);
    }

}