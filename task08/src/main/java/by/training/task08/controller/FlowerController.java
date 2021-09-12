package by.training.task08.controller;

import by.training.task08.controller.exception.FlowerControllerException;
import by.training.task08.entity.Flower;

import java.util.Set;

public interface FlowerController {
    Set<Flower> parseFlowers(String parseType, String fileName) throws FlowerControllerException;
}
