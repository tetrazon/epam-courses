package by.training.task08.dao.parser;

import java.util.HashSet;

public class FlowerBuilderFactory {
    private enum TypeParser {
        SAX, DOM
    }
    private FlowerBuilderFactory() {
    }
    public static AbstractFlowersBuilder createFlowerBuilder(String typeParser) {
        // insert parser name validation
        TypeParser type = TypeParser.valueOf(typeParser.toUpperCase());
        switch (type) {
            case DOM:
                return new FlowersDomBuilder(new HashSet<>());
            case SAX :  return new FlowersSaxBuilder(new HashSet<>());
            default: throw new EnumConstantNotPresentException(
                    type.getDeclaringClass(), type.name());
        }
    }

}
