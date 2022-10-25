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

        /*if(ticket.getTicketById() == 0){
            throw new InvalidTicketException("Ticket ID cannot be zero (0)");}
        return this.ticketDAO.createNewTicket(ticket);*/
        return this.ticketDAO.createNewTicket(ticket);

    }

    @Override
    public Ticket getTicketById(int id) {
        return this.ticketDAO.getTicketById(id);
    }
}
