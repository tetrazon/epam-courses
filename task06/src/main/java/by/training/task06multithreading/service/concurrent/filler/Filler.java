package by.training.task06multithreading.service.concurrent.filler;

import by.training.task06multithreading.service.concurrent.filler.exception.FillerException;

public interface Filler<T> {
    T fill(T t) throws FillerException;
}
