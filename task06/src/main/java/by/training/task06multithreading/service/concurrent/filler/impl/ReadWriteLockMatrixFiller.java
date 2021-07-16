package by.training.task06multithreading.service.concurrent.filler.impl;

import by.training.task06multithreading.entity.CommonResource;
import by.training.task06multithreading.entity.Matrix;
import by.training.task06multithreading.service.concurrent.ReadWriteLockConcurrent;
import by.training.task06multithreading.service.concurrent.filler.Filler;
import by.training.task06multithreading.service.concurrent.filler.exception.FillerException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockMatrixFiller implements Filler<Matrix> {

    private static final Logger logger = LogManager.getLogger(ReadWriteLockMatrixFiller.class);

    @Override
    public Matrix fill(Matrix matrix) throws FillerException {
        final int matrixSize = matrix.getHorizontalSize();
        final int threadsNumber = matrixSize > 3 ? matrixSize / 2 : 2;
        CommonResource commonResource = new CommonResource(matrix, 0);
        ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
        List<Thread> threadList = new ArrayList<>();

        for (int i = 0; i < threadsNumber; i++) {
            threadList.add(new Thread(new ReadWriteLockConcurrent(commonResource,lock)));
        }

        threadList.forEach(Thread::start);

        while (commonResource.getCounter()!= commonResource.getMatrix().getHorizontalSize()){
            try {
                TimeUnit.MILLISECONDS.sleep(10);
            } catch (InterruptedException e) {
                throw new FillerException("Thread has been interrupted", e);
            }

        }

        logger.info("filled matrix:\n" + matrix);
        return matrix;
    }
}
