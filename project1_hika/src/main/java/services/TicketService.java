package services;

import entities.Ticket;

public interface TicketService {

    Ticket createNewTicket(Ticket ticket);

    Ticket getTicketDescription (String description);

    Ticket getTicketAmount (long amount);

    Ticket getTicketById (int id);


}
