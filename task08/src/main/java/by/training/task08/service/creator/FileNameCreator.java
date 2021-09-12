package by.training.task08.service.creator;

import by.training.task08.service.creator.exception.FileNameCreatorException;

import java.io.File;

public class FileNameCreator {
    public String createFileName(String fileName){
        if (fileName == null || fileName.isEmpty()){
            throw new FileNameCreatorException("null/empty name");
        }
        ClassLoader classLoader = getClass().getClassLoader();
        File tmpFileName;
        try {
            tmpFileName = new File((classLoader.getResource( fileName)).getFile());
            return tmpFileName.getAbsolutePath();
        } catch (NullPointerException e){
            throw new FileNameCreatorException("error creating file", e);
        }
    }
}
