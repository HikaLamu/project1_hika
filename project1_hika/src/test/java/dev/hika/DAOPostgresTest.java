package dev.hika;

import entities.Ticket;
import entities.User;
import org.junit.jupiter.api.*;
import repositories.TicketDAO;
import repositories.TicketDAOPostgres;
import repositories.UserDAO;
import repositories.UserDAOPostgres;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DAOPostgresTest {
    static UserDAO userDAO = new UserDAOPostgres();
    static TicketDAO ticketDAO = new TicketDAOPostgres();

    @Test
    @Order(1)
    void create_user_test(){
        User newUser = new User(1,"Hika Lamu","abcdef",false);
        User registeredUser = userDAO.createUser(newUser);
        Assertions.assertNotEquals(0,registeredUser.getId());
    }
    @Test
    @Order(2)
    void create_ticket_test(){

        Ticket newTicket = new Ticket(1, 700, "Office Supply", "PENDING", 2);
        Ticket createdTicket = ticketDAO.createNewTicket(newTicket);
        Assertions.assertNotEquals(0,createdTicket.getId());
    }
    @Test
    @Order(3)
    void get_ticket_by_id_test(){
        Ticket gottenTicket = ticketDAO.getTicketById(1);
        Assertions.assertEquals(2500,gottenTicket.getAmount());
    }

    @Test
    @Order(4)
    void update_ticket_status_test(){
        Ticket ticket = ticketDAO.getTicketById(1);
        Ticket ticket1=new Ticket(ticket.getId(), ticket.getAmount(), ticket.getDescription(), "APPROVED", ticket.getUserId() );
        ticketDAO.updateTicket(ticket1);
        Ticket updatedTicket = ticketDAO.getTicketById(ticket1.getId());
        Assertions.assertEquals("APPROVED",updatedTicket.getStatus());



    }

}
