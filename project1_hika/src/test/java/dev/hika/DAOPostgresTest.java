package dev.hika;

import entities.Ticket;
import entities.User;
import org.junit.jupiter.api.*;
import repositories.TicketDAO;
import repositories.TicketDAOPostgres;
import repositories.UserDAO;
import repositories.UserDAOPostgres;

import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DAOPostgresTest {
    static UserDAO userDAO = new UserDAOPostgres();
    static TicketDAO ticketDAO = new TicketDAOPostgres();

    @Test
    @Order(1)
    void create_user_test(){
        User newUser = new User(15,"Thomaas Partey","Arsenal",false);
        User registeredUser = userDAO.createUser(newUser);
        Assertions.assertNotEquals(0,registeredUser.getId());
    }
    @Test
    @Order(2)
    void create_ticket_test(){

        Ticket newTicket = new Ticket();
        newTicket.setAmount(700);
        newTicket.setUserId(4);
        newTicket.setDescription("Office Supply");
        Ticket createdTicket = ticketDAO.createNewTicket(newTicket);
        Assertions.assertNotEquals(0,createdTicket.getId());
    }


    @Test
    @Order(3)
    void update_ticket_status_test(){
        //When testing update, you should either get the book and use its values or create a completely new book and use those values
        Ticket ticket = ticketDAO.getTicketById(1);
        Assertions.assertNotNull(ticket);
        List<User> user = userDAO.getUserByCreds("Adam Smith", "abcdef");
        Assertions.assertNotNull(user);
        Ticket ticket1=new Ticket(1,20000,"office use","PENDING",4);

        Ticket ticket2=ticketDAO.updateTicket(ticket1);
        Assertions.assertEquals(ticket2,ticket1);




    }

}
