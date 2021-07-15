package by.training.task05.service.impl;

import by.training.task05.entity.Triangle;
import by.training.task05.entity.TriangleProperty;
import by.training.task05.repository.exception.TriangleRepositoryException;
import by.training.task05.service.exception.TrianglePropertyServiceException;
import by.training.task05.service.parser.TriangleParser;
import by.training.task05.repository.Repository;
import by.training.task05.repository.TriangleRepository;
import by.training.task05.repository.factory.RepositoryFactory;
import by.training.task05.service.TrianglePropertyRegistrar;
import by.training.task05.service.TrianglePropertyService;
import by.training.task05.service.TriangleService;
import by.training.task05.service.exception.TriangleServiceException;
import by.training.task05.specification.impl.AreaFindSpecification;
import by.training.task05.specification.impl.FirstOctantFindSpecification;
import by.training.task05.specification.impl.SortByXSpecification;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.*;

public class TriangleServiceImplTest {

    private final RepositoryFactory repositoryFactory = RepositoryFactory.getInstance();
    private TriangleRepository<Triangle> triangleRepository = repositoryFactory.getTriangleRepository();
    private Repository<TriangleProperty> trianglePropertyRepository = repositoryFactory.getTrianglePropertyRepository();
    private TrianglePropertyService trianglePropertyService = new TrianglePropertyServiceImpl(trianglePropertyRepository);
    private TriangleService service = new TriangleServiceImpl(triangleRepository,
            new TrianglePropertyRegistrar(trianglePropertyRepository),
            trianglePropertyService);

    @DataProvider(name = "dataForSortByXYZSpecification")
    public static Object[][] createDataForSortByXYZSpecification() {
        return new Object[][]{
                {"textData/triangles-spec.txt", "textData/triangles-sortByXYZ.txt"}
        };
    }


    @DataProvider(name = "dataForTestRead")
    public Object[][] createDataForTestRead() {

        return new Object[][]{
                {"textData/triangles.txt"}
        };
    }

    @DataProvider(name = "dataForTestGetByAreaFindSpecification")
    public Object[][] createDataForGetByAreaFindSpecification() {

        return new Object[][]{
                {"textData/triangles-spec.txt", 3, 1, 10}
        };
    }

    @DataProvider(name = "dataForTestGetByFirstOctantSpecification")
    public Object[][] createDataForGetByFirstOctantSpecification() {

        return new Object[][]{
                {"textData/triangles-spec.txt", 7}
        };
    }

    @DataProvider(name = "dataForTestSortByFirstPointXCoordinate")
    public static Object[][] createDataForTestSortByFirstPointXCoordinate() {
        return new Object[][]{
                {"textData/triangles-spec.txt", 7}
        };
    }

    @DataProvider(name = "wrongDataForTestReadWrong")
    public static Object[][] createDataForTestReadWrong() {
        return new Object[][]{
                {-5},
                {-77},
                {0},
                {-1}
        };
    }

    private Triangle getTriangle() {
        Triangle.Point a = new Triangle.Point(0., 0., 0.);
        Triangle.Point b = new Triangle.Point(0., 4., 0.);
        Triangle.Point c = new Triangle.Point(3., 0., 0.);

        return Triangle.builder().a(a).b(b).c(c).build();
    }

    /**
     * reset repos before starting each method
     */
    @BeforeMethod
    public void preset(){
        RepositoryFactory.getInstance().resetTrianglePropertyRepository();
        RepositoryFactory.getInstance().resetTriangleRepository();
        triangleRepository = repositoryFactory.getTriangleRepository();
        trianglePropertyRepository = repositoryFactory.getTrianglePropertyRepository();
        trianglePropertyService = new TrianglePropertyServiceImpl(trianglePropertyRepository);
        service = new TriangleServiceImpl(triangleRepository,
                new TrianglePropertyRegistrar(trianglePropertyRepository),
                trianglePropertyService);

    }

    @Test(description = "positive scenario of read() method")
    public void testRead() throws TriangleRepositoryException, TriangleServiceException {
        Triangle initTriangle = getTriangle();
        final int id = triangleRepository.create(initTriangle);
        assertTrue(service.read(id).isPresent());
    }

    @Test(description = "negative scenario of read() method",
    dataProvider = "wrongDataForTestReadWrong",
    expectedExceptions = TriangleServiceException.class)
    public void testReadWrong(int id) throws TriangleServiceException {
        service.read(id);
    }

    @Test(description = "positive scenario of create() method")
    public void testCreate() throws TriangleServiceException {
        Triangle triangle = getTriangle();
        final int id = service.create(triangle.getA(), triangle.getB(), triangle.getC());
        assertTrue(triangleRepository.read(id).isPresent());
    }

    @Test(description = "positive scenario of createFromFile() method",
    dataProvider = "dataForTestRead")
    public void testCreateFromFile(String filename) throws TriangleServiceException {
        service.createFromFile(filename);
        assertFalse(service.readAll().isEmpty());
    }

    @Test(description = "positive scenario of update() method")
    public void testUpdate() throws TriangleRepositoryException,
            TriangleServiceException, TrianglePropertyServiceException {
        Triangle initTriangle = getTriangle();
        final int id = triangleRepository.create(initTriangle);
        Triangle.Point newPointC = new Triangle.Point(10., 10., 10.);
        service.update(id, initTriangle.getA(), initTriangle.getB(), newPointC);
        final Triangle updatedTriangle = triangleRepository.read(id).get();
        assertEquals(updatedTriangle.getC(), newPointC);
    }

    @Test(description = "positive scenario of observer work")
    public void testObserver() throws TriangleRepositoryException,
            TriangleServiceException, TrianglePropertyServiceException {
        Triangle initTriangle = getTriangle();
        final int id = service.create(initTriangle.getA(), initTriangle.getB(), initTriangle.getC());
        TriangleProperty trianglePropertyOld = trianglePropertyService.readPropertiesById(id);
        Triangle.Point newPointC = new Triangle.Point(10., 10., 10.);
        service.update(id, initTriangle.getA(), initTriangle.getB(), newPointC);
        TriangleProperty trianglePropertyNew = trianglePropertyService.readPropertiesById(id);
        assertFalse(trianglePropertyNew.equals(trianglePropertyOld));
    }

    @Test(description = "positive scenario of update() method")
    public void testDelete() throws TriangleRepositoryException, TriangleServiceException {
        Triangle initTriangle = getTriangle();
        int id = triangleRepository.create(initTriangle);
        service.delete(id);
        assertTrue(triangleRepository.read(id).isEmpty());
    }

    @Test(description = "positive scenario of getProperties() method")
    public void testGetProperties() throws TriangleServiceException {
        Triangle triangle = getTriangle();
        final int id = service.create(triangle.getA(), triangle.getB(), triangle.getC());
        TriangleProperty triangleProperty = trianglePropertyRepository.read(id).get();
        assertEquals(service.getProperties(id), triangleProperty.toString());
    }

    @Test(description = "positive scenario of getBySpec() method, areaFindSpecification as  a param",
            dataProvider = "dataForTestGetByAreaFindSpecification")
    public void testGetByAreaFindSpecification(String filename, int expectedQuantity, int minArea, int maxArea) throws TriangleServiceException {
        service.createFromFile(filename);
        AreaFindSpecification specification = new AreaFindSpecification(minArea, maxArea, trianglePropertyRepository);
        final List<Triangle> triangleList = service.getBySpec(specification);
        assertEquals(triangleList.size(), expectedQuantity);
    }

    @Test(description = "positive scenario of getBySpec() method, FirstOctantFindSpecification as a param",
            dataProvider = "dataForTestGetByFirstOctantSpecification")
    public void testGetByFirstOctantSpecification(String filename, int expectedQuantity) throws TriangleServiceException {
        service.createFromFile(filename);
        FirstOctantFindSpecification specification = new FirstOctantFindSpecification();
        final List<Triangle> triangleList = service.getBySpec(specification);
        assertEquals(triangleList.size(), expectedQuantity);
    }

    @Test(description = "positive scenario of getBySpec() method, SortByXSpecification as a param",
            dataProvider = "dataForSortByXYZSpecification")
    public void testSortByXYZSpecification(String inputFilename, String expectedFilename) throws TriangleServiceException {
        service.createFromFile(inputFilename);
        SortByXSpecification sort = new SortByXSpecification();
        List<Triangle> triangleList = service.getBySpec(sort);
        List<Triangle> expected = TriangleParser.getInstance().parseTrianglesFromFile(expectedFilename);
        for (int i = 0; i < expected.size(); i++) {
            expected.get(i).setId(0);
            triangleList.get(i).setId(0);
        }
        assertEquals(triangleList, expected);
    }



}