package by.training.task06multithreading.service.concurrent.filler.impl;

import by.training.task06multithreading.entity.CommonResource;
import by.training.task06multithreading.entity.Matrix;
import by.training.task06multithreading.service.concurrent.filler.exception.FillerException;
import by.training.task06multithreading.service.concurrent.filler.Filler;
import by.training.task06multithreading.service.concurrent.ReentrantConcurrent;
import lombok.extern.log4j.Log4j2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

@Log4j2
public class ReentrantMatrixFiller implements Filler<Matrix> {
    @Override
    public Matrix fill(Matrix matrix) throws FillerException {
        final int matrixSize = matrix.getHorizontalSize();
        final int threadsNumber = matrixSize > 3 ? matrixSize / 2 : 2;
        CommonResource commonResource = new CommonResource(matrix, 0);
        ReentrantLock lock = new ReentrantLock();
        List<Thread> threadList = new ArrayList<>();
        for (int i = 0; i < threadsNumber; i++) {
            threadList.add(new Thread(new ReentrantConcurrent(commonResource,lock)));
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
