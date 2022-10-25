package services;

import entities.User;


public interface UserService {

    User createUser (User user);

    User getUserById(int id);

    User updateUser(User user);

    boolean deleteUserById(int id);

}
