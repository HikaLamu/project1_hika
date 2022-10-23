package repositories;

import entities.Status;
import entities.Ticket;

public interface TicketDAO {

    Ticket createNewTicket(Ticket ticket);

    Ticket getTicketDescription (String description);

    Ticket getTicketAmount (long amount);

    Ticket getTicketById (int id);

}
