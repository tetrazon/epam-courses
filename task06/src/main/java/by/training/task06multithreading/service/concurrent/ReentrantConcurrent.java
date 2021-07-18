package by.training.task06multithreading.service.concurrent;

import by.training.task06multithreading.entity.CommonResource;
import by.training.task06multithreading.entity.exception.MatrixException;
import by.training.task06multithreading.service.concurrent.exception.ConcurrentException;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;

import java.util.concurrent.locks.ReentrantLock;

@AllArgsConstructor
@Log4j2
public class ReentrantConcurrent implements Runnable {

    private CommonResource commonResource;
    private ReentrantLock locker;

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
    @SneakyThrows
    @Override
    public void run() {
        int limit = commonResource.getMatrix().getHorizontalSize();
        while (true){
            try{
                if (locker.tryLock()){
                    if (commonResource.getCounter() == limit) {
                        locker.unlock();
                        return;
                    }
                    for (int i = 0; i < limit; i++) {
                        try {
                            if (commonResource.getMatrix().getElement(i, i) == 0) {
                                commonResource.getMatrix().setElement(i, i, (int) Thread.currentThread().getId());
                                log.info("thread #" + Thread.currentThread().getId()
                                        + " setting the value matrix[" + i + "][" + i + "]");
                                commonResource.setCounter(commonResource.getCounter() + 1);
                                break;
                            }
                        } catch (MatrixException e) {
                            throw new ConcurrentException("matrix operation error", e);
                        }
                    }
                    locker.unlock();
                }
            } finally {
                if (locker.isHeldByCurrentThread())
                {
                    locker.unlock();
                }
            }
        }
    }
}

