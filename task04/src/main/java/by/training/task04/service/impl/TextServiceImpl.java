package by.training.task04.service.impl;

import by.training.task04.dao.TextDao;
import by.training.task04.entity.Sentence;
import by.training.task04.entity.Text;
import by.training.task04.entity.Word;
import by.training.task04.service.TextService;
import by.training.task04.service.exception.TextServiceException;
import by.training.task04.util.Parser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

/**
 * service class to work controller with DAO
 */
public class TextServiceImpl implements TextService {

    private static final Logger logger = LogManager.getLogger(TextServiceImpl.class);

    private TextDao textDao;

    public TextServiceImpl(TextDao textDao) {
        this.textDao = textDao;
    }

    @Override
    public int createFromFile(String fileName) {
        if (fileName == null || fileName.isEmpty()) {
            throw new TextServiceException("null/empty file name");
        }
        return textDao.createFromFile(fileName);
    }

    private void checkIndex(int index){
        if (index < 0){
            throw new TextServiceException("incorrect index");
        }
    }

    @Override
    public Text read(int index) {
        checkIndex(index);
        Optional<Text> optionalText = textDao.read(index);
        return optionalText.orElseThrow(()-> new TextServiceException("wrong index"));
    }

    @Override
    public String getHead(int index) {
        checkIndex(index);
        Optional<Text> optionalText = textDao.read(index);
        return optionalText
                .map(Text::getHead)
                .orElseThrow(()-> new TextServiceException("wrong index"));
    }

    @Override
    public void setHead(int index, String head) {
        checkIndex(index);
        if (head == null || head.isEmpty()) {
            throw  new TextServiceException("null/empty head!");
        }

        Optional<Text> optionalText = textDao.read(index);
        if (optionalText.isPresent()){
            Text text = optionalText.get();
            text.setHead(head);
            textDao.update(index, text);
        } else {
            throw  new TextServiceException("error: null text");
        }
    }

    @Override
    public String getSentences(int index) {
        checkIndex(index);
        Optional<Text> optionalText = textDao.read(index);
        return optionalText
                .map(Text::SentencesToString)
                .orElseThrow(()-> new TextServiceException("wrong index"));
    }

    @Override
    public Text addSentence(int index, String sentenceString) {
        checkIndex(index);

        if (sentenceString == null || sentenceString.isEmpty()){
            throw new TextServiceException("empty/null sentence");
        }

        Optional<Text> optionalText = textDao.read(index);
        if (optionalText.isPresent()){
            Sentence newSentence = new Sentence(Parser.parseStringToWordList(sentenceString));
            textDao.addSentence(index, newSentence);
            return textDao.read(index).get();
        } else {
            throw new TextServiceException("empty/null sentence");
        }
    }

    @Override
    public void delete(int index) {
        checkIndex(index);
        textDao.delete(index);
    }

    @Override
    public void addWordInSentence(int index, int sentenceIndex, String newWordString) {
        checkIndex(index);
        checkIndex(sentenceIndex);
        if (newWordString == null || newWordString.isEmpty()){
            throw new TextServiceException("empty/null word");
        }
        textDao.addWordInSentence(index, sentenceIndex, new Word(newWordString));
    }

    @Override
    public void removeWordInSentence(int index, int sentenceIndex, int wordIndexToDelete) {
        checkIndex(index);
        checkIndex(sentenceIndex);
        checkIndex(wordIndexToDelete);
        textDao.removeWordInSentence(index, sentenceIndex, wordIndexToDelete);
    }
}
