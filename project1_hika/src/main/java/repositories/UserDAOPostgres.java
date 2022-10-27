package repositories;

import entities.User;
import exceptions.UserNameIsAlreadyPresent;
import util.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAOPostgres implements UserDAO {


    @Override
    public User createUser(User user) {
        UserDAOPostgres dao=new  UserDAOPostgres();
        User userinfo=dao.getUserByUserName(user.getUserName());
        if(null==userinfo){

        try (Connection connection = ConnectionFactory.getConnection()) {

                String sql = "insert into employee values(default, ?, ? , ?)";
                PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                preparedStatement.setString(1, user.getUserName());
                preparedStatement.setString(2, user.getPassword());
                preparedStatement.setBoolean(3, user.isAdmin());

                preparedStatement.execute();
                ResultSet resultSet = preparedStatement.getGeneratedKeys();
                resultSet.next();
                int generatedKey = resultSet.getInt("id");
                user.setId(generatedKey);
                return user;

        } catch (SQLException e){
            e.printStackTrace();
        } }else {
            try {
                throw new UserNameIsAlreadyPresent("username name already Exist please try with another one");
            } catch (UserNameIsAlreadyPresent ex) {
                ex.printStackTrace();
            }
        }

        return null;
    }

    @Override
    public User getUserById(int id) {

        try (Connection connection = ConnectionFactory.getConnection()) {
            String sql = "select * from employee where id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            rs.next();

            User user = new User();
            user.setId(rs.getInt("id"));
            user.setUserName(rs.getString("username"));
            user.setPassword(rs.getString("userpassword"));
            user.setIsAdmin(rs.getBoolean("isadmin"));

            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<User> getAllUsers() {
        try (Connection connection = ConnectionFactory.getConnection()) {
            String sql = "select * from employee";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            ResultSet rs = preparedStatement.executeQuery();

            List<User> userList = new ArrayList();

            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setUserName(rs.getString("username"));
                user.setPassword(rs.getString("userpassword"));
                user.setIsAdmin(rs.getBoolean("isadmin"));
                userList.add(user);
            }
            return userList;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<User> getUserByCreds(String userName, String password) {
        System.out.println(userName + password);
        try (Connection connection = ConnectionFactory.getConnection()) {
            String sql = "select * from employee where username=? and userpassword=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, userName);
            preparedStatement.setString(2, password);
            ResultSet rs = preparedStatement.executeQuery();

            rs.next();
            User user = new User();
            user.setId(rs.getInt("id"));
            user.setUserName(rs.getString("username"));
            user.setPassword(rs.getString("userpassword"));
            user.setIsAdmin(rs.getBoolean("isadmin"));
            if(user.isAdmin()==true){
                UserDAOPostgres daoPostgres=new UserDAOPostgres();
                return daoPostgres.getAllUsers();
            }else {
                ArrayList<User> list=new ArrayList<>();
                list.add(user);
                return list;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public User getUserByUserName(String userName) {

        try (Connection connection = ConnectionFactory.getConnection()) {
            String sql = "select * from employee where username = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, userName);

            ResultSet rs = ps.executeQuery();
            rs.next();

            User user = new User();
            user.setId(rs.getInt("id"));
            user.setUserName(rs.getString("username"));
            user.setPassword(rs.getString("userpassword"));
            user.setIsAdmin(rs.getBoolean("isadmin"));

            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
