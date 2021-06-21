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
                    System.out.println(textController.setHead(getRequest(sc)));
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
                    System.out.println(textController.addWordInSentence(getRequest(sc)));
                    break;
                case "8":
                    System.out.println(mm.getString("menu.text.removeWord"));
                    System.out.println(textController.removeWordInSentence(getRequest(sc)));
                    break;
                case "9":
                    System.out.println(mm.getString("menu.text.delete"));
                    System.out.println(textController.delete(getRequest(sc)));
                    break;
                case "10":
                    System.out.println(mm.getString("menu.text.generateText"));
                    System.out.println(textController.generateTextToFile(getRequest(sc)));
                    break;
                default:
                    System.out.println(mm.getString("menu.text.errorInput"));
                    break;
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
