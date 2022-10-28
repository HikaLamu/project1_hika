package repositories;

import dto.StatusUpdate;
import entities.Ticket;
import entities.User;
import exceptions.CredentialsDoesNotMacthwithAdmin;
import exceptions.InvalidUserIdException;
import util.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TicketDAOPostgres implements TicketDAO {
    private int userId;

    @Override
    public Ticket createNewTicket(Ticket ticket) {
        System.out.println(ticket);
        System.out.println(ticket.getStatus());
        ticket.setStatus("PENDING");
        try (Connection connection = ConnectionFactory.getConnection()) {
            String sql = "insert into ticket values(default, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, ticket.getAmount());
            preparedStatement.setString(2, ticket.getDescription());
            preparedStatement.setString(3, ticket.getStatus());
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
            System.out.println("update value"+id);
            ResultSet rs = ps.executeQuery();
            rs.next();

            Ticket ticket = new Ticket();
            ticket.setId(rs.getInt("id"));
            ticket.setAmount(rs.getLong("amount"));
            ticket.setDescription(rs.getString("description"));
            ticket.setStatus(rs.getString("status"));
            ticket.setUserId(rs.getInt("userid"));

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
            preparedStatement.setString(5, ticket.getStatus());
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
            String sql = "select * from ticket where status = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,"PENDING");

            ResultSet rs = preparedStatement.executeQuery();

            List<Ticket> pendingTicketList = new ArrayList();

            while (rs.next()) {
                Ticket ticket = new Ticket();
                ticket.setId(rs.getInt("id"));
                ticket.setAmount(rs.getLong("amount"));
                ticket.setDescription(rs.getString("description"));
                ticket.setStatus(rs.getString("status"));
                pendingTicketList.add(ticket);
            }
            return pendingTicketList;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }
//checking for manager can access pending requests
    @Override

public List<Ticket> getAllPendingTicketsByRole(String UserName,String password) {
    try (Connection connection = ConnectionFactory.getConnection()) {
        String sql = "select * from employee where username=? and userpassword=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, UserName);
        preparedStatement.setString(2, password);
        ResultSet rs = preparedStatement.executeQuery();

        rs.next();
        User user = new User();
        user.setId(rs.getInt("id"));
        user.setUserName(rs.getString("username"));
        user.setPassword(rs.getString("userpassword"));
        user.setIsAdmin(rs.getBoolean("isadmin"));

        if (user.isAdmin() == true) {
            TicketDAOPostgres ticketDAOPostgres = new TicketDAOPostgres();
            List<Ticket> tickets = ticketDAOPostgres.getAllPendingTickets();
            return tickets;
        }else{
            try {
                throw new CredentialsDoesNotMacthwithAdmin("Only admin can access all pending tickets");
            } catch (CredentialsDoesNotMacthwithAdmin e) {
                e.printStackTrace();
            }
        }
    } catch (SQLException e) {
       e.printStackTrace();
    }
    return null;
}
    @Override
    public Ticket getTicketStatusupdatedByAdmin(StatusUpdate stsup) {
        try (Connection connection = ConnectionFactory.getConnection()) {
            String sql = "select * from employee where username=? and userpassword=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, stsup.getUserName());
            preparedStatement.setString(2, stsup.getPassword());
            ResultSet rs = preparedStatement.executeQuery();

            rs.next();
            User user = new User();
            user.setId(rs.getInt("id"));
            user.setUserName(rs.getString("username"));
            user.setPassword(rs.getString("userpassword"));
            user.setIsAdmin(rs.getBoolean("isadmin"));
            if (user.isAdmin() == true) {
                System.out.println(stsup);
                TicketDAOPostgres ticketDAOPostgres1=new TicketDAOPostgres();
            Ticket ticket1 = ticketDAOPostgres1.getTicketById(stsup.getUserId());
                if (null != ticket1) {
                    TicketDAOPostgres ticketDAOPostgres = new TicketDAOPostgres();
                     ticketDAOPostgres.updateUser(ticket1.getId(),stsup.getStatus());
                    Ticket ticket = new Ticket();
                    ticket.setUserId(ticket1.getUserId());
                    ticket.setStatus(stsup.getStatus());
                    ticket.setAmount(ticket1.getAmount());
                    ticket.setId(ticket1.getId());
                    return ticket;
                } else {
                    try {
                        throw new InvalidUserIdException("userId is not present");
                    } catch (InvalidUserIdException e) {
                        e.printStackTrace();
                    }
                }

            } else {
                try {
                    throw new CredentialsDoesNotMacthwithAdmin("please provide Admin Credentials");
                } catch (CredentialsDoesNotMacthwithAdmin e) {
                    e.printStackTrace();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return null;
    }
    private void updateUser(int id,String status) {
        try (Connection connection = ConnectionFactory.getConnection()) {
            String sql = "update ticket set status = ? where id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, status);
            preparedStatement.setInt(2, id);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
           // return null;
        }
    }
    @Override
    public List<Ticket> getAllTicketsBesedonRole(String UserName,String password) {

        try (Connection connection = ConnectionFactory.getConnection()) {
            String sql = "select * from employee where username=? and userpassword=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, UserName);
            preparedStatement.setString(2, password);
            ResultSet rs = preparedStatement.executeQuery();

            rs.next();
            User user = new User();
            user.setId(rs.getInt("id"));
            user.setUserName(rs.getString("username"));
            user.setPassword(rs.getString("userpassword"));
            user.setIsAdmin(rs.getBoolean("isadmin"));

            if (user.isAdmin() == true) {
                TicketDAOPostgres ticketDAOPostgres=new TicketDAOPostgres();
         List<Ticket> tickets=ticketDAOPostgres.getAllEmployeeTickets();
            return tickets;
            } else {
                TicketDAOPostgres ticketDAOPostgres=new TicketDAOPostgres();
                List<Ticket> ticket= ticketDAOPostgres.getEmployeeTicks(user.getId());
                return ticket;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
return null;
    }
    private List<Ticket> getAllEmployeeTickets(){
        try (Connection connection = ConnectionFactory.getConnection()) {
            String sql = "select * from ticket";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);


            ResultSet rs = preparedStatement.executeQuery();

            List<Ticket> pendingTicketList = new ArrayList();

            while (rs.next()) {
                Ticket ticket = new Ticket();
                ticket.setId(rs.getInt("id"));
                ticket.setAmount(rs.getLong("amount"));
                ticket.setDescription(rs.getString("description"));
                ticket.setStatus(rs.getString("status"));
                pendingTicketList.add(ticket);
            }
            return pendingTicketList;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    private List<Ticket> getEmployeeTicks(int userId){
        try (Connection connection = ConnectionFactory.getConnection()) {
            String sql = "select * from ticket where userid=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, userId);

            ResultSet rs = preparedStatement.executeQuery();

            List<Ticket> pendingTicketList = new ArrayList();

            while (rs.next()) {
                Ticket ticket = new Ticket();
                ticket.setId(rs.getInt("id"));
                ticket.setAmount(rs.getLong("amount"));
                ticket.setDescription(rs.getString("description"));
                ticket.setStatus(rs.getString("status"));
                pendingTicketList.add(ticket);
            }
            return pendingTicketList;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}