package by.training.carservicebook.hash;

public interface HashGenerator {
    String hash(String password);
    boolean check (String originalPassword, String hashedPassword);
}
