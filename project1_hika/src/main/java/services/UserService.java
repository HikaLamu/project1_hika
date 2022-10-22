package services;

import entities.User;

import java.util.List;

public interface UserService {

    User createUser (User user);

    User createUserName(User userName);

    User createPassword (User password);

    User getUserById(int id);

    User updateUserName(User user);

    boolean deleteUserById(int id);

}
