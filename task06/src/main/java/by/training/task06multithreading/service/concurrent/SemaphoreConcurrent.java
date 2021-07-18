package by.training.task06multithreading.service.concurrent;

import by.training.task06multithreading.entity.CommonResource;
import by.training.task06multithreading.entity.exception.MatrixException;
import by.training.task06multithreading.service.concurrent.exception.ConcurrentException;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;

import java.util.IllegalFormatCodePointException;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

@AllArgsConstructor
@Log4j2
public class SemaphoreConcurrent implements Runnable {

    private CommonResource commonResource;
    private Semaphore semaphore;

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
            boolean acquired = false;
            try {
                semaphore.acquire();
                acquired = true;
                if (commonResource.getCounter() == limit) {
                    semaphore.release();
                    acquired = false;
                    return;
                }
                for (int i = 0; i < limit; i++) {
                    try {
                        if (commonResource.getMatrix().getElement(i, i) == 0) {
                            commonResource.getMatrix().setElement(i, i, (int) Thread.currentThread().getId());
                            log.info("thread #" + Thread.currentThread().getId()
                                    + " setting the value matrix[" + i + "][" + i + "]");
                            commonResource.setCounter(commonResource.getCounter() + 1);
                            semaphore.release();
                            acquired = false;
                            TimeUnit.MILLISECONDS.sleep(1);
                            break;
                        }
                    } catch (MatrixException e) {
                        throw new ConcurrentException("matrix operation error", e);
                    }
                }
            } catch (InterruptedException e) {
                throw new ConcurrentException("Thread has been interrupted", e);
            } finally {
                if (acquired){
                    semaphore.release();
                }
            }
        }
    }
}

