package repositories;

import entities.Ticket;
import util.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TicketDAOPostgres implements TicketDAO {
    private int userId;

    @Override
    public Ticket createNewTicket(Ticket ticket) {
        System.out.println(ticket);
        try (Connection connection = ConnectionFactory.getConnection()) {
            String sql = "insert into ticket values(default, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, ticket.getAmount());
            preparedStatement.setString(2, ticket.getDescription());
            preparedStatement.setInt(3, ticket.getStatus());
            preparedStatement.setInt(4, ticket.getUserId());

            preparedStatement.execute();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();
            int generatedKey = resultSet.getInt("id");
            ticket.setId(generatedKey);
            return ticket;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Ticket getTicketById(int id) {
        try (Connection connection = ConnectionFactory.getConnection()) {
            String sql = "select * from ticket where id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            rs.next();

            Ticket ticket = new Ticket();
            ticket.setId(rs.getInt("id"));
            ticket.setAmount(rs.getLong("amount"));
            ticket.setDescription(rs.getString("description"));
            ticket.setStatus(rs.getInt("status"));
            ticket.setUserId(rs.getInt("userid"));
            //how do you add foreign key value?

            return ticket;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Ticket updateTicket(Ticket ticket) {
        try (Connection connection = ConnectionFactory.getConnection()) {
            String sql = "update ticket set amount = ?, description = ?, status = ?, userid = ? where id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setLong(1, ticket.getAmount());
            preparedStatement.setString(2, ticket.getDescription());
            preparedStatement.setInt(5, ticket.getStatus());
            preparedStatement.setInt(3, ticket.getUserId());
            preparedStatement.setInt(4, ticket.getId());


            preparedStatement.executeUpdate();
            return ticket;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Ticket> getAllPendingTickets() {
        try (Connection connection = ConnectionFactory.getConnection()) {
            String sql = "select * from ticket where status = 1";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            ResultSet rs = preparedStatement.executeQuery();

            List<Ticket> pendingTicketList = new ArrayList();

            while (rs.next()) {
                Ticket ticket = new Ticket();
                ticket.setId(rs.getInt("id"));
                ticket.setAmount(rs.getLong("amount"));
                ticket.setDescription(rs.getString("description"));
                ticket.setStatus(rs.getInt("status"));
                pendingTicketList.add(ticket);
            }
            return pendingTicketList;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }
}