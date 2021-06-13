package by.training.demo.dao.impl;

import by.training.demo.entity.Array;
import by.training.demo.exception.DaoException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class InMemoryArrayDaoTest {

    private InMemoryArrayDao inMemoryArrayDao = new InMemoryArrayDao();

    @DataProvider(name = "dataForCreateIntegerFromFile")
    public Object[][] createDataForCreateIntegerFromFile() {
        return new Object[][]{
                {"src/main/resources/test/intarr.txt", new Array(new Integer[] {1, 3, 4, 5, 7, 20, 44, 55, 78})},
        };
    }

    @DataProvider(name = "dataForCreateDoubleFromFile")
    public Object[][] createDataForCreateDoubleFromFile() {
        return new Object[][]{
                {"src/main/resources/test/doublearr.txt", new Array(new Double[] {1.0, 3.0, 4.0, 5.0, 7.0, 20.0, 44.0, 55.0, 78.0})},
        };
    }


    @Test(description = "positive scenario of Integer Array creation", dataProvider = "dataForCreateIntegerFromFile")
    public void testCreateIntegerFromFile(String filename, Array expected) throws DaoException {
        int arrIndex = inMemoryArrayDao.createIntegerFromFile(filename);
        Array<Integer> actual = inMemoryArrayDao.read(arrIndex).get();
        assertEquals(expected, actual);
    }

    @Test(description = "negative scenario of Integer Array creation", expectedExceptions = DaoException.class)
    public void testWrongCreateIntegerFromFile() throws DaoException {
        inMemoryArrayDao.createIntegerFromFile("src/main/resources/test/wrongdata.txt");
    }

    @Test(description = "positive scenario of Double Array creation", dataProvider = "dataForCreateDoubleFromFile")
    public void testCreateDoubleFromFile(String filename, Array expected) throws DaoException {
        int arrIndex = inMemoryArrayDao.createDoubleFromFile(filename);
        Array<Integer> actual = inMemoryArrayDao.read(arrIndex).get();
        assertEquals(expected, actual);
    }

    @Test(description = "negative scenario of Double Array creation", expectedExceptions = DaoException.class)
    public void testWrongCreateDoubleFromFile() throws DaoException {
        inMemoryArrayDao.createIntegerFromFile("src/main/resources/test/wrongdata.txt");
    }

}