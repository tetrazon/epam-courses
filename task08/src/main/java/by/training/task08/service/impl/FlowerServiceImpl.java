package by.training.task08.service.impl;

import by.training.task08.dao.parser.AbstractFlowersBuilder;
import by.training.task08.dao.parser.FlowerBuilderFactory;
import by.training.task08.entity.Flower;
import by.training.task08.service.FlowerService;
import by.training.task08.service.creator.FileNameCreator;
import by.training.task08.service.creator.exception.FileNameCreatorException;
import by.training.task08.service.exception.ParseServiceException;
import lombok.extern.log4j.Log4j2;

import java.util.Set;

@Log4j2
public class FlowerServiceImpl implements FlowerService {
    private FileNameCreator fileNameCreator;
    private AbstractFlowersBuilder flowersBuilder;

    public FlowerServiceImpl() {
        fileNameCreator = new FileNameCreator();
    }

    public FlowerServiceImpl(FileNameCreator fileNameCreator) {
        this.fileNameCreator = fileNameCreator;
    }

    @Override
    public Set<Flower> parseFlowers(String parseType, String fileName) throws ParseServiceException {
        try {
            flowersBuilder = FlowerBuilderFactory.createFlowerBuilder(parseType);
            flowersBuilder.buildSetFlowers(new FileNameCreator().createFileName(fileName));
            log.info("service layer: flowers \n" + flowersBuilder.getFlowers());
            return flowersBuilder.getFlowers();
        } catch (EnumConstantNotPresentException e) {
            throw new ParseServiceException("parse error, wrong parse type", e);
        } catch (FileNameCreatorException e) {
            throw new ParseServiceException("parse error, wrong file name", e);
        }
    }
}
