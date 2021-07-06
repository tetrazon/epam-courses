package by.training.task05.repository.factory;

import by.training.task05.entity.Triangle;
import by.training.task05.entity.TriangleProperty;
import by.training.task05.repository.Repository;
import by.training.task05.repository.TriangleRepository;
import by.training.task05.repository.impl.TrianglePropertyRepositoryImpl;
import by.training.task05.repository.impl.TriangleRepositoryImpl;
import lombok.Getter;

@Getter
public final class RepositoryFactory {

    private static RepositoryFactory instance = new RepositoryFactory();
    private TriangleRepository<Triangle> triangleRepository = new TriangleRepositoryImpl();
    private Repository<TriangleProperty> trianglePropertyRepository = new TrianglePropertyRepositoryImpl();

    private RepositoryFactory(){}

    public static RepositoryFactory getInstance(){
        return instance;
    }

    public void resetTriangleRepository(){
        triangleRepository = new TriangleRepositoryImpl();
    }

    public void resetTrianglePropertyRepository(){
        trianglePropertyRepository = new TrianglePropertyRepositoryImpl();
    }



}
