package by.training.task05.service.parser;

import by.training.task05.entity.Triangle;
import by.training.task05.service.parser.exception.TriangleParserException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class TriangleParser {

    private static final Logger logger = LogManager.getLogger(TriangleParser.class);
    private static final String DATASET_DELIMITER = "\n";
    private static final String PARAMS_DELIMITER = "\\s";
    private static TriangleParser instance = new TriangleParser();

    private TriangleParser(){}

    public static TriangleParser getInstance() {
        return instance;
    }

    public List<Triangle> parseTrianglesFromFile(String fileName){
        if (fileName == null || fileName.isEmpty()){
            logger.error("empty/null filename");
            throw new TriangleParserException("empty/null filename");
        }

        File file;
        try {
            file = getFileFromResource(fileName);
        } catch (URISyntaxException e) {
            throw new TriangleParserException("Error file name", e);
        }

        List<String> stringList;
        try(Stream<String> stream = Files.lines(file.toPath(), StandardCharsets.UTF_8)) {
            stringList = stream.collect(Collectors.toList());
        } catch (IOException e) {
            throw new TriangleParserException("Error file reading", e);
        }

        return parseTriangleFromStringArray(stringList);
    }

    private List<Triangle> parseTriangleFromStringArray(List<String> stringList) {
        List<Triangle> triangleList = new ArrayList<>();
        String[] paramsString;
        for (String stringLine : stringList) {
            paramsString = stringLine.split(PARAMS_DELIMITER);
            parseTriangleFromStringsAndAddToList(paramsString, triangleList);
        }
        return triangleList;
    }

    private void parseTriangleFromStringsAndAddToList(String[] strings, List<Triangle> triangles){
        if (strings.length != 10) {
            logger.error("incorrect number of params: " + Arrays.deepToString(strings));
            return;
        }
        double[] doubles = new double[9];
        for (int i = 1; i < strings.length; i++) {
            try {
                doubles[i -1] = Double.parseDouble(strings[i]);
            } catch (NumberFormatException e){
                logger.error("incorrect coordinate: " + strings[i]);
                return;
            }
        }

        String name = strings[0];
        Triangle.Point a = new Triangle.Point(doubles[0],doubles[1],doubles[2]);
        Triangle.Point b = new Triangle.Point(doubles[3],doubles[4],doubles[5]);
        Triangle.Point c = new Triangle.Point(doubles[6],doubles[7],doubles[8]);

        final Triangle triangle = Triangle.builder().name(name).a(a).b(b).c(c).build();
        triangles.add(triangle);

    }

    private File getFileFromResource(String fileName) throws URISyntaxException {

        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource(fileName);
        if (resource == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        } else {

            // failed if files have whitespaces or special characters
            //return new File(resource.getFile());

            return new File(resource.toURI());
        }

    }





}