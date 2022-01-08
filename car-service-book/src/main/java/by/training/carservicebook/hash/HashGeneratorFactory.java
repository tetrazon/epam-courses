package by.training.carservicebook.hash;

import by.training.carservicebook.service.*;
import lombok.extern.log4j.Log4j2;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Log4j2
public class HashGeneratorFactory {

    private static final Map<String, Class<? extends HashGenerator>> HASH_GENERATORS = new ConcurrentHashMap<>();

    static {
        HASH_GENERATORS.put("Bcrypt", BcryptHashGenerator.class);
    }

    @SuppressWarnings("unchecked")
    public static <T extends HashGenerator> T getHashGenerator(String key) {
        try {
            return (T) HASH_GENERATORS.get(key).getDeclaredConstructor().newInstance();
        } catch (InstantiationException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            log.error("create hash generator error", e);
            return null;
        }
    }
}
