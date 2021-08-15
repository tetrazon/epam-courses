package by.training.task07informationhandling.service;

import by.training.task07informationhandling.entity.composite.impl.Composite;
import by.training.task07informationhandling.service.exception.TextServiceException;

public interface TextService {
    Composite parseText(String text);
    void sortBySentenceNumber(Composite composite);
    void sortByWordLengthInSentence(Composite composite);
    String sortLexemes(Composite composite, char symbolToCount);
    String readFromFile(String filename) throws TextServiceException;
}
