package services;

import entities.Ticket;
import exceptions.InvalidTicketException;
import exceptions.InvalidUserNameException;
import repositories.TicketDAO;
import repositories.UserDAO;

public class TicketServiceImp implements TicketService{

    private TicketDAO ticketDAO;

    public TicketServiceImp(TicketDAO ticketDAO){
        this.ticketDAO = ticketDAO;
    }

    @Override
    public Ticket createNewTicket(Ticket ticket) {

        if(ticket.getTicketById() == 0){
            throw new InvalidTicketException("Ticket ID cannot be zero (0)");}
        return this.ticketDAO.createNewTicket(ticket);

    }

    @Override
    public Ticket getTicketDescription(String description) {
        return this.ticketDAO.getTicketDescription(description);
    }

    @Override
    public Ticket getTicketAmount(long amount) {
        return this.ticketDAO.getTicketAmount(amount);
    }

    @Override
    public Ticket getTicketById(int id) {
        return this.ticketDAO.getTicketById(id);
    }
}
