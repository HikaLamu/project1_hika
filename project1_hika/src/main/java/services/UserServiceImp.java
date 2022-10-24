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
        return this.userDAO.createUser(user);
    }

    @Override
    public User getUserById(int id) {
        return this.userDAO.getUserById(id);
    }

    @Override
    public User updateUser(User user) {

        if(user.getUserName().length() == 0){
            throw new InvalidUserNameException("Username cannot be empty");
        }
        return this.userDAO.updateUser(user);
    }

    @Override
    public boolean deleteUserById(int id) {
        return this.userDAO.deleteUserById(id);
    }
}
