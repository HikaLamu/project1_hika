package repositories;

import entities.User;
import util.ConnectionFactory;

import java.sql.*;

public class UserDAOPostgres implements UserDAO {
    @Override
    public User createUser(User user) {
        try(Connection connection = ConnectionFactory.getConnection()){
            String sql = "insert into employee values(default, ?, ? , ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, user.getUserName());
            preparedStatement.setString(2,user.getPassword());
            preparedStatement.setBoolean(3,user.isAdmin());

            preparedStatement.execute();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();
            int generatedKey = resultSet.getInt("id");
            user.setId(generatedKey);
            return user;
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public User getUserById(int id) {

        try(Connection connection = ConnectionFactory.getConnection()){
            String sql = "select * from employee where id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1,id);

            ResultSet rs = ps.executeQuery();
            rs.next();

            User user = new User();
            user.setId(rs.getInt("id"));
            user.setUserName(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setIsAdmin(rs.getBoolean("isadmin"));

            return user;
        }
        catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public User updateUser(User user) {
        try(Connection connection = ConnectionFactory.getConnection()){
            String sql = "update employee set username = ?, password = ?, isadmin = ? where id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, user.getUserName());
            preparedStatement.setString(2,user.getPassword());
            preparedStatement.setBoolean(3,user.isAdmin());
            preparedStatement.setInt(4,user.getId());

            preparedStatement.executeUpdate();
            return user;
        }
        catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean deleteUserById(int id) {
        try(Connection connection = ConnectionFactory.getConnection()){
            String sql = "delete from employee where id =?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1,id);

            preparedStatement.execute();
            return true;
        }
        catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

}
