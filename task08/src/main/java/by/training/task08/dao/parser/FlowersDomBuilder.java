package by.training.task08.dao.parser;

import by.training.task08.entity.Flower;
import by.training.task08.service.creator.FileNameCreator;
import by.training.task08.dao.parser.exception.ParserException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.util.Set;

public class FlowersDomBuilder extends AbstractFlowersBuilder{
    private DocumentBuilder docBuilder;
    public FlowersDomBuilder(Set<Flower> flowers) {
        super(flowers);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            docBuilder = factory.newDocumentBuilder();
            // create schema
            String constant = XMLConstants.W3C_XML_SCHEMA_NS_URI;
            SchemaFactory xsdFactory = SchemaFactory.newInstance(constant);
            Schema schema = xsdFactory.newSchema(new File(new FileNameCreator().createFileName("data/orangery.xsd")));
            // set schema
            factory.setNamespaceAware(true);
            factory.setValidating(false);
            factory.setSchema(schema);
        } catch (ParserConfigurationException | SAXException e) {
            e.printStackTrace(); // log
        }
    }

    public void buildSetFlowers(String filename) {
        Document doc;
        try {
            doc = docBuilder.parse(filename);
            Element root = doc.getDocumentElement();
            NodeList flowersList = root.getElementsByTagName("flower");
            for (int i = 0; i < flowersList.getLength(); i++) {
                Element flowerElement = (Element) flowersList.item(i);
                Flower flower = buildFlower(flowerElement);
                flowers.add(flower);
            }
        } catch (IOException | SAXException | ParserException e) {
            e.printStackTrace(); // log
        }
    }
    private Flower buildFlower(Element flowerElement) throws ParserException {
        Flower flower = new Flower();
        // add null check
        if (flowerElement == null){
            throw new ParserException("null element");
        }
        flower.setItemName(flowerElement.getAttribute("itemName"));
        final String isPoisoning = flowerElement.getAttribute("isPoisoning");
        if (isPoisoning!= null){
            flower.setPoisoning(Boolean.parseBoolean(isPoisoning));
        }
        flower.setOrigin(getElementTextContent(flowerElement,"origin"));
        flower.setSoil(getElementTextContent(flowerElement,"soil"));
        flower.setMultiplying(getElementTextContent(flowerElement,"multiplying"));
        flower.setGrowDate(Date.valueOf(getElementTextContent(flowerElement,"growDate")));
        flower.setName(getElementTextContent(flowerElement,"name"));

        Flower.VisualParams visualParams = flower.getVisualParams();
        Element visualParamsElement =
                (Element) flowerElement.getElementsByTagName("visualParams").item(0);
        visualParams.setAvSize(getElementTextContent(visualParamsElement, "avSize"));
        visualParams.setStColor(getElementTextContent(visualParamsElement, "stColor"));
        visualParams.setLfColor(getElementTextContent(visualParamsElement, "lfColor"));

        Flower.GrowingTips growingTips = flower.getGrowingTips();
        Element growingTipsElement =
                (Element) flowerElement.getElementsByTagName("growingTips").item(0);
        growingTips.setTemperature(Double.parseDouble(getElementTextContent(growingTipsElement, "temperature")));
        growingTips.setLight(Boolean.parseBoolean(getElementTextContent(growingTipsElement, "light")));
        growingTips.setWater(Double.parseDouble(getElementTextContent(growingTipsElement, "water")));
        return flower;
    }
    // get the text content of the tag
    private static String getElementTextContent(Element element, String elementName) {
        NodeList nList = element.getElementsByTagName(elementName);
        Node node = nList.item(0);
        String text = node.getTextContent();
        return text;
    }
}
