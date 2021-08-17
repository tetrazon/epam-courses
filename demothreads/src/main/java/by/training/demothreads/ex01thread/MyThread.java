package by.training.demothreads.ex01thread;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MyThread extends Thread{
    private static final Logger logger = LogManager.getLogger(MyThread.class);

    public MyThread(){}

    @Override
    public void run() {
        logger.info("my thread started");
    }



    /**
     * Allocates a new {@code Thread} object. This constructor has the same
     * effect as {@linkplain #Thread(ThreadGroup, Runnable, String) Thread}
     * {@code (null, null, name)}.
     *
     * @param name the name of the new thread
     */
    public MyThread(String name) {
        super(name);
        logger.info("thread from constructor with name");
    }



    public static void main(String[] args) {
        MyThread thread = new MyThread();
        MyThread thread1 = new MyThread("Ololo");
        thread.start();
        thread1.start();

    }
}
