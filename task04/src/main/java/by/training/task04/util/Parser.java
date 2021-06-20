package by.training.task04.util;

import by.training.task04.entity.Sentence;
import by.training.task04.entity.Text;
import by.training.task04.entity.Word;
import by.training.task04.exception.ParseException;
import by.training.task04.exception.TextParserException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class Parser {

    private static final Logger logger = LogManager.getLogger(Parser.class);
    private static final String SENTENCES_DELIMITER = "\n";
    private static final String WORDS_DELIMITER = "\\s";



    private Parser(){}

    public static Text parseTextFromFile(String fileName){
        if (fileName == null || fileName.isEmpty()){
            logger.error("empty/null filename");
            throw new TextParserException("empty/null filename");
        }

        String[] stringSentences;
        try {
            stringSentences = new String(Files.readAllBytes(Paths.get(fileName))).split(SENTENCES_DELIMITER);
        } catch (IOException e) {
            logger.error("Error file reading", e);
            throw new TextParserException("Error file reading", e);
        }

        return parseTextFromStringArray(stringSentences);
    }

    public static Text parseTextFromString(String textString){
        String[] stringSentences = textString.split(SENTENCES_DELIMITER);
        return parseTextFromStringArray(stringSentences);
    }

    private static Text parseTextFromStringArray(String[] stringSentences) {
        Text text = new Text();
        text.setHead(stringSentences[0]);
        List<Sentence> sentenceList = new ArrayList<>();
        String[] stringWords;
        for (int i = 1; i < stringSentences.length; i++) {
            List<Word> wordList = parseStringToWordList(stringSentences[i]);
            sentenceList.add(new Sentence(wordList));
        }

        text.setSentenceList(sentenceList);
        return text;
    }

    public static List<Word> parseStringToWordList(String stringSentence) {
        if (stringSentence == null || stringSentence.isEmpty()){
            logger.error("null/empty string");
            throw new ParseException("null/empty string");
        }
        String[] stringWords;
        stringSentence = stringSentence.substring(0, stringSentence.length()-1);
        stringWords = stringSentence.split(WORDS_DELIMITER);
        List<Word> wordList = new ArrayList<>();
        for (String stringWord : stringWords) {
            wordList.add(new Word(stringWord));
        }
        return wordList;
    }
}