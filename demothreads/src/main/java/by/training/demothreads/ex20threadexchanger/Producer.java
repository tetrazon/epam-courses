package by.training.demothreads.ex20threadexchanger;

public class Producer extends Subject implements Runnable {
    public Producer(String name, Item item) {
        super(name, item);
    }
    public void run() {
        try {
            synchronized(item) { // блок синхронизации не нужен, но показателен
                int proposedNumber = this.getItem().getNumber();
// обмен синхронизированными экземплярами
                item = exchanger.exchange(item);
                if (proposedNumber <= item.getNumber()) {
                    System.out.println("Producer " + getName()
                            + " повышает план производства товара");
                } else {
                    System.out.println("Producer " + getName()
                            + " снижает план производства товара");
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
