package by.training.task04.controller;

import by.training.task04.controller.exception.TextControllerException;
import by.training.task04.entity.Text;
import by.training.task04.service.TextService;
import by.training.task04.util.TextGenerator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TextController {
    private static final Logger logger = LogManager.getLogger(TextController.class);
    private static final String COMMAND_DELIMITER = "\\|";

    private TextService textService;

    public TextController(TextService textService) {
        this.textService = textService;
    }



    public int createFromFile(String fileName){
        return textService.createFromFile(fileName);
    }

    public Text read(String index) {
        return textService.read(Integer.parseInt(index));
    }

    public String getHead(String stringTextId){

        int textId = Integer.parseInt(stringTextId);
        return textService.getHead(textId);
    }
    public void setHead(String request){
        String[] params = request.split(COMMAND_DELIMITER);
        if (params.length != 2){
            throw new TextControllerException("wrong command param number");
        }
        String stringTextId = params[0];
        String head = params[1];
        int textId = Integer.parseInt(stringTextId);
        textService.setHead(textId, head);
    }
    public String getSentences(String request){
        return textService.getSentences(Integer.parseInt(request));
    }
    public Text addSentence(String request){
        String[] params = request.split(COMMAND_DELIMITER);
        if (params.length != 2){
            throw new TextControllerException("wrong command param number");
        }
        String stringTextId = params[0];
        String sentenceString = params[1];
        int textId = Integer.parseInt(stringTextId);

        return textService.addSentence(textId, sentenceString);
    }
    public void delete(String request){
        textService.delete(Integer.parseInt(request));
    }
    public void addWordInSentence(String request){
        String[] params = request.split(COMMAND_DELIMITER);
        if (params.length != 3){
            throw new TextControllerException("wrong command param number");

        }
        String stringTextId = params[0];
        String sentenceIndexString = params[1];
        String wordString = params[2];
        int textId = Integer.parseInt(stringTextId);
        int sentenceIndex = Integer.parseInt(sentenceIndexString);
        textService.addWordInSentence(textId, sentenceIndex, wordString);
    }
    public void removeWordInSentence(String request){
        String[] params = request.split(COMMAND_DELIMITER);
        if (params.length != 3){
            throw new TextControllerException("wrong command param number");
        }
        String stringTextId = params[0];
        String sentenceIndexString = params[1];
        String wordIndexToDeleteString = params[2];
        int textId = Integer.parseInt(stringTextId);
        int sentenceIndex = Integer.parseInt(sentenceIndexString);
        int wordIndexToDelete = Integer.parseInt(wordIndexToDeleteString);
        textService.removeWordInSentence(textId, sentenceIndex, wordIndexToDelete);
    }

    public String generateTextToFile(String numberOfSentencesString){
        int numberOfSentences = 0;
        try {
            numberOfSentences = Integer.parseInt(numberOfSentencesString);
        } catch (NumberFormatException e){
            logger.error("NumberFormatException in TextController");
            throw new TextControllerException("NumberFormatException in TextController", e);
        }
        return TextGenerator.generateTextToFile(numberOfSentences);
    }
}
