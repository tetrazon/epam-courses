package by.training.task06multithreading.service.concurrent.filler.impl;

import by.training.task06multithreading.entity.Matrix;
import by.training.task06multithreading.entity.exception.MatrixException;
import by.training.task06multithreading.service.concurrent.ReadWriteLockConcurrent;
import by.training.task06multithreading.service.concurrent.filler.Filler;
import by.training.task06multithreading.service.concurrent.filler.exception.FillerException;
import lombok.extern.log4j.Log4j2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

@Log4j2
public class ConcurrentHashMapSolution implements Filler<Matrix> {
    private Matrix matrix;
    private ConcurrentHashMap<String, Integer> concurrentHashMap = new ConcurrentHashMap<>();

    @Override
    public Matrix fill(Matrix matrix) throws FillerException {
        final int matrixSize = matrix.getHorizontalSize();
        final int threadsNumber = matrixSize > 3 ? matrixSize / 2 : 2;

        List<Thread> threadList = new ArrayList<>();

        for (int i = 0; i < threadsNumber; i++) {
            threadList.add(new Thread(() -> {
                log.info("thread #" + (int) Thread.currentThread().getId() + " started");
                for (int j = 0; j < matrixSize; j++) {
                    if (!concurrentHashMap.containsKey(j)) {
                            //matrix.setElement(j, j, (int) Thread.currentThread().getId());
                        concurrentHashMap.put("" + j + j, (int) Thread.currentThread().getId());
                            log.info("thread #" + Thread.currentThread().getId()
                                    + " setting the value matrix[" + j + "][" + j + "]");
                    }
                }
            } ));
        }

        threadList.forEach(Thread::start);

        while (concurrentHashMap.size() != matrixSize){
            try {
                TimeUnit.MILLISECONDS.sleep(10);
            } catch (InterruptedException e) {
                throw new FillerException("Thread has been interrupted", e);
            }
        }

        for (int i = 0; i < matrixSize; i++) {
            try {
                matrix.setElement(i, i, concurrentHashMap.get("" + i + i));
            } catch (MatrixException e) {
                throw new FillerException("Matrix operation error", e);
            }
        }

        log.info("filled matrix:\n" + matrix);
        return matrix;
    }
}
