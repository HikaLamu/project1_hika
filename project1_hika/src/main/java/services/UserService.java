package services;

import entities.User;

import java.util.List;


public interface UserService {

    User createUser (User user);

    User getUserById(int id);


    List<User> getAllUsers();
   List<User> loginUser(String userName,String password);


}
