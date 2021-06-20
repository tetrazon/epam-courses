package by.training.task04;

import by.training.task04.controller.TextController;
import by.training.task04.dao.TextDao;
import by.training.task04.dao.impl.TextDaoImpl;
import by.training.task04.repository.Repository;
import by.training.task04.repository.impl.RepositoryImpl;
import by.training.task04.service.TextService;
import by.training.task04.service.impl.TextServiceImpl;
import by.training.task04.view.TextView;

public class Runner {
    public static void main(String[] args) {
        Repository<String> repository = new RepositoryImpl();
        TextDao textDao = new TextDaoImpl(repository);
        TextService textService = new TextServiceImpl(textDao);
        TextController textController = new TextController(textService);
        TextView textView = new TextView(textController);
        textView.menu();
    }
}
