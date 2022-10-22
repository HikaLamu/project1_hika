package services;


import exceptions.InvalidPasswordException;
import exceptions.InvalidUserNameException;
import entities.User;
import repositories.UserDAO;

public class UserServiceImp implements UserService{

    private UserDAO userDAO;

    public UserServiceImp(UserDAO userDAO){
        this.userDAO = userDAO;
    }

    @Override
    public User createUser(User user) {
        if(user.getUserName().length() == 0){
            throw new InvalidUserNameException("User name cannot be empty");
        }
        if(user.getPassword().length() == 0){
            throw new InvalidPasswordException("Password cannot be empty");
        }
        User savedUser = this.userDAO.createUser(user);
        return savedUser;
    }

    @Override
    public User createUserName(User userName) {
        return null;
    }

    @Override
    public User createPassword(User password) {
        return null;
    }


    @Override
    public User getUserById(int id) {
        return null;
    }

    @Override
    public User updateUserName(User user) {
        return null;
    }

    @Override
    public boolean deleteUserById(int id) {
        return false;
    }
}
