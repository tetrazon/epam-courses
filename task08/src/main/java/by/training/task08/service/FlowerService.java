package by.training.task08.service;

import by.training.task08.entity.Flower;
import by.training.task08.service.exception.ParseServiceException;

import java.util.Set;

public interface FlowerService {
    Set<Flower> parseFlowers(String parseType, String fileName) throws ParseServiceException;
}
