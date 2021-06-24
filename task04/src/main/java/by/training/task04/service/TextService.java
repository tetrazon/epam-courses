package by.training.task04.service;

import by.training.task04.entity.Text;

public interface TextService {
    int createFromFile(String fileName);
    Text read(int index);
    String getHead(int textId);
    void setHead(int textId, String head);
    String getSentences(int textId);
    Text addSentence(int textId, String sentenceString);
    void delete(int textId);
    void addWordInSentence(int textId, int sentenceIndex, String newWordString);
    void removeWordInSentence(int textId, int sentenceIndex, int wordIndexToDelete);
}
