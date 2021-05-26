package by.training.task02branchingstatements.secret.model;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.List;

import static org.testng.Assert.*;

public class AccessLevelTest {

    @DataProvider(name = "WrongDataForAccessLevelCreation")
    public Object[][] getWrongDataForAccessLevelCreation(){
        return new Object[][]{
                {null,
                        List.of(
                                new DataBase("A"),
                                new DataBase("B"),
                                new DataBase("C")),
                        List.of("9583", "1747")
                },

                {"first",
                        null,
                        List.of("9583", "1747")},
                {"first",
                        List.of(
                                new DataBase("A"),
                                new DataBase("B"),
                                new DataBase("C")),
                        null
                },
                {"first",
                        Collections.EMPTY_LIST,
                        List.of("9583", "1747")
                },
                {"first",
                        List.of(
                                new DataBase("A"),
                                new DataBase("B"),
                                new DataBase("C")),
                        Collections.EMPTY_LIST
                }

        };
    }

    @Test(description = "negative scenario of AccessLevel creation",
    dataProvider = "WrongDataForAccessLevelCreation",
    expectedExceptions = IllegalArgumentException.class,
    expectedExceptionsMessageRegExp = "Wrong argument in the constructor parameters!")
    public void testWrongAccessLevelCreation(String accessLevelName,
                                             List<DataBase> dataBaseList,
                                             List<String> passwordsList){
        AccessLevel accessLevel = new AccessLevel(accessLevelName, dataBaseList, passwordsList);
    }
}