package services;

import dto.StatusUpdate;
import entities.Ticket;
import entities.User;

import java.util.List;

public interface TicketService {

    Ticket createNewTicket(Ticket ticket);

    Ticket getTicketById (int id);

    Ticket updateTicket (Ticket ticket);

    Ticket getStatusUpdateByAdmin(StatusUpdate stsup);

    List<Ticket> getAllTicketsBesedOnRole(String username,String password);

    List<Ticket> getAllPendingTicketsBesedOnRole(String username,String password);



}
