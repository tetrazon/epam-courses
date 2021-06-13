package by.training.demo.dao.factory;

import by.training.demo.dao.ArrayDao;
import by.training.demo.dao.MatrixDao;
import by.training.demo.dao.impl.InMemoryArrayDao;
import by.training.demo.dao.impl.InMemoryMatrixDao;

public final class DaoFactory {

    private static final DaoFactory instance = new DaoFactory();

    private final MatrixDao matrixDaoImpl = new InMemoryMatrixDao();
    private final ArrayDao arrayDaoImpl = new InMemoryArrayDao();

    private DaoFactory(){}

    public static DaoFactory getInstance(){
        return instance;
    }

    public ArrayDao getArrayDao() {
        return arrayDaoImpl;
    }

    public MatrixDao getMatrixDao() {
        return matrixDaoImpl;
    }
}
