package by.training.task08.service.validator;

import by.training.task08.service.creator.exception.FileNameCreatorException;
import by.training.task08.dao.handler.FlowerErrorHandler;
import by.training.task08.service.creator.FileNameCreator;
import by.training.task08.service.validator.exception.XmlValidatorException;
import lombok.extern.log4j.Log4j2;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

@Log4j2
public class XmlValidator {
    private static final String LANGUAGE = XMLConstants.W3C_XML_SCHEMA_NS_URI;
    public boolean validate(String schemaName, String fileName) throws XmlValidatorException {
        if (schemaName == null || fileName == null || schemaName.isEmpty() || fileName.isEmpty()){
            return false;
        }

        SchemaFactory factory = SchemaFactory.newInstance(LANGUAGE);
        final FileNameCreator fileNameCreator = new FileNameCreator();
        String schemaLocation;
        try {
            schemaLocation = fileNameCreator.createFileName(schemaName);
            // schema creation
            Schema schema = factory.newSchema(new File(schemaLocation));
            // creating a schema-based validator
            Validator validator = schema.newValidator();
            Source source = new StreamSource(new File(fileNameCreator.createFileName(fileName)));
            // document check
            validator.setErrorHandler(new FlowerErrorHandler());
            validator.validate(source);
        } catch (SAXException | IOException | FileNameCreatorException e) {
            log.error(fileName + " is not correct or valid");
            return false;
        }
        return true;
    }
}
