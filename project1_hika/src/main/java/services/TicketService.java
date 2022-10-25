package services;

import entities.Ticket;
import entities.User;

public interface TicketService {

    Ticket createNewTicket(Ticket ticket);

    Ticket getTicketById (int id);

    Ticket updateTicket (Ticket ticket);


}
