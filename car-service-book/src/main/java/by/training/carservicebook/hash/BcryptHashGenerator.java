package by.training.carservicebook.hash;

import at.favre.lib.crypto.bcrypt.BCrypt;

public class BcryptHashGenerator implements HashGenerator {
    @Override
    public String hash(String password) {
        return BCrypt.withDefaults().hashToString(12, password.toCharArray());
    }

    @Override
    public boolean check(String originalPassword, String hashedPassword) {
        BCrypt.Result result = BCrypt.verifyer().verify(originalPassword.toCharArray(), hashedPassword);
        return result.verified;
    }
}
