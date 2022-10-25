package repositories;

import entities.Status;
import entities.Ticket;

public interface TicketDAO {

    Ticket createNewTicket(Ticket ticket);

    Ticket getTicketById (int id);

    Ticket updateTicket(Ticket ticket);
}
