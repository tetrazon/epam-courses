package by.training.demo.service.impl;

import by.training.demo.exception.ServiceException;
import by.training.demo.service.ArrayService;
import by.training.demo.service.factory.ServiceFactory;
import by.training.demo.service.sorting.ArraySorting;
import by.training.demo.service.sorting.impl.BubbleSort;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ArrayServiceImplTest {

    private ArrayService arrayService = ServiceFactory.getInstance().getArrayService();

    @DataProvider(name = "wrongDataForCreateFromFile")
    public Object[][] createWrongDataForCreateFromFile() {
        return new Object[][]{
                {"src/main/resources/test/wrong.txt"},
                {""},
                {null}};
    }

    @DataProvider(name = "wrongDataForCreateFromString")
    public Object[][] createWrongDataForCreateFromString() {
        return new Object[][]{
                {"a a b f c d"},
                {""},
                {null}};
    }

    @DataProvider(name = "wrongDataForIndex")
    public Object[][] createWrongDataForIndex() {
        return new Object[][]{
                {"1.abc"},
                {""},
                {null}};
    }

    @DataProvider(name = "wrongDataForSort")
    public Object[][] createWrongDataForSort() {
        return new Object[][]{
                {new BubbleSort(), "0", true},
                {new BubbleSort(), "-1", true},
                {null, "0", true},
                };
    }

    @Test(description = "negative scenario for createInteger()",
    dataProvider = "wrongDataForCreateFromString",
    expectedExceptions = ServiceException.class)
    public void testCreateInteger(String request) throws ServiceException {
        arrayService.createInteger(request);

    }

    @Test(description = "negative scenario for createDouble()",
            dataProvider = "wrongDataForCreateFromString",
            expectedExceptions = ServiceException.class)
    public void testCreateDouble(String request) throws ServiceException {
        arrayService.createDouble(request);
    }

    @Test(description = "negative scenario for createIntegerFromFile()",
            dataProvider = "wrongDataForCreateFromFile",
            expectedExceptions = ServiceException.class)
    public void testCreateIntegerFromFile(String request) throws ServiceException {
        arrayService.createIntegerFromFile(request);
    }

    @Test(description = "negative scenario for createDouble()",
            dataProvider = "wrongDataForCreateFromFile",
            expectedExceptions = ServiceException.class)
    public void testCreateDoubleFromFile(String request) throws ServiceException {
        arrayService.createDoubleFromFile(request);
    }

    @Test(description = "negative scenario for read()",
            dataProvider = "wrongDataForIndex",
            expectedExceptions = ServiceException.class)
    public void testRead(String request) throws ServiceException {
        arrayService.read(request);
    }

    @Test(description = "negative scenario for delete()",
            dataProvider = "wrongDataForIndex",
            expectedExceptions = ServiceException.class)
    public void testDelete(String request) throws ServiceException {
        arrayService.delete(request);
    }

    @Test(description = "negative scenario for sort()",
            dataProvider = "wrongDataForSort",
            expectedExceptions = ServiceException.class)
    public void testSort(ArraySorting arraySorting, String arrayIndex, boolean isAscending) throws ServiceException {
        arrayService.sort(arraySorting,arrayIndex, isAscending);
    }
}