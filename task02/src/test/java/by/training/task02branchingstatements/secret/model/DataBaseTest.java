package by.training.task02branchingstatements.secret.model;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class DataBaseTest {

    @DataProvider(name = "WrongDataForDataBaseCreation")
    public Object[][] getWrongDataForDataBaseCreation(){
        return new Object[][]{
                {null},
                {""}
        };
    }

    @Test(description = "Negative scenario for Database creation",
    dataProvider = "WrongDataForDataBaseCreation",
    expectedExceptions = IllegalArgumentException.class,
    expectedExceptionsMessageRegExp = "DataBase name must be not null and not empty")
    public void testWrongDatabaseCreation(String databaseName){
        DataBase dataBase = new DataBase(databaseName);
    }
}