package by.training.task08.service.validator;

import by.training.task08.service.validator.exception.XmlValidatorException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class XmlValidatorTest {
    private XmlValidator xmlValidator = new XmlValidator();

    @DataProvider(name = "dataForTestValidate")
    public Object[][] createDataForTestValidate() {

        return new Object[][]{
                {"data/orangery.xsd", "data/orangery.xml"}};
    }

    @DataProvider(name = "wrongDataForTestValidate")
    public Object[][] createWrongDataForTestValidate() {

        return new Object[][]{
                {"", "data/orangery.xml"},
                {null, "data/orangery.xml"},
                {"", null},
                {"wrong.xsd", "wrong.xml"}
        };
    }

    @Test(description = "positive scenario of testValidate() method",
            dataProvider = "wrongDataForTestValidate")
    public void testValidate(String schemaName, String fileName) throws XmlValidatorException {
        assertFalse(xmlValidator.validate(schemaName,fileName));
    }
}