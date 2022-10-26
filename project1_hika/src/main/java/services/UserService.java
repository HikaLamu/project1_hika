package services;

import entities.User;

import java.util.List;


public interface UserService {

    User createUser (User user);

    User getUserById(int id);

    User updateUser(User user);

    boolean deleteUserById(int id);

    List<User> getAllUsers();
    User loginUser(String userName,String password);


}
