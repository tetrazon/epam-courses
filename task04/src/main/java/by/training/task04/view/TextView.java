package by.training.task04.view;

import by.training.task04.controller.TextController;
import by.training.task04.entity.MessageManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * class for working the client with the application
 */
public class TextView {
    private static final Logger logger = LogManager.getLogger(TextView.class);
    private static final String OK_STATUS = "command has performed successfully";
    private static final String ERROR_STATUS = "wrong command: ";

    private TextController textController;

    public TextView(TextController textController) {
        this.textController = textController;
    }

    public void menu(){
        MessageManager mm;
        Scanner sc = new Scanner(System.in);
        System.out.println("1 — eng\n2 — ru\nany — en");
        int langOption = 0;
        try{
            langOption = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e){
            logger.error("parse int exception");
        }
        switch (langOption) {
            case 2:
                mm = MessageManager.RU;
                break;
            case 1:
            default:
                mm = MessageManager.EN;
                break;
        }

        String request = "";
        while(true){
            System.out.println(mm.getString("menu.options"));
            request = getRequest(sc);
            if (request.equals("11")){
                break;
            }
            try{
                switch (request){
                    case "1":
                        System.out.println(mm.getString("menu.text.createFromFile"));
                        System.out.println(textController.createFromFile(getRequest(sc)));
                        break;
                    case "2":
                        System.out.println(mm.getString("menu.text.writeToConsole"));
                        System.out.println(textController.read(getRequest(sc)));
                        break;
                    case "3":
                        System.out.println(mm.getString("menu.text.getParam"));
                        System.out.println(textController.getHead(getRequest(sc)));
                        break;
                    case "4":
                        System.out.println(mm.getString("menu.text.changeHead"));
                        textController.setHead(getRequest(sc));
                        System.out.println(OK_STATUS);
                        break;
                    case "5":
                        System.out.println(mm.getString("menu.text.getParam"));
                        System.out.println(textController.getSentences(getRequest(sc)));
                        break;
                    case "6":
                        System.out.println(mm.getString("menu.text.addSentence"));
                        System.out.println(textController.addSentence(getRequest(sc)));
                        break;
                    case "7":
                        System.out.println(mm.getString("menu.text.addWord"));
                        textController.addWordInSentence(getRequest(sc));
                        System.out.println(OK_STATUS);
                        break;
                    case "8":
                        System.out.println(mm.getString("menu.text.removeWord"));
                        textController.removeWordInSentence(getRequest(sc));
                        System.out.println(OK_STATUS);
                        break;
                    case "9":
                        System.out.println(mm.getString("menu.text.delete"));
                        textController.delete(getRequest(sc));
                        System.out.println(OK_STATUS);
                        break;
                    case "10":
                        System.out.println(mm.getString("menu.text.generateText"));
                        System.out.println(textController.generateTextToFile(getRequest(sc)));
                        break;
                    default:
                        System.out.println(mm.getString("menu.text.errorInput"));
                        break;
                }
            } catch (Exception e){
                logger.error("error performing command", e);
                System.out.println(ERROR_STATUS + e.getMessage());
            }

        }

    }

    private String getRequest(Scanner sc) {
        String request = "\n";
        try {
            request = sc.nextLine();
        } catch (InputMismatchException e) {
            logger.error("request error");
        }
        return request;
    }
}
