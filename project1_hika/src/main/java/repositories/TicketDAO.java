package repositories;

import entities.Status;
import entities.Ticket;
import entities.User;

import java.util.List;

public interface TicketDAO {

    Ticket createNewTicket(Ticket ticket);

    Ticket getTicketById (int id);

    Ticket updateTicket(Ticket ticket);

    List<Ticket> getAllPendingTickets();


}
