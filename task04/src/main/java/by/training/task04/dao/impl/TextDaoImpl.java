package by.training.task04.dao.impl;

import by.training.task04.dao.TextDao;
import by.training.task04.dao.exception.TextDaoException;
import by.training.task04.entity.Sentence;
import by.training.task04.entity.Text;
import by.training.task04.entity.Word;
import by.training.task04.repository.Repository;
import by.training.task04.util.Parser;

import java.util.Optional;

/**
 * class for working with Text entities saved in repository
 */
public class TextDaoImpl implements TextDao {

    private Repository<String> repository;

    public TextDaoImpl(Repository<String> repository) {
        this.repository = repository;
    }

    @Override
    public int createFromFile(String fileName) {
        Text text = Parser.parseTextFromFile(fileName);
        return repository.create(text.toString());
    }

    @Override
    public Optional<Text> read(int index) {
        Optional<String> optionalTextString = repository.getById(index);
        Optional<Text> optionalText = Optional.empty();
        if (optionalTextString.isPresent()){
            optionalText = Optional.of(Parser.parseTextFromString(optionalTextString.get()));
        }
        return optionalText;
    }

    @Override
    public String getHead(int index) {
        Text text = extractText(index);
        return text.getHead();
    }

    @Override
    public void setHead(int index, String head) {
        if (head == null || head.isEmpty()){
            throw new TextDaoException("empty/null string");
        }
        Text text = extractText(index);
        text.setHead(head);
        repository.update(text.toString(), index);
    }

    @Override
    public String getSentences(int index) {
        Text text = extractText(index);
        return text.SentencesToString();
    }

    private Text extractText(int index) {
        Optional<Text> optionalText = read(index);
        if (optionalText.isEmpty()) {
            throw new TextDaoException("cannot get text by index");
        }
        return optionalText.get();
    }

    @Override
    public void addSentence(int index, Sentence sentence) {
        if (sentence == null || sentence.getWordList().isEmpty()){
            throw new TextDaoException("empty/null sentence");
        }
        Text text = extractText(index);
        text.addSentence(sentence);
        repository.update(text.toString(), index);

    }

    @Override
    public void delete(int index) {
        repository.remove(index);

    }

    @Override
    public void addWordInSentence(int textId, int sentenceIndex, Word newWord) {
        if (newWord == null || newWord.getValue().isEmpty()){
            throw new TextDaoException("empty/null word");
        }
        Text text = extractText(textId);
        if (sentenceIndex < 0 || sentenceIndex > (text.getSentenceList().size() - 1)){
            throw new TextDaoException("sentence index out of band");
        }
        text.getSentenceList().get(sentenceIndex).addWord(newWord);
        repository.update(text.toString(), textId);
    }

    @Override
    public void removeWordInSentence(int textId, int sentenceIndex, int wordIndexToDelete) {
        Text text = extractText(textId);
        if (sentenceIndex < 0 || sentenceIndex > (text.getSentenceList().size() - 1)){
            throw new TextDaoException("sentence index out of band");
        }

        Sentence sentence = text.getSentenceList().get(sentenceIndex);
        if (wordIndexToDelete < 0 || wordIndexToDelete > (sentence.getWordList().size() -1)){
            throw new TextDaoException("word index out of band");
        }
        sentence.removeWordByIndex(wordIndexToDelete);
        repository.update(text.toString(), textId);
    }

    @Override
    public void update(int index, Text newText) {
        repository.update(newText.toString(), index);
    }
}
