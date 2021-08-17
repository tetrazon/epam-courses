package by.training.demothreads.ex20threadexchanger;

public class RunExchange {
    public static void main(String[ ] args) {
        Item ss1 = new Item(34, 2200);
        Item ss2 = new Item(34, 2100);
        new Thread(new Producer("HP ", ss1)).start();
        new Thread(new Consumer("RETAIL Trade", ss2)).start();
    }
}
