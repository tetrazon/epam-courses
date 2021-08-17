package by.training.task07informationhandling.manager;

public enum TypeManager {
    WORD("%s"),
    LEXEME("%s"),
    SENTENCE("%s "),
    PARAGRAPH(" %s"),
    TEXT("    %s\n");

    private String format;

    TypeManager(String delimiter) {
        this.format = delimiter;
    }

    public String getFormat() {
        return format;
    }
}