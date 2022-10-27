package repositories;

import dto.StatusUpdate;
import entities.Status;
import entities.Ticket;
import entities.User;

import java.util.List;

public interface TicketDAO {

    Ticket createNewTicket(Ticket ticket);

    Ticket getTicketById (int id);

    Ticket updateTicket(Ticket ticket);

    List<Ticket> getAllPendingTickets();
    Ticket getTicketStatusupdatedByAdmin(StatusUpdate stsup);

    List<Ticket> getAllTicketsBesedonRole(String userName,String password);

    List<Ticket> getAllPendingTicketsByRole(String userName,String Password);


}
