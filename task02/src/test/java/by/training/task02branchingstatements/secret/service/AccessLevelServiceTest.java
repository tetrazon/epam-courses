package by.training.task02branchingstatements.secret.service;

import by.training.task02branchingstatements.secret.model.AccessLevel;
import by.training.task02branchingstatements.secret.model.DataBase;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Optional;

import static org.testng.Assert.*;

public class AccessLevelServiceTest {

    @DataProvider(name = "wrongDataForGetAccessLevelByPassword")
    public Object[][] createWrongDataForGetAccessLevelByPassword() {
        List<AccessLevel> accessLevelList = List.of(new AccessLevel(
                "first",
                List.of(
                        new DataBase("A"),
                        new DataBase("B"),
                        new DataBase("C")),
                List.of("9583", "1747")),
                new AccessLevel(
                        "second",
                        List.of(new DataBase("B"),
                                new DataBase("C")),
                        List.of("3331", "7992")),
                new AccessLevel("third",
                        List.of(new DataBase("A")),
                        List.of("9455", "8997")));
        return new Object[][]{
                {accessLevelList, "1111"},
                {accessLevelList, "1234"},
                {accessLevelList, "3421"},
                };
    }

    @DataProvider(name = "dataForGetAccessLevelByPassword")
    public Object[][] createDataForGetAccessLevelByPassword() {
        List<AccessLevel> accessLevelList = List.of(new AccessLevel(
                        "first",
                        List.of(
                                new DataBase("A"),
                                new DataBase("B"),
                                new DataBase("C")),
                        List.of("9583", "1747")),
                new AccessLevel(
                        "second",
                        List.of(new DataBase("B"),
                                new DataBase("C")),
                        List.of("3331", "7992")),
                new AccessLevel("third",
                        List.of(new DataBase("A")),
                        List.of("9455", "8997")));
        return new Object[][]{
                {accessLevelList, "9583", "first"},
                {accessLevelList, "1747", "first"},
                {accessLevelList, "3331", "second"},
                {accessLevelList, "7992", "second"},
                {accessLevelList, "9455", "third"},
                {accessLevelList, "8997", "third"}
        };
    }

    @Test(description = "Positive scenario of the getAccessLevelByPassword",
            dataProvider = "dataForGetAccessLevelByPassword")
    public void testGetAccessLevelByPassword(List<AccessLevel> accessLevelList,
                                             String password,
                                             String expectedLevelName) {
        AccessLevelService accessLevelService = new AccessLevelService();
        Optional<String> optionalActualAccessLevel = accessLevelService.
                getAccessLevelByPassword(accessLevelList, password);
        assertTrue(optionalActualAccessLevel.isPresent() &&
                optionalActualAccessLevel.get().equals(expectedLevelName));

    }

    @Test(description = "Negative scenario of the getAccessLevelByPassword",
            dataProvider = "wrongDataForGetAccessLevelByPassword")
    public void testWrongGetAccessLevelByPassword(List<AccessLevel> accessLevelList,
                                             String password) {
        AccessLevelService accessLevelService = new AccessLevelService();
        Optional<String> optionalActualAccessLevel = accessLevelService.
                getAccessLevelByPassword(accessLevelList, password);
        assertTrue(optionalActualAccessLevel.isEmpty());

    }

}