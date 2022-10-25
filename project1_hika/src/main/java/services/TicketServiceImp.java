package services;

import entities.Ticket;
import repositories.TicketDAO;


public class TicketServiceImp implements TicketService{

    private TicketDAO ticketDAO;

    public TicketServiceImp(TicketDAO ticketDAO){
        this.ticketDAO = ticketDAO;
    }

    @Override
    public Ticket createNewTicket(Ticket ticket) {

        return this.ticketDAO.createNewTicket(ticket);

    }

    @Override
    public Ticket getTicketById(int id) {
        return this.ticketDAO.getTicketById(id);
    }

    @Override
    public Ticket updateTicket(Ticket ticket){
        return this.ticketDAO.updateTicket(ticket);
    }
}
