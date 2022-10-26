package repositories;

import entities.User;

import java.util.List;

public interface UserDAO {
    User createUser (User user);

    User getUserById(int id);

    User updateUser(User user);

    boolean deleteUserById(int id);

    List<User> getAllUsers();

    User getUserByCreds(String userName,String password);


    User getUserByUserName(String userName);

}
