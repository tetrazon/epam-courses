package by.training.task04.dao;

import by.training.task04.entity.Sentence;
import by.training.task04.entity.Text;
import by.training.task04.entity.Word;

import java.util.Optional;

public interface TextDao {

    int createFromFile(String fileName);
    Optional<Text> read(int index);
    String getHead(int textId);
    void setHead(int textId, String head);
    String getSentences(int textId);
    void addSentence(int textId, Sentence sentence);
    void delete(int textId);
    void addWordInSentence(int textId, int sentenceIndex, Word newWord);
    void removeWordInSentence(int textId, int sentenceIndex, int wordIndexToDelete);
    void update(int index, Text newText);

}
