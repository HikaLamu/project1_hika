package repositories;

import entities.Ticket;
import util.ConnectionFactory;

import java.sql.*;

public class TicketDAOPostgres implements TicketDAO {
    @Override
    public Ticket createNewTicket(Ticket ticket) {
        System.out.println(ticket);
        try(Connection connection = ConnectionFactory.getConnection()){
            String sql = "insert into ticket values(default, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, ticket.getAmount());
            preparedStatement.setString(2, ticket.getDescription());
            preparedStatement.setInt(3, ticket.getStatus());

            //How do you insert foreign key here?

            preparedStatement.execute();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();
            int generatedKey = resultSet.getInt("id");
            ticket.setId(generatedKey);
            return ticket;
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Ticket getTicketById(int id) {
        try(Connection connection = ConnectionFactory.getConnection()){
            String sql = "select * from ticket where id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1,id);

            ResultSet rs = ps.executeQuery();
            rs.next();

            Ticket ticket = new Ticket();
            ticket.setId(rs.getInt("id"));
            ticket.setAmount(rs.getLong("amount"));
            ticket.setDescription(rs.getString("description"));
            //how do you add foreign key value?

            return ticket;
        }
        catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }
}
