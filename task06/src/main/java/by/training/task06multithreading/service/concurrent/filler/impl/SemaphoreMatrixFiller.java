package by.training.task06multithreading.service.concurrent.filler.impl;

import by.training.task06multithreading.entity.CommonResource;
import by.training.task06multithreading.entity.Matrix;
import by.training.task06multithreading.service.concurrent.SemaphoreConcurrent;
import by.training.task06multithreading.service.concurrent.filler.Filler;
import by.training.task06multithreading.service.concurrent.filler.exception.FillerException;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

@Log4j2
public class SemaphoreMatrixFiller implements Filler<Matrix> {
    @SneakyThrows
    @Override
    public Matrix fill(Matrix matrix) {
        final int matrixSize = matrix.getHorizontalSize();
        final int threadsNumber = matrixSize > 3 ? matrixSize / 2 : 2;
        CommonResource commonResource = new CommonResource(matrix, 0);
        Semaphore semaphore = new Semaphore(1);
        List<Thread> threadList = new ArrayList<>();
        for (int i = 0; i < threadsNumber; i++) {
            threadList.add(new Thread(new SemaphoreConcurrent(commonResource,semaphore)));
        }

        threadList.forEach(Thread::start);

        while (commonResource.getCounter()!= commonResource.getMatrix().getHorizontalSize()){
            try {
                TimeUnit.MILLISECONDS.sleep(10);
            } catch (InterruptedException e) {
                throw new FillerException("Thread has been interrupted", e);

            }

        }
        log.info("filled matrix:\n" + matrix);
        return matrix;
    }
}
