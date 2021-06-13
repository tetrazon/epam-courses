package by.training.demo.view;

import by.training.demo.controller.Controller;
import by.training.demo.exception.ControllerException;
import by.training.demo.entity.manager.MessageManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.InputMismatchException;
import java.util.Scanner;

public class View {
    private final static Logger logger = LogManager.getLogger(View.class);

    private Controller controller;
    public View(Controller controller){
        this.controller = controller;
    }

    public void menu() throws ControllerException {
        MessageManager mm;
        Scanner sc = new Scanner(System.in);
        System.out.println("1 — eng\n2 — be\n3 — ru\nany — en");
        int langOption = 0;
        try{
            langOption = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e){
            logger.error("parse int exception");
        }
        switch (langOption) {
            case 3:
                mm = MessageManager.RU;
                break;
            case 2:
                mm = MessageManager.BY;
                break;
            case 1:
            default:
                mm = MessageManager.EN;
                break;
        }

        System.out.println(mm.getString("view.options"));
        String request = "";
        while(true){
            try{
                request = sc.nextLine();
            } catch (InputMismatchException e){
                logger.error("request error");
            }
            if (request.equals("exit")){
                break;
            }

            try {
                System.out.println(controller.executeTask(request));
            } catch (ControllerException e){
                System.out.println("wrong command, try again");
            }
        }

    }

}
