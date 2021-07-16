package by.training.task06multithreading.service.parser;

import by.training.task06multithreading.entity.Matrix;
import by.training.task06multithreading.service.parser.exception.MatrixParserException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class MatrixParser {

    private static final Logger logger = LogManager.getLogger(MatrixParser.class);
    private static final String PARAMS_DELIMITER = "\\s";
    private static MatrixParser instance = new MatrixParser();

    private MatrixParser(){}

    public static MatrixParser getInstance() {
        return instance;
    }

    public Matrix parseMatrixFromFile(String fileName) throws MatrixParserException {
        if (fileName == null || fileName.isEmpty()){
            throw new MatrixParserException("empty/null filename");
        }

        File file = getFileFromResource(fileName);

        List<String> stringList;
        try(Stream<String> stream = Files.lines(file.toPath(), StandardCharsets.UTF_8)) {
            stringList = stream.collect(Collectors.toList());
        } catch (IOException e) {
            throw new MatrixParserException("Error file reading", e);
        }

        return parseMatrixFromStringArray(stringList);
    }

    private Matrix parseMatrixFromStringArray(List<String> stringList) {
        int size = stringList.size();
        int[] tmpInts;
        int[][] arr = new int[size][size];
        for (int i = 0; i < size; i++) {
            tmpInts = Arrays.stream(stringList.get(i).split(PARAMS_DELIMITER))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            for (int j = 0; j < size; j++) {
                arr[i][j] = tmpInts[i];
            }

        }
        return new Matrix(arr);
    }


    private File getFileFromResource(String fileName) throws MatrixParserException {

        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource(fileName);
        if (resource == null) {
            throw new MatrixParserException("file not found! " + fileName);
        } else {
            try {
                return new File(resource.toURI());
            } catch (URISyntaxException e) {
                throw new MatrixParserException("error reading file", e);
            }
        }

    }





}