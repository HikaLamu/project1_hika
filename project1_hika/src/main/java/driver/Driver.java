package driver;

import controllers.TicketController;
import controllers.UserController;
import handlers.*;
import io.javalin.Javalin;
import repositories.TicketDAOPostgres;
import repositories.UserDAOPostgres;
import services.TicketService;
import services.TicketServiceImp;
import services.UserService;
import services.UserServiceImp;

public class Driver {

    public static UserService userService = new UserServiceImp(new UserDAOPostgres());

    public static TicketService ticketService = new TicketServiceImp(new TicketDAOPostgres());

    public static void main(String[] args) {

        Javalin app = Javalin.create();

          GetUserByIdHandler getUserByIdHandler = new GetUserByIdHandler();
          CreateUserHandler createUserHandler = new CreateUserHandler();
          DeleteUserHandler deleteUserHandler = new DeleteUserHandler();
          UserController userController = new UserController();

          CreateNewTicketHandler createNewTicketHandler = new CreateNewTicketHandler();
          GetTicketByIdHandler getTicketByIdHandler = new GetTicketByIdHandler();
          TicketController ticketController = new TicketController();




        app.get("/users/{id}", getUserByIdHandler);

        app.post("/users", createUserHandler);

        app.delete("/users/{id}", deleteUserHandler);


        app.start();

    }

}
