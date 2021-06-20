package by.training.task04.service;

public interface TextService {
    String createFromFile(String fileName);
    String read(int index);
    String getHead(int textId);
    String setHead(int textId, String head);
    String getSentences(int textId);
    String addSentence(int textId, String sentenceString);
    String delete(int textId);
    String addWordInSentence(int textId, int sentenceIndex, String newWordString);
    String removeWordInSentence(int textId, int sentenceIndex, int wordIndexToDelete);
}
