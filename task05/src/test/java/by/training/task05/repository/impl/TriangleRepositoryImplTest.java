package by.training.task05.repository.impl;

import by.training.task05.entity.Triangle;
import by.training.task05.repository.TriangleRepository;
import by.training.task05.repository.exception.TriangleRepositoryException;
import by.training.task05.repository.factory.RepositoryFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.w3c.dom.Text;

import java.util.List;

import static org.testng.Assert.*;

public class TriangleRepositoryImplTest {
    private TriangleRepository<Triangle> repository = RepositoryFactory.getInstance().getTriangleRepository();

    @DataProvider(name = "dataForTestCreate")
    public Object[][] createDataForTestCreate() {
        return new Object[][]{
                {getTriangle()}
        };
    }

    private Triangle getTriangle() {
        Triangle.Point a = new Triangle.Point(0., 0., 0.);
        Triangle.Point b = new Triangle.Point(0., 4., 0.);
        Triangle.Point c = new Triangle.Point(3., 0., 0.);

        return Triangle.builder().a(a).b(b).c(c).build();
    }

    @DataProvider(name = "dataForTestUpdate")
    public Object[][] createDataForTestUpdate() {
        Triangle.Point a = new Triangle.Point(0., 0., 0.);
        Triangle.Point b = new Triangle.Point(0., 4., 0.);
        Triangle.Point c = new Triangle.Point(3., 0., 0.);
        Triangle.Point c1 = new Triangle.Point(4., 0., 0.);

        return new Object[][]{
                {
                        Triangle.builder()
                                .a(a)
                                .b(b)
                                .c(c)
                                .build(),

                        Triangle.builder()
                                .a(a)
                                .b(b)
                                .c(c1)
                                .build()}
        };
    }

    @DataProvider(name = "dataForTestDelete")
    public Object[][] createDataForTestDelete() {
        return new Object[][]{
                {getTriangle()}
        };
    }

    @Test
    public void testRead() {
    }

    @Test(description = "positive scenario of create() method",
            dataProvider = "dataForTestCreate")
    public void testCreate(Triangle triangle) throws TriangleRepositoryException {
        int id = repository.create(triangle);
        assertTrue(repository.read(id).isPresent());
    }
    @Test(description = "negative scenario of create() method",
    expectedExceptions = TriangleRepositoryException.class)
    public void wrongTestCreate() throws TriangleRepositoryException {
        int id = repository.create(null);
    }

    @Test(description = "positive scenario of update() method",
            dataProvider = "dataForTestUpdate")
    public void testUpdate(Triangle initialTriangle, Triangle resultTriangle) throws TriangleRepositoryException {
        int id = repository.create(initialTriangle);
        repository.update(id, resultTriangle);
        assertEquals(repository.read(id).get(), resultTriangle);
    }


    @Test(description = "positive scenario of delete() method",
            dataProvider = "dataForTestDelete")
    public void testDelete(Triangle triangle) throws TriangleRepositoryException {
        int id = repository.create(triangle);
        repository.delete(id);
        assertFalse(repository.read(id).isPresent());
    }

    @Test
    public void testGetBySpec() {
    }
}