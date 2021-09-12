package by.training.task08.dao.parser;

import by.training.task08.entity.Flower;

import java.util.HashSet;
import java.util.Set;

public abstract class AbstractFlowersBuilder {
    // protected - it is often accessed from a subclass
    protected Set<Flower> flowers;
    public AbstractFlowersBuilder() {
        flowers = new HashSet<>();
    }

    public AbstractFlowersBuilder(Set<Flower> flowers) {
        this.flowers = flowers;
    }

    public Set<Flower> getFlowers() {
        return flowers;
    }

    public abstract void buildSetFlowers(String filename);
}
