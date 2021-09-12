package by.training.task08.dao.parser;

import by.training.task08.entity.Flower;
import by.training.task08.service.creator.FileNameCreator;
import by.training.task08.dao.handler.FlowerErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.XMLConstants;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.IOException;
import java.util.Set;

public class FlowersSaxBuilder extends AbstractFlowersBuilder{
    private FlowerHandler handler = new FlowerHandler();
    private XMLReader reader;
    public FlowersSaxBuilder(Set<Flower> flowers) {
        super(flowers);
        // reader configuration
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            // create schema
            String constant = XMLConstants.W3C_XML_SCHEMA_NS_URI;
            SchemaFactory xsdFactory = SchemaFactory.newInstance(constant);
            Schema schema = xsdFactory.newSchema(new File(new FileNameCreator().createFileName("data/orangery.xsd")));
            // set schema
            factory.setNamespaceAware(true);
            factory.setValidating(false);
            factory.setSchema(schema);
            SAXParser saxParser = factory.newSAXParser();
            reader = saxParser.getXMLReader();
        } catch (ParserConfigurationException | SAXException e) {
            e.printStackTrace(); // log
        }
        reader.setErrorHandler(new FlowerErrorHandler());
        reader.setContentHandler(handler);
    }
    public void buildSetFlowers(String filename) {
        try {
            reader.parse(filename);
        } catch (IOException | SAXException e) {
            e.printStackTrace(); // log
        }
        flowers = handler.getFlowers();
    }
}