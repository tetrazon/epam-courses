package by.training.task02branchingstatements.secret.service;

import by.training.task02branchingstatements.secret.model.AccessLevel;
import by.training.task02branchingstatements.secret.validator.PasswordValidator;

import java.util.List;
import java.util.Optional;

/**
 * class for access level service
 */
public class AccessLevelService {

    /**
     * returns access level name for particular password if exists
     * @param accessLevelList list of available access levels
     * @param password password to check the access level
     * @return Optional<String> of access level for particular password
     */
    public Optional<String> getAccessLevelByPassword(List<AccessLevel> accessLevelList, String password){
            return PasswordValidator.isCorrectPassword(password) ? accessLevelList
                    .stream()
                    .filter(accessLevel -> accessLevel.getPasswords().contains(password))
                    .map(AccessLevel::getAccessLevelName)
                    .findFirst()
                    : Optional.empty();
    }



}
