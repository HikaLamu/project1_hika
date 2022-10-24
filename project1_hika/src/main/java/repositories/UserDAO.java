package repositories;

import entities.User;

public interface UserDAO {
    User createUser (User user);

    User getUserById(int id);

    User updateUser(User user);

    boolean deleteUserById(int id);

}
