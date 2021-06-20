package by.training.task04.service.impl;

import by.training.task04.dao.TextDao;
import by.training.task04.entity.Sentence;
import by.training.task04.entity.Text;
import by.training.task04.entity.Word;
import by.training.task04.service.TextService;
import by.training.task04.util.Parser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

public class TextServiceImpl implements TextService {

    private static final Logger logger = LogManager.getLogger(TextServiceImpl.class);

    private TextDao textDao;

    public TextServiceImpl(TextDao textDao) {
        this.textDao = textDao;
    }

    @Override
    public String createFromFile(String fileName) {
        String message = "null/empty file name";
        if (fileName == null || fileName.isEmpty()){
            logger.error(message);
        } else {
            message = String.valueOf(textDao.createFromFile(fileName));
        }

       return message;
    }

    @Override
    public String read(int index) {
        String message = "incorrect index";
        if (index < 0){
            logger.error(message);
        } else {
            Optional<Text> optionalText = textDao.read(index);
            if (optionalText.isPresent()){
                message = optionalText.get().toString();
            }
        }

        return message;
    }

    @Override
    public String getHead(int index) {
        String message = "incorrect index";
        if (index < 0){
            logger.error(message);
        } else {
            Optional<Text> optionalText = textDao.read(index);
            if (optionalText.isPresent()){
                message = optionalText.get().getHead();
            }
        }
        return message;
    }

    @Override
    public String setHead(int index, String head) {
        String message = "ok";
        if (head == null || head.isEmpty()){
            message = "null/empty head";
            logger.error(message);
        }else if (index < 0){
            message = "incorrect index";
            logger.error(message);
        } else {
            Optional<Text> optionalText = textDao.read(index);
            if (optionalText.isPresent()){
                Text text = optionalText.get();
                text.setHead(head);
                textDao.update(index, text);
            } else {
                message = "error: null text";
            }
        }

        return message;
    }

    @Override
    public String getSentences(int index) {
        String message = "incorrect index";
        if (index < 0){
            logger.error(message);
        } else {
            Optional<Text> optionalText = textDao.read(index);
            if (optionalText.isPresent()){
                message = optionalText.get().SentencesToString();
            }
        }

        return message;
    }

    @Override
    public String addSentence(int index, String sentenceString) {
        String message = "incorrect index";
        if (index < 0){
            logger.error(message);
        } else if (sentenceString == null || sentenceString.isEmpty()){
            message = "empty/null sentence";
            logger.error(message);
        } else {
            Optional<Text> optionalText = textDao.read(index);
            if (optionalText.isPresent()){
                Text text = optionalText.get();
                Sentence newSentence = new Sentence(Parser.parseStringToWordList(sentenceString));
                textDao.addSentence(index, newSentence);
                message = textDao.read(index).get().toString();
            }
        }

        return message;
    }

    @Override
    public String delete(int index) {
        String message = "incorrect index";
        if (index < 0){
            logger.error(message);
        } else {
            textDao.delete(index);
            message = "ok";
        }

        return message;

    }

    @Override
    public String addWordInSentence(int index, int sentenceIndex, String newWordString) {
        String message = "error adding";
        if (index < 0 || sentenceIndex < 0){
            message = "incorrect text/sentence index";
            logger.error(message);
        } else if (newWordString == null || newWordString.isEmpty()){
            message = "empty/null word";
            logger.error(message);
        } else {
            textDao.addWordInSentence(index, sentenceIndex, new Word(newWordString));
            message = "ok";
        }

        return message;
    }

    @Override
    public String removeWordInSentence(int index, int sentenceIndex, int wordIndexToDelete) {
        String message = "error removing";
        if (index < 0 || sentenceIndex < 0 || wordIndexToDelete < 0){
            message = "incorrect text/sentence/word index";
            logger.error(message);
        } else {
            textDao.removeWordInSentence(index, sentenceIndex, wordIndexToDelete);
            message = "ok";
        }
        return message;
    }
}
