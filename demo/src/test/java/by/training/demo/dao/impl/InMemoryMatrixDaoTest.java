package by.training.demo.dao.impl;

import by.training.demo.dao.MatrixDao;
import by.training.demo.dao.factory.DaoFactory;
import by.training.demo.entity.Matrix;
import by.training.demo.exception.DaoException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Optional;

import static org.testng.Assert.*;

public class InMemoryMatrixDaoTest {
    private MatrixDao matrixDao = DaoFactory.getInstance().getMatrixDao();

    @DataProvider(name = "dataForCreateFromFile")
    public Object[][] createDataForCreateFromFile() {
        return new Object[][]{
                {"src/main/resources/test/matrix.txt",
                        new Matrix(new int[][]{
                                {1,2,3},
                                {1,2,3},
                                {1,2,4},
                        })}
        };
    }

    @DataProvider(name = "dataForAdd")
    public Object[][] createDataAdd() {
        return new Object[][]{{
            "src/main/resources/test/matrixall1.txt",
                "src/main/resources/test/matrixall1.txt",
                "src/main/resources/test/martixall2.txt"}};
    }

    @DataProvider(name = "dataForMultiply")
    public Object[][] createDataForMultiply() {
        return new Object[][]{{
                "src/main/resources/test/matrixall1.txt",
                "src/main/resources/test/matrixall1.txt",
                "src/main/resources/test/matrixall1.txt"}};
    }

    @DataProvider(name = "dataForSubtract")
    public Object[][] createDataForSubtract() {
        return new Object[][]{{
                "src/main/resources/test/matrixall1.txt",
                "src/main/resources/test/matrixall1.txt",
                "src/main/resources/test/matrixall0.txt"}};
    }

    @DataProvider(name = "dataForTranspose")
    public Object[][] createDataForTranspose() {
        return new Object[][]{{
                "src/main/resources/test/matrix-for-transpose.txt",
                "src/main/resources/test/transposed-matrix.txt"}};
    }


    @Test(description = "positive scenario of Matrix creation")
    public void testCreate() throws DaoException {
        int[][] intMatrix = {
                {1, 2, 3},
                {1, 2, 3},
                {1, 2, 3},
        };
        Matrix expected = new Matrix(intMatrix);

        int actualMatrixIndex = matrixDao.create(intMatrix);
        Optional<Matrix> actual = matrixDao.read(actualMatrixIndex);
        assertTrue(actual.isPresent());
        assertEquals(actual.get(), expected);
    }

    @Test(description = "positive scenario of random Matrix creation")
    public void testCreateRandom() throws DaoException {
        int index = matrixDao.createRandom(3,3, 0, 10);
        Optional<Matrix> actual = matrixDao.read(index);
        assertTrue(actual.isPresent());
        Matrix actualMatrix = actual.get();
        assertEquals(3, actualMatrix.getHorizontalSize());
        assertEquals(3, actualMatrix.getVerticalSize());
    }

    @Test(description = "positive scenario of Matrix creation from file",
    dataProvider = "dataForCreateFromFile")
    public void testCreateFromFile(String filename, Matrix expected) throws DaoException {
        int index = matrixDao.createFromFile(filename);
        Optional<Matrix> actualOptional = matrixDao.read(index);
        assertTrue(actualOptional.isPresent());
        Matrix actual = actualOptional.get();
        assertEquals(actual, expected);

    }


    @Test(description = "positive scenario matrices adding",
            dataProvider = "dataForAdd")
    public void testAdd(String filenameA, String filenameB, String filenameExpected) throws DaoException {
        int indexA = matrixDao.createFromFile(filenameA);
        int indexB = matrixDao.createFromFile(filenameB);
        int indexExpected = matrixDao.createFromFile(filenameExpected);
        Matrix actual = matrixDao.add(indexA, indexB);
        Optional<Matrix> expected = matrixDao.read(indexExpected);
        assertTrue(expected.isPresent());
        assertEquals(actual, expected.get());
    }

    @Test(description = "positive scenario matrices multiplying",
            dataProvider = "dataForMultiply")
    public void testMultiply(String filenameA, String filenameB, String filenameExpected) throws DaoException {
        int indexA = matrixDao.createFromFile(filenameA);
        int indexB = matrixDao.createFromFile(filenameB);
        int indexExpected = matrixDao.createFromFile(filenameExpected);
        Matrix actual = matrixDao.multiply(indexA, indexB);
        Optional<Matrix> expected = matrixDao.read(indexExpected);
        assertTrue(expected.isPresent());
        assertEquals(actual, expected.get());
    }

    @Test(description = "positive scenario matrices subtracting",
            dataProvider = "dataForSubtract")
    public void testSubtract(String filenameA, String filenameB, String filenameExpected) throws DaoException {
        int indexA = matrixDao.createFromFile(filenameA);
        int indexB = matrixDao.createFromFile(filenameB);
        int indexExpected = matrixDao.createFromFile(filenameExpected);
        Matrix actual = matrixDao.subtract(indexA, indexB);
        Optional<Matrix> expected = matrixDao.read(indexExpected);
        assertTrue(expected.isPresent());
        assertEquals(actual, expected.get());
    }

    @Test(description = "positive scenario for matrix transpose",
            dataProvider = "dataForTranspose")
    public void testTranspose(String filenameInit, String filenameExpected) throws DaoException {
        int indexInit = matrixDao.createFromFile(filenameInit);
        int indexExpected = matrixDao.createFromFile(filenameExpected);
         Matrix actual = matrixDao.transpose(indexInit);
         assertEquals(actual, matrixDao.read(indexExpected).get());
    }
}