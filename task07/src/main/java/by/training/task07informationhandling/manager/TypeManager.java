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

    public static void main(String[] args) {
        System.out.println(String.format(WORD.getFormat(), "ololo"));
        System.out.println(String.format(LEXEME.getFormat(), "ololo"));
        System.out.println(String.format(SENTENCE.getFormat(), "ololo"));
        System.out.println(String.format(PARAGRAPH.getFormat(), "ololo"));
    }
}