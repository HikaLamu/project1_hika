package services;

import entities.Ticket;

public interface TicketService {

    Ticket createNewTicket(Ticket ticket);

    Ticket getTicketById (int id);


}
