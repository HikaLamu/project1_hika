package repositories;

import entities.User;

public interface UserDAO {
    User createUser (User user);

    User createUserName(User userName);

    User createPassword (User password);

    User getUserById(int id);

    User updateUserName(User user);

    boolean deleteUserById(int id);
}
