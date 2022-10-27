package services;

import dto.StatusUpdate;
import entities.Ticket;
import repositories.TicketDAO;

import java.util.List;


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


    @Override
    public Ticket getStatusUpdateByAdmin(StatusUpdate stsup) {
        return this.ticketDAO.getTicketStatusupdatedByAdmin(stsup);
    }

    @Override
    public List<Ticket> getAllTicketsBesedOnRole(String username, String password) {
        return this.ticketDAO.getAllTicketsBesedonRole(username,password);
    }

    @Override
    public List<Ticket> getAllPendingTicketsBesedOnRole(String username, String password) {
        return this.ticketDAO.getAllPendingTicketsByRole(username, password);
    }

}
