package by.training.demo.dao.impl;

import by.training.demo.dao.MatrixDao;
import by.training.demo.entity.Matrix;
import by.training.demo.exception.DaoException;
import by.training.demo.exception.MatrixException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class InMemoryMatrixDao implements MatrixDao {

    private static final Logger logger = LogManager.getLogger(InMemoryMatrixDao.class);
    private List<Matrix> storage = new ArrayList<>();

    @Override
    public int create(int[][] intMatrix) {
        Matrix matrix = new Matrix(intMatrix);
        storage.add(matrix);
        return storage.indexOf(matrix);
    }

    /**
     * @param columns size of columns of matrix
     * @param rows size of rows of matrix
     * @param minValue min value of matrix
     * @param maxValue max value of matrix
     * @return index of created matrix
     * @throws DaoException
     */
    @Override
    public int createRandom(int columns, int rows, int minValue, int maxValue) throws DaoException {
        Matrix matrix = new Matrix(new int[rows][columns]);
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < columns; j++) {
                try {
                    int value = (int) ((Math.random() * (maxValue - minValue)) + minValue);
                    matrix.setElement(i, j, value);
                } catch (MatrixException e) {
                    // log: exception impossible
                }
            }
        }
        storage.add(matrix);
        return storage.indexOf(matrix);
    }

    @Override
    public int createFromFile(String fileName) throws DaoException {
        int rows;
        int columns;
        int [][] intMatrix;
        List<String[]> stringMatrix = new ArrayList<>();
        try(Scanner sc = new Scanner(new BufferedReader(new FileReader(fileName)))) {
            while(sc.hasNextLine()) {
                stringMatrix.add(sc.nextLine().trim().split(" "));
            }
        } catch (FileNotFoundException e) {
            logger.error("Error file reading", e);
        }

        rows = stringMatrix.size();
        columns = stringMatrix.get(0).length;

        //check if it right matrix
        for (String[] row : stringMatrix) {
            if (row.length != columns){
                logger.error("wrong input data: column mismatch");
                throw new DaoException("wrong input data: column mismatch");
            }
        }

        intMatrix = new int[rows][columns];

        //create int matrix
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                try {
                    intMatrix[i][j] = Integer.parseInt(stringMatrix.get(i)[j]);
                } catch (NumberFormatException e){
                    logger.error("Number format exception", e);
                }
            }
        }

        return create(intMatrix);
    }

    @Override
    public Optional<Matrix> read(int index) throws DaoException {
        if (!isInRange(index)){
            throw new DaoException("wrong index");
        }
        return Optional.of(storage.get(index));
    }

    /**
     * @param indexA index of A
     * @param indexB index of B
     * @return the result of A + B matrices
     * @throws DaoException
     */
    @Override
    public Matrix add(int indexA, int indexB) throws DaoException {
        Optional<Matrix> matrixA = read(indexA);
        Optional<Matrix> matrixB = read(indexB);
        Matrix result = null;
        if (matrixA.isPresent() && matrixB.isPresent()){
            try {
                result = add(matrixA.get(), matrixB.get());
            } catch (MatrixException e) {
                // log: exception impossible
            }
        } else {
            throw new DaoException("wrong index!");
        }
        return result;
    }

    /**
     * @param p first matrix
     * @param q second matrix
     * @return the result of p + q matrices
     * @throws MatrixException
     */
    private Matrix add(Matrix p, Matrix q) throws MatrixException {
        int pRow = p.getVerticalSize();
        int pCol = p.getHorizontalSize();
        int qRow = q.getVerticalSize();
        int qCol = q.getHorizontalSize();
        int tempSum;
        if (pRow != qRow || pCol != qCol) {
            throw new MatrixException("incompatible matrices");
        }
        Matrix result = new Matrix(pRow, pCol);
        try {
            for (int i = 0; i < pRow; i++) {
                for (int j = 0; j < pCol; j++) {
                    tempSum = p.getElement(i, j) + q.getElement(i, j);
                    result.setElement(i, j, tempSum);
                }

            }
        } catch (MatrixException e) {
            // log: exception impossible
        }
        return result;
    }

    /**
     * @param indexA index of matrix A
     * @param indexB index of matrix B
     * @return matrix A * matrix B
     * @throws DaoException
     */
    @Override
    public Matrix multiply(int indexA, int indexB) throws DaoException {
        Optional<Matrix> matrixA = read(indexA);
        Optional<Matrix> matrixB = read(indexB);
        Matrix result = null;
        if (matrixA.isPresent() && matrixB.isPresent()){
            try {
                result = multiply(matrixA.get(), matrixB.get());
            } catch (MatrixException e) {
                // log: exception impossible
            }
        } else {
            throw new DaoException("wrong index!");
        }
        return result;
    }

    /**
     *
     * @param p matrix p
     * @param q matrix q
     * @return p * q
     * @throws MatrixException
     */
    private Matrix multiply(Matrix p, Matrix q) throws MatrixException {
        int v = p.getVerticalSize();
        int h = q.getHorizontalSize();
        int controlSize = p.getHorizontalSize();
        if (controlSize != q.getVerticalSize()) {
            throw new MatrixException("incompatible matrices");
        }
        Matrix result = new Matrix(v, h);
        try {
            for (int i = 0; i < v; i++) {
                for (int j = 0; j < h; j++) {
                    int value = 0;
                    for (int k = 0; k < controlSize; k++) {
                        value = p.getElement(i, k) * q.getElement(k, j);
                    }
                    result.setElement(i, j, value);
                }
            }
        } catch (MatrixException e) {
            // log: exception impossible
        }
        return result;
    }

    /**
     *
     * @param indexA
     * @param indexB
     * @return matrixA - matrixB
     * @throws DaoException
     */
    @Override
    public Matrix subtract(int indexA, int indexB) throws DaoException {
        Optional<Matrix> matrixA = read(indexA);
        Optional<Matrix> matrixB = read(indexB);
        Matrix result = null;
        if (matrixA.isPresent() && matrixB.isPresent()){
            try {
                result = subtract(matrixA.get(), matrixB.get());
            } catch (MatrixException e) {
                // log: exception impossible
            }
        } else {
            throw new DaoException("wrong index!");
        }
        return result;
    }

    /**
     *
     * @param p Matrix p
     * @param q Matrix q
     * @return p - q
     * @throws MatrixException
     */
    private Matrix subtract(Matrix p, Matrix q) throws MatrixException {
        int pRow = p.getVerticalSize();
        int pCol = p.getHorizontalSize();
        int qRow = q.getVerticalSize();
        int qCol = q.getHorizontalSize();
        int tempSum;
        if (pRow != qRow || pCol != qCol) {
            throw new MatrixException("incompatible matrices");
        }
        Matrix result = new Matrix(pRow, pCol);
        try {
            for (int i = 0; i < pRow; i++) {
                for (int j = 0; j < pCol; j++) {
                    tempSum = p.getElement(i, j) - q.getElement(i, j);
                    result.setElement(i, j, tempSum);
                }

            }
        } catch (MatrixException e) {
            // log: exception impossible
        }
        return result;
    }

    /**
     *
     * @param index index of matrix to transpose
     * @return transposed Matrix
     * @throws DaoException
     */
    @Override
    public Matrix transpose(int index) throws DaoException {
        Optional<Matrix> matrix = read(index);
        Matrix result = null;
        if (matrix.isPresent()){
            try {
                result = transpose(matrix.get());
            } catch (MatrixException e) {
                // log: exception impossible
            }
        } else {
            throw new DaoException("wrong index!");
        }
        return result;
    }

    /**
     *
     * @param m
     * @return transposed Matrix m
     * @throws MatrixException
     */
    private Matrix transpose(Matrix m) throws MatrixException {
        int transposedRow = m.getHorizontalSize();
        int transposedCol = m.getVerticalSize();
        int[][] transposed = new int[transposedRow][transposedCol];
        for (int i = 0; i < transposedRow; i++) {
            for (int j = 0; j < transposedCol; j++) {
                transposed[i][j] = m.getElement(j, i);
            }
        }

        return new Matrix(transposed);
    }

    private boolean isInRange(int index) {
        return index >= 0 && index < storage.size();
    }
}
