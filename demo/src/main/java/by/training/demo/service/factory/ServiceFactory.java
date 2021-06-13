package by.training.demo.service.factory;

import by.training.demo.service.ArrayService;
import by.training.demo.service.MatrixService;
import by.training.demo.service.impl.ArrayServiceImpl;
import by.training.demo.service.impl.MatrixServiceImpl;

public final class ServiceFactory {

    private static ServiceFactory instance = new ServiceFactory();

    private ArrayService arrayService = new ArrayServiceImpl();
    private MatrixService matrixService = new MatrixServiceImpl();

    private ServiceFactory(){}

    public static ServiceFactory getInstance() {
        return instance;
    }

    public ArrayService getArrayService() {
        return arrayService;
    }

    public MatrixService getMatrixService() {
        return matrixService;
    }


}
