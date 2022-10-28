package repositories;

import entities.User;

import java.util.List;

public interface UserDAO {
    User createUser (User user);

    User getUserById(int id);

    List<User> getAllUsers();

    List<User> getUserByCreds(String userName,String password);

    User getUserByUserName(String userName);

  //  User getUserByName(String username);

}
