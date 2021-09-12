package by.training.task08.dao.handler;

public enum FlowerXmlTag {
    FLOWERS("flowers"),
    ITEMNAME("itemName"),
    IS_POISONING("isPoisoning"),
    FLOWER("flower"),
    NAME("name"),
    SOIL("soil"),
    ORIGIN("origin"),
    STCOLOR("stColor"),
    LFCOLOR("lfColor"),
    AVSIZE("avSize"),
    TEMPERATURE("temperature"),
    LIGHT("light"),
    WATER("water"),
    MULTIPLYING("multiplying"),
    GROWDATE("growDate"),
    VISUALPARAMS("visualParams"),
    GROWINGTIPS("growingTips");
    private String value;
    FlowerXmlTag(String value) {
        this.value = value;
    }
    public String getValue() {
        return value;
    }
}
