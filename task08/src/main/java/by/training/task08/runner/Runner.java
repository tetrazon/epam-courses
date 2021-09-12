package by.training.task08.runner;

import by.training.task08.service.creator.FileNameCreator;
import by.training.task08.dao.parser.AbstractFlowersBuilder;
import by.training.task08.dao.parser.FlowerBuilderFactory;

import java.util.Scanner;

public class Runner {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input;
        AbstractFlowersBuilder builder;
        while (true) {
            System.out.println("enter the parsing type:\n" + "sax, dom\n\n" + "enter x for exit\n");
            input = sc.nextLine();
            if (input.equals("x")) {
                break;
            }
            try{
                builder = FlowerBuilderFactory.createFlowerBuilder(input);
                builder.buildSetFlowers(new FileNameCreator().createFileName("data/orangery.xml"));
                System.out.println(builder.getFlowers());
            } catch (EnumConstantNotPresentException e){
                System.out.println("wrong input, try again");
            }
        }
    }
}
