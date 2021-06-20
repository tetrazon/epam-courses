package by.training.task04.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.crypto.Data;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class TextGenerator {
    private static final Logger logger = LogManager.getLogger(TextGenerator.class);

    private final static List<String> STRING_LIST_11 = List.of("this", "the", "every");
    private final static List<String> STRING_LIST_12 = List.of("cat", "universe", "autogeneration", "ship", "bamboo", "star");
    private final static List<String> STRING_LIST_13 = List.of("is", "isn't");
    private final static List<String> STRING_LIST_14 = List.of("old", "ancient", "expensive", "cool", "boring");

    private final static List<String> STRING_LIST_21 = List.of("these", "the most of", "none of");
    private final static List<String> STRING_LIST_22 = List.of("apples", "boxes", "computers", "shoes", "miners");
    private final static List<String> STRING_LIST_23 = List.of("are", "aren't");
    private final static List<String> STRING_LIST_24 = List.of("conceptual", "meaningless", "never existed", "complex", "fine");

    private final String PATH_TO_FILE = "src/main/resources/text/";

    public String generateTextToFile(int sentencesNumber){

        int sizeOfStringList11 = STRING_LIST_11.size();
        int sizeOfStringList12 = STRING_LIST_12.size();
        int sizeOfStringList13 = STRING_LIST_13.size();
        int sizeOfStringList14 = STRING_LIST_14.size();

        int sizeOfStringList21 = STRING_LIST_21.size();
        int sizeOfStringList22 = STRING_LIST_22.size();
        int sizeOfStringList23 = STRING_LIST_23.size();
        int sizeOfStringList24 = STRING_LIST_24.size();

        StringBuilder sb = new StringBuilder();
        double rand;
        for (int i = 0; i < sentencesNumber; i++) {
            rand = Math.random();
            sb
                    .append(STRING_LIST_11.get((int)(rand * STRING_LIST_11.size())))
                    .append(" ")
                    .append(STRING_LIST_12.get((int)(rand * STRING_LIST_12.size())))
                    .append(" ")
                    .append(STRING_LIST_13.get((int)(rand * STRING_LIST_13.size())))
                    .append(" ")
                    .append(STRING_LIST_14.get((int)(rand * STRING_LIST_14.size())))
                    .append(".\n")
                    .append(STRING_LIST_21.get((int)(rand * STRING_LIST_21.size())))
                    .append(" ")
                    .append(STRING_LIST_22.get((int)(rand * STRING_LIST_22.size())))
                    .append(" ")
                    .append(STRING_LIST_23.get((int)(rand * STRING_LIST_23.size())))
                    .append(" ")
                    .append(STRING_LIST_24.get((int)(rand * STRING_LIST_24.size())))
                    .append(".\n");

        }
        File newTextFile = new File(PATH_TO_FILE + System.currentTimeMillis() + ".txt");
        try (FileWriter fw = new FileWriter(newTextFile)) {
                fw.write(sb.toString());
        } catch (IOException iox) {

        }

        return newTextFile.toString();
    }


}
