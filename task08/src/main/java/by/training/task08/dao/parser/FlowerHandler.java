package by.training.task08.dao.parser;

import by.training.task08.entity.Flower;
import by.training.task08.dao.handler.FlowerXmlTag;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.sql.Date;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

public class FlowerHandler extends DefaultHandler {
    private Set<Flower> flowers;
    private Flower current;
    private FlowerXmlTag currentXmlTag;
    private EnumSet<FlowerXmlTag> withText;
    private static final String ELEMENT_FLOWER = "flower";
    public FlowerHandler() {
        flowers = new HashSet<>();
        withText = EnumSet.range(FlowerXmlTag.FLOWER, FlowerXmlTag.GROWDATE);
    }
    public Set<Flower> getFlowers() {
        return flowers;
    }
    public void startElement(String uri, String localName, String qName, Attributes attrs) {
        if (ELEMENT_FLOWER.equals(qName)) {
            current = new Flower();
            current.setItemName(attrs.getValue(FlowerXmlTag.ITEMNAME.getValue()));
            if (attrs.getLength() == 2) {
                current.setPoisoning(Boolean.parseBoolean(attrs.getValue(FlowerXmlTag.IS_POISONING.getValue())));
            }
        } else {
            FlowerXmlTag temp = FlowerXmlTag.valueOf(qName.toUpperCase());
            if (withText.contains(temp)) {
                currentXmlTag = temp;
            }
        }
    }
    public void endElement(String uri, String localName, String qName) {
        if (ELEMENT_FLOWER.equals(qName)) {
            flowers.add(current);
        }
    }
    public void characters(char[] ch, int start, int length) {
        String data = new String(ch, start, length).trim();
        if (currentXmlTag!= null) {
            switch (currentXmlTag) {
                case NAME:
                    current.setName(data);
                    break;
                case SOIL :
                    current.setSoil(data);
                    break;
                case ORIGIN :
                    current.setOrigin(data);
                    break;
                case STCOLOR :
                    current.getVisualParams().setStColor(data);
                    break;
                case LFCOLOR :
                    current.getVisualParams().setLfColor(data);
                    break;
                case AVSIZE :
                    current.getVisualParams().setAvSize(data);
                    break;
                case TEMPERATURE :
                    current.getGrowingTips().setTemperature(Double.parseDouble(data));
                    break;
                case LIGHT :
                    current.getGrowingTips().setLight(Boolean.parseBoolean(data));
                    break;
                case WATER :
                    current.getGrowingTips().setWater(Double.parseDouble(data));
                    break;
                case MULTIPLYING :
                    current.setMultiplying(data);
                    break;
                case GROWDATE :
                    current.setGrowDate(Date.valueOf(data));
                    break;
                default : throw new EnumConstantNotPresentException(
                        currentXmlTag.getDeclaringClass(), currentXmlTag.name());
            }
        }
        currentXmlTag = null;
    }
}
