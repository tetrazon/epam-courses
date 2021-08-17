package by.training.demothreads.ex02threadrunnableperson;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.TimeUnit;

public class RunnablePerson extends Person implements Runnable {

    private static final Logger logger = LogManager.getLogger(RunnablePerson.class);

    public RunnablePerson(String surname) {
        super(surname);
    }

    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        logger.info("rp thread started");

        for (int i = 0; i < 100; i++) {
            logger.info(i + ") " + getSurname() + ": hello Ololo");
            //logger.info("------" + Thread.currentThread() +"----------");

            try {
                TimeUnit.MILLISECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }

    public static void main(String[] args) {

        Runnable r = new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread());
            }
        };
        r.run();

        Thread thread = new Thread(r);
        thread.start();

        /*logger.info("------" + Thread.currentThread() +"----------");
        RunnablePerson alice = new RunnablePerson("Alice");
        Thread threadAlice = new Thread(alice, "ThreadAlice");

        RunnablePerson bob = new RunnablePerson("Bob");
        Thread threadBob = new Thread(bob, "ThreadBob");
        threadAlice.setDaemon(true);
        threadBob.setDaemon(true);
        threadAlice.setPriority(10);
        threadBob.setPriority(1);
        threadAlice.start();
        threadBob.start();
        try {
            threadAlice.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        logger.info("--------" + "main has finished" + "--------");*/
    }
}
