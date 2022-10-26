package dev.hika;

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
        User newUser = new User(0,"John Doe","abcde",false);
        User registeredUser = userDAO.createUser(newUser);
        Assertions.assertNotEquals(0,registeredUser.getId());
    }
    @Test
    @Order(2)
    void get_user_by_id_test(){
        User returnedUser = userDAO.getUserById(1);
        Assertions.assertEquals("John Doe",returnedUser.getUserName());
    }
    @Test
    @Order(3)
    void update_book_test(){
        //When testing update, you should either get the book and use its values or create a completely new book and use those values
        User user = userDAO.getUserById(1);
        //You can think of update more of a full replacement/swap and less of specific values being update
        User userNew = new User(user.getId(),user.getUserName(),"James Matthew Barrie",user.isAdmin());
        userDAO.updateUser(userNew);
        User updatedUser = userDAO.getUserById(userNew.getId());
        Assertions.assertEquals("James Matthew Barrie",updatedUser.getUserName());
    }
    @Test
    @Order(4)
    void delete_book_by_id_test(){
        boolean result = userDAO.deleteUserById(1);
        Assertions.assertTrue(result);
    }
}
