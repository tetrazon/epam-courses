package by.training.task04.controller;

import by.training.task04.entity.Word;
import by.training.task04.service.TextService;
import by.training.task04.service.impl.TextServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TextController {
    private static final Logger logger = LogManager.getLogger(TextController.class);
    private static final String COMMAND_DELIMITER = "\\|";

    private TextService textService;

    public TextController(TextService textService) {
        this.textService = textService;
    }



    public String createFromFile(String fileName){
       return textService.createFromFile(fileName);
    }

    public String read(String index) {
        String message = "Error parsing";
        try {
            message = textService.read(Integer.parseInt(index));
        } catch (NumberFormatException e){
            logger.error("NumberFormatException");
        }
        return message;
    }

    public String getHead(String stringTextId){
        String message = "Error parsing";
        try {
            int textId = Integer.parseInt(stringTextId);
            message = textService.getHead(textId);
        } catch (NumberFormatException e){
            logger.error("NumberFormatException");
        }
        return message;
    }
    public String setHead(String request){
        String message = "incorrect request";
        String[] params = request.split(COMMAND_DELIMITER);
        if (params.length != 2){
            return message;
        }
        String stringTextId = params[0];
        String head = params[1];
        try {
            int textId = Integer.parseInt(stringTextId);
            message = textService.setHead(textId, head);
        } catch (NumberFormatException e){
            logger.error("NumberFormatException");
        }
        return message;
    }
    public String getSentences(String request){
        String message = "Error parsing";
        try {
            message = textService.getSentences(Integer.parseInt(request));
        } catch (NumberFormatException e){
            logger.error("NumberFormatException");
        }
        return message;
    }
    public String addSentence(String request){
        String message = "incorrect request";
        String[] params = request.split(COMMAND_DELIMITER);
        if (params.length != 2){
            return message;
        }
        String stringTextId = params[0];
        String sentenceString = params[1];
        try {
            int textId = Integer.parseInt(stringTextId);
            message = textService.addSentence(textId, sentenceString);
        } catch (NumberFormatException e){
            logger.error("NumberFormatException");
        }
        return message;
    }
    public String delete(String request){
        String message = "Error parsing";
        try {
            message = textService.delete(Integer.parseInt(request));
        } catch (NumberFormatException e){
            logger.error("NumberFormatException");
        }
        return message;
    }
    public String addWordInSentence(String request){
        String message = "incorrect request";
        String[] params = request.split(COMMAND_DELIMITER);
        if (params.length != 3){
            return message;
        }
        String stringTextId = params[0];
        String sentenceIndexString = params[1];
        String wordString = params[2];
        try {
            int textId = Integer.parseInt(stringTextId);
            int sentenceIndex = Integer.parseInt(sentenceIndexString);
            message = textService.addWordInSentence(textId, sentenceIndex, wordString);
        } catch (NumberFormatException e){
            logger.error("NumberFormatException");
        }
        return message;
    }
    public String removeWordInSentence(String request){
        String message = "incorrect request";
        String[] params = request.split(COMMAND_DELIMITER);
        if (params.length != 3){
            return message;
        }
        String stringTextId = params[0];
        String sentenceIndexString = params[1];
        String wordIndexToDeleteString = params[2];
        try {
            int textId = Integer.parseInt(stringTextId);
            int sentenceIndex = Integer.parseInt(sentenceIndexString);
            int wordIndexToDelete = Integer.parseInt(wordIndexToDeleteString);
            message = textService.removeWordInSentence(textId, sentenceIndex, wordIndexToDelete);
        } catch (NumberFormatException e){
            logger.error("NumberFormatException");
        }
        return message;
    }
}
