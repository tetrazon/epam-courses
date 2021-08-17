package by.training.task06multithreading.main;

import by.training.task06multithreading.entity.Matrix;
import by.training.task06multithreading.service.MatrixService;
import by.training.task06multithreading.service.concurrent.filler.Filler;
import by.training.task06multithreading.service.concurrent.filler.exception.FillerException;
import by.training.task06multithreading.service.concurrent.filler.impl.ConcurrentHashMapFiller;
import by.training.task06multithreading.service.concurrent.filler.impl.ReadWriteLockMatrixFiller;
import by.training.task06multithreading.service.concurrent.filler.impl.ReentrantMatrixFiller;
import by.training.task06multithreading.service.concurrent.filler.impl.SemaphoreMatrixFiller;
import by.training.task06multithreading.service.exception.MatrixServiceException;
import by.training.task06multithreading.service.impl.MatrixServiceImpl;
import lombok.extern.log4j.Log4j2;

import java.util.ArrayList;
import java.util.List;

@Log4j2
public class Runner {
    public static void main(String[] args) {

        MatrixService matrixService = new MatrixServiceImpl();
        Matrix matrix = null;
        try {
            matrix = matrixService.readFromFile("data/matrix.txt");
        } catch (MatrixServiceException e) {
            log.error("error reading matrix from file", e);
        }

        List<Filler<Matrix>> fillerList = new ArrayList<>();
        fillerList.add(new ReadWriteLockMatrixFiller());
        fillerList.add(new ReentrantMatrixFiller());
        fillerList.add(new SemaphoreMatrixFiller());
        fillerList.add(new ConcurrentHashMapFiller());

        for (Filler<Matrix> matrixFiller : fillerList) {
            try {
                log.info("initial matrix:\n" + matrix);
                matrixService.setZerosInDiagonal(matrix);
                log.info("zero diagonal matrix:\n" + matrix);
                matrix = matrixFiller.fill(matrix);
                log.info("filled matrix:\n" + matrix);
            } catch (MatrixServiceException e) {
                log.error("service matrix exception", e);
            } catch (FillerException e) {
                log.error("filler matrix exception", e);
            }
        }
    }
}
