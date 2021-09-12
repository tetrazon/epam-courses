package by.training.task08.controller.impl;

import by.training.task08.controller.FlowerController;
import by.training.task08.controller.exception.FlowerControllerException;
import by.training.task08.entity.Flower;
import by.training.task08.service.FlowerService;
import by.training.task08.service.exception.ParseServiceException;
import by.training.task08.service.impl.FlowerServiceImpl;

import java.util.Set;

public class FlowerControllerImpl implements FlowerController {
    private FlowerService flowerService;

    public FlowerControllerImpl(){
        flowerService = new FlowerServiceImpl();
    }

    public FlowerControllerImpl(FlowerService flowerService) {
        this.flowerService = flowerService;
    }

    @Override
    public Set<Flower> parseFlowers(String parseType, String fileName) throws FlowerControllerException {
        try{
            return flowerService.parseFlowers(parseType,fileName);
        } catch (ParseServiceException e) {
            throw new FlowerControllerException("error parsing flowers", e);
        }
    }
}
