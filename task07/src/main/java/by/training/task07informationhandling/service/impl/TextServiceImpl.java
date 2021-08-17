package by.training.task07informationhandling.service.impl;

import by.training.task07informationhandling.entity.composite.Component;
import by.training.task07informationhandling.entity.composite.impl.Composite;
import by.training.task07informationhandling.manager.TypeManager;
import by.training.task07informationhandling.service.TextService;
import by.training.task07informationhandling.service.exception.TextServiceException;
import by.training.task07informationhandling.service.parser.impl.*;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
@Log4j2
public class TextServiceImpl implements TextService {
    @Override
    public Composite parseText(String text) {
        WordParser wordParser = new WordParser();
        LexemeParser lexemeParser = new LexemeParser(wordParser, TypeManager.WORD);
        SentenceParser sentenceParser = new SentenceParser(lexemeParser, TypeManager.LEXEME);
        ParagraphParser paragraphParser = new ParagraphParser(sentenceParser, TypeManager.SENTENCE);
        TextParser textParser = new TextParser(paragraphParser, TypeManager.PARAGRAPH);
        Composite composite = new Composite(TypeManager.TEXT);
        textParser.handleRequest(composite, text);
        log.info("parsed text:\n" + composite.collect());
        return composite;
    }

    @Override
    public void sortBySentenceNumber(Composite composite) {
        composite.getChildList().sort(Comparator.comparingInt(a -> a.getChildList().size()));
    }

    @Override
    public void sortByWordLengthInSentence(Composite composite) {
        final List<Component> paragraphs = composite.getChildList();
        for (int i = 0; i < paragraphs.size(); i++) {
            List<Component> sentences = paragraphs.get(i).getChildList();
            for (int j = 0; j < sentences.size(); j++) {
                sentences.get(j).getChildList().sort(Comparator.comparingInt(a -> a.getChild(0).collect().length()));
            }
        }
    }

    @Override
    public String sortLexemes(Composite composite, char symbolToCount) {
        List<String> lexemes = new ArrayList<>();
        final List<Component> paragraphs = composite.getChildList();
        for (int i = 0; i < paragraphs.size(); i++) {
            List<Component> sentences = paragraphs.get(i).getChildList();
            for (int j = 0; j < sentences.size(); j++) {
                lexemes.addAll(sentences.get(j).getChildList().
                        stream().
                        map(a -> a.getChild(0).collect().trim()).
                        collect(Collectors.toList()));
            }
        }
        Comparator <String> compareBySymbolCountThenAlphaBet = (a, b)-> {
            int res = (int) (b.chars().filter(ch -> ch == symbolToCount).count()
                    - a.chars().filter(ch -> ch == symbolToCount).count());
                if (res == 0){
                    res = a.compareTo(b);
                }
            return res;
        };
        lexemes.sort(compareBySymbolCountThenAlphaBet);
        return lexemes.stream().reduce("", (a, b) -> a + " " + b);

    }

    @Override
    public String readFromFile(String fileName) throws TextServiceException {
        if (fileName == null || fileName.isEmpty()){
            throw new TextServiceException("empty/null filename");
        }

        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource(fileName);
        if (resource == null){
            throw new TextServiceException("File does not exist.");
        }
        String textFromFile = "";
        try {
            byte[] fileBytes = Files.readAllBytes(Path.of(resource.toURI()));
            textFromFile = new String(fileBytes);
        } catch (IOException e) {
            throw new TextServiceException("Error reading file. ", e);
        } catch (URISyntaxException e) {
            throw new TextServiceException("File name error. ", e);
        }
        if (textFromFile.isEmpty() || textFromFile.length() < 3){
            throw new TextServiceException("Error text string in the file. ");
        }

        return textFromFile;
    }
}
